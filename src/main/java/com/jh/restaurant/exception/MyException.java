package com.jh.restaurant.exception;

import com.jh.restaurant.enums.ResultEnum;
import lombok.Getter;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/18 14:00
 */
@Getter
public class MyException extends RuntimeException {

    private static final long serialVersionUID = -8370097435103715285L;

    private Integer code;

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public MyException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
