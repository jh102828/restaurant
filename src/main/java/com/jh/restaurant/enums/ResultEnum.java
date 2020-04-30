package com.jh.restaurant.enums;

import lombok.Getter;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/14 22:02
 */
@Getter
public enum ResultEnum {
    
    // 成功
    SUCCESS(200, "success"),
    
    // 失败
    ERROR(404, "error"),

    // 商品不存在
    PRODUCT_NOT_EXIST(404, "商品不存在"),

    // 商品库存不足
    PRODUCT_STOCK_LOW(404, "商品库存不足"),

    // 订单状态错误
    ORDER_STATUS_ERROR(404, "订单状态错误"),

    // 订单更新失败
    ORDER_UPDATE_FAIL(404, "订单更新失败"),

    // 订单详情为空
    ORDER_DETAIL_EMPTY(404, "订单详情为空"),

    // 参数错误
    PARAM_ERROR(404, "参数错误"),

    // 购物车为空
    CART_EMPTY(404, "购物车为空"),

    // 订单用户错误
    ORDER_OWNER_ERROR(404, "订单用户错误"),
    ;
    
    private Integer code;
    
    private String message;


    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
