package com.jh.restaurant.util;

import com.jh.restaurant.domain.vo.Result;
import com.jh.restaurant.enums.ResultEnum;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/14 22:11
 */
public class ResultUtil {

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.setData(object);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMessage(ResultEnum.ERROR.getMessage());
        return result;
    }

}
