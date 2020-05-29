package com.jh.restaurant.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jh.restaurant.domain.entity.OrderDetail;
import com.jh.restaurant.dto.OrderDTO;
import com.jh.restaurant.enums.ResultEnum;
import com.jh.restaurant.exception.MyException;
import com.jh.restaurant.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/21 23:37
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm) {

        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList;
        try{
            orderDetailList = gson.fromJson(orderForm.getCartJSon(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("[OrderForm2OrderDTO:convert] 参数错误，string={}", orderForm.getCartJSon());
            throw new MyException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }
}
