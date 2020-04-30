package com.jh.restaurant.domain.entity;

import com.jh.restaurant.enums.ProductStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/15 21:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = -7021693262445253514L;

    private String productId;

    private String productName;

    private Double productPrice;

    private Integer productStock;

    private String productDescription;

    private String productImage;

    private Integer productStatus = ProductStatusEnum.UP.getCode();

    private String categoryType;

    private Date createTime;

    private Date updateTime;

}
