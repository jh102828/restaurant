package com.jh.restaurant.enums;

import lombok.Getter;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/15 21:21
 */
@Getter
public enum OrderStatusEnum {

    // 新订单
    NEW(0, "新订单"),

    // 已完结订单
    FINISHED(1, "已完结"),

    // 已取消订单
    CANCEL(-1, "已取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
