package com.jh.restaurant.service.impl;

import com.jh.restaurant.domain.entity.*;
import com.jh.restaurant.dto.OrderDTO;
import com.jh.restaurant.enums.OrderStatusEnum;
import com.jh.restaurant.enums.PayStatusEnum;
import com.jh.restaurant.enums.ResultEnum;
import com.jh.restaurant.exception.MyException;
import com.jh.restaurant.mapper.OrderDetailMapper;
import com.jh.restaurant.mapper.OrderMasterMapper;
import com.jh.restaurant.service.CartService;
import com.jh.restaurant.service.OrderService;
import com.jh.restaurant.service.ProductService;
import com.jh.restaurant.service.UserService;
import com.jh.restaurant.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/18 13:46
 */
@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private ProductService productService;

    @Resource
    private UserService userService;

    @Resource
    private CartService cartService;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private OrderMasterMapper orderMasterMapper;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey();
        double orderAmount = 0.0d;

        // 查询OrderDetailList中的商品数量和价格
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            Product product = productService.findOne(orderDetail.getProductId());
            if (product == null) {
                log.error("[OrderServiceImpl] 商品不存在");
                throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 订单总价
            orderAmount = product.getProductPrice() * orderDetail.getProductQuantity()
                    + orderAmount;

            // orderDetail入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(product, orderDetail);
            orderDetailMapper.saveOrderDetail(orderDetail);
        }

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterMapper.saveOrderMaster(orderMaster);

        // 商品扣库存
        List<Cart> cartList = orderDTO.getOrderDetailList()
                .stream()
                .map(o -> new Cart(o.getProductId(), o.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartList);

        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrder(String orderId) {

        OrderDTO orderDTO = findOne(orderId);
        OrderMaster orderMaster = new OrderMaster();

        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[OrderServiceImpl:cancelOrder] 订单状态不正确");
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int updateResult = orderMasterMapper.saveOrderMaster(orderMaster);
        if (updateResult < 0) {
            log.error("[OrderServiceImpl:cancelOrder] 更新失败");
            throw new MyException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[OrderServiceImpl:cancelOrder] 订单中无商品详情");
            throw new MyException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<Cart> cartList = orderDTO.getOrderDetailList()
                .stream()
                .map(e -> new Cart(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartList);

        // 如果已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            User user = userService.findUserByOpenid(orderDTO.getBuyerOpenid());
            double money = user.getMoney();
            for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                Product product = productService.findOne(orderDetail.getProductId());
                if (product == null) {
                    log.error("[OrderServiceImpl] 商品不存在");
                    throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
                }
                money = product.getProductPrice() * orderDetail.getProductQuantity()
                        + money;
            }
            user.setMoney(money);
            userService.saveUser(user);
        }

        return orderDTO;
    }

    @Override
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO payOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterMapper.findOrderMasterByOrderId(orderId);
        List<OrderDetail> orderDetailList = orderDetailMapper.findOrderDetailByOrderId(orderId);
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findList(String openid) {
        return null;
    }

    @Override
    public List<OrderMaster> findList(int status) {
        return orderMasterMapper.findOrderMasterByStatus(status);
    }


    @Override
    public OrderDTO saveOrder(String[] productIds, OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey();
        double totalMoney = 0.0d;
        List<String> ids = Arrays.asList(productIds);
        List<Product> productList = productService.findByIds(ids);
        List<Cart> cartList = new ArrayList<>();
        for (Product product : productList) {
            Cart one = new Cart(orderDTO.getBuyerOpenid(), product.getProductId());
            Cart cart = cartService.findOne(one);
            cartList.add(cart);
            // 计算订单总价
            totalMoney += product.getProductPrice() * cart.getProductQuantity();
            // orderDetail入库
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            BeanUtils.copyProperties(product,orderDetail);
            orderDetail.setProductQuantity(cart.getProductQuantity());
            orderDetailMapper.saveOrderDetail(orderDetail);
        }
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(totalMoney);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterMapper.saveOrderMaster(orderMaster);
        // 商品扣库存
        productService.decreaseStock(cartList);

        return orderDTO;
    }

    @Override
    public int finish(String orderId) {
        return orderMasterMapper.finishOrder(orderId);
    }

    @Override
    public int cancel(String orderId) {
        return orderMasterMapper.cancelOrder(orderId);
    }

    @Override
    public List<OrderDetail> getOrderDetailList(String orderId) {
        return orderDetailMapper.findOrderDetailByOrderId(orderId);
    }
}
