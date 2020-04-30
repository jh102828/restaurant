package com.jh.restaurant.enums;

import lombok.Getter;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/14 0:38
 */
@Getter
public enum UserEnum {

    // 用户为管理员
    IS_ADMIN(1, "管理员"),

    // 用户为普通用户
    NOT_ADMIN(0, "普通用户"),

    // 用户有效
    IS_VALID(1, "有效"),

    // 用户已过期
    NOT_VALID(0, "过期"),

    // 用户名已存在
    NAME_IS_EXIST(404, "用户名已存在"),
    ;

    private Integer code;

    private String message;

    UserEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
