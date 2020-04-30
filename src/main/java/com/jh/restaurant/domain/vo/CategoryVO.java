package com.jh.restaurant.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/17 22:02
 */
@Data
public class CategoryVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private String categoryType;

    @JsonProperty("products")
    private List<ProductVO> productList;

}
