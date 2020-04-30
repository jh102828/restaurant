package com.jh.restaurant.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jh.restaurant.enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/14 0:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value={"password"})
public class User implements Serializable {

    private static final long serialVersionUID = 8588977034124519050L;

    private Integer uid;

    private String openid;

    private String username;

    private String password;

    private String image;

    private String email;

    private String phone;

    private String address;

    private Double money;

    private Integer isAdmin = UserEnum.NOT_ADMIN.getCode();

    private Integer isValid = UserEnum.IS_VALID.getCode();

}
