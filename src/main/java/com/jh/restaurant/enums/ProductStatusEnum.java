package com.jh.restaurant.enums;

import lombok.Getter;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/15 21:16
 */
@Getter
public enum ProductStatusEnum {

    // 商品在线上卖
    UP(1, "上线"),

    // 商品已下架
    DOWN(0, "下架");

    private Integer code;

    private String message;

    ProductStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
