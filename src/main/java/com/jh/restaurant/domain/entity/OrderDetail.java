package com.jh.restaurant.domain.entity;

import lombok.Data;


/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/15 21:23
 */
@Data
public class OrderDetail {

    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private Double productPrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    private String productImage;

}
