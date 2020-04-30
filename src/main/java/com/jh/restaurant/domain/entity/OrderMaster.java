package com.jh.restaurant.domain.entity;

import com.jh.restaurant.enums.OrderStatusEnum;
import com.jh.restaurant.enums.PayStatusEnum;
import lombok.Data;


/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/15 21:19
 */
@Data
public class OrderMaster {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private Double orderAmount;

    /**
     * 订单状态, 默认为0新下单.
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态, 默认为0未支付.
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

}
