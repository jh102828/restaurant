package com.jh.restaurant.domain.vo;

import lombok.Data;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/14 22:08
 */
@Data
public class Result<T> {

    private Integer code;

    private String message;

    T data;

}
