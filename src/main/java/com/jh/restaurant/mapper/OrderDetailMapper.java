package com.jh.restaurant.mapper;

import com.jh.restaurant.domain.entity.OrderDetail;
import com.jh.restaurant.domain.entity.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/18 13:30
 */
public interface OrderDetailMapper {

    int saveOrderDetail(@Param("orderDetail") OrderDetail orderDetail);

    List<OrderDetail> findOrderDetailByOrderId(@Param("orderId") String orderId);



}
