package com.jh.restaurant.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/18 22:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private Integer cartId;

    private String productId;

    private Integer productQuantity;

    public Cart(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
