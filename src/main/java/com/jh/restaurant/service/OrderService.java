package com.jh.restaurant.service;

import com.jh.restaurant.domain.entity.OrderDetail;
import com.jh.restaurant.dto.OrderDTO;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/18 13:39
 */
public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO cancelOrder (String orderId);

    OrderDTO finishOrder(OrderDTO orderDTO);

    OrderDTO payOrder(OrderDTO orderDTO);

    OrderDTO findOne(String orderId);

    List<OrderDTO> findList(String openid);

    List<OrderDTO> findList();

}
