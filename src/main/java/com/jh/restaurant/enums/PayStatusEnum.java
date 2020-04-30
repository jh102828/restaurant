package com.jh.restaurant.enums;

import lombok.Getter;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/15 21:23
 */
@Getter
public enum PayStatusEnum {

    // 等待支付
    WAIT(0,"等待支付"),

    // 支付成功
    SUCCESS(1, "支付成功"),

    // 支付失败
    ERROR(-1, "支付失败"),
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
