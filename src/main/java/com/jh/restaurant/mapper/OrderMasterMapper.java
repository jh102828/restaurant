package com.jh.restaurant.mapper;

import com.jh.restaurant.domain.entity.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/18 12:45
 */
public interface OrderMasterMapper {

    List<OrderMaster> findOrderMasterByOpenid(@Param("openid") String openid,
                                              @Param("status") int status);

    int saveOrderMaster(@Param("orderMaster") OrderMaster orderMaster);

    OrderMaster findOrderMasterByOrderId(@Param("orderId") String orderId);

    List<OrderMaster> findOrderMasterByStatus(@Param("status") int status);

    int finishOrder(@Param("orderId") String orderId);

    int cancelOrder(@Param("orderId") String orderId);
}
