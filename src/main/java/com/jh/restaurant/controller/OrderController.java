package com.jh.restaurant.controller;

import com.github.pagehelper.PageHelper;
import com.jh.restaurant.converter.OrderForm2OrderDTO;
import com.jh.restaurant.domain.vo.Result;
import com.jh.restaurant.dto.OrderDTO;
import com.jh.restaurant.enums.ResultEnum;
import com.jh.restaurant.exception.MyException;
import com.jh.restaurant.form.OrderForm;
import com.jh.restaurant.service.OrderService;
import com.jh.restaurant.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/21 23:44
 */
@Slf4j
@RequestMapping("/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 用户创建订单
     * @param orderForm 用户的订单表
     * @return 返回订单号
     */
    @PostMapping("/create")
    public Result<Map<String, String>> create(@Validated OrderForm orderForm) {

        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[OrderController:create] 购物车不能为空");
            throw new MyException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.createOrder(orderDTO);
        Map<String, String> map = new HashMap<>(16);
        map.put("orderId", createResult.getOrderId());
        return ResultUtil.success(map);

    }

    /**
     * 用户查询自己下的所有订单
     * @param openid 用户的openid
     * @param page 分页处理的第几页
     * @param size 分页处理的每页最大能放多少条目
     * @return 用户的订单列表
     */
    @GetMapping("/list")
    public Result<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {

        if (StringUtils.isEmpty(openid)) {
            log.error("[OrderController:list] openid为空");
            throw new MyException(ResultEnum.PARAM_ERROR);
        }

        PageHelper.startPage(page, size);
        List<OrderDTO> orderList = orderService.findList(openid);
        return ResultUtil.success(orderList);

    }

    /**
     * 用户查询自己的某一条订单
     * @param openid 用户的openid
     * @param orderId 用户查询的订单id
     * @return 返回一条目标订单
     */
    @GetMapping("/detail")
    public Result<OrderDTO> detail(@RequestParam("openid") String openid,
                                   @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        // 判断是否是自己的订单
        if (!openid.equals(orderDTO.getBuyerOpenid())) {
            log.error("[查询订单] 订单的openid不一致， openid={}, orderDTO={}", openid, orderDTO);
            throw new MyException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return ResultUtil.success(orderDTO);
    }


    @GetMapping("/cancel")
    public Result cancel(@RequestParam("orderId") String orderId) {
        orderService.cancelOrder(orderId);
        return ResultUtil.success();
    }


}
