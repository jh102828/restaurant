package com.jh.restaurant.dto;

import com.jh.restaurant.domain.entity.OrderDetail;
import lombok.Data;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/15 21:28
 */
@Data
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private Double orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    List<OrderDetail> orderDetailList;

}
