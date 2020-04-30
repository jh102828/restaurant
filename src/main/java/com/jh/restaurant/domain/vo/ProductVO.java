package com.jh.restaurant.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/17 22:52
 */
@Data
public class ProductVO {

    private String productId;

    private String productName;

    private Double productPrice;

    private Integer productStock;

    @JsonProperty("description")
    private String productDescription;

    private String productImage;

    private String categoryType;

}
