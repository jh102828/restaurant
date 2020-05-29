package com.jh.restaurant.form;

import com.jh.restaurant.domain.entity.Cart;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/18 22:28
 */
@Data
public class  OrderForm {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    @NotBlank(message = "收货地址不能为空")
    private String address;

    @NotBlank(message = "openid不能为空")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String cartJSon;

}
