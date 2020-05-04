package com.jh.restaurant.service;

import com.jh.restaurant.domain.entity.Cart;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/5/2 16:05
 */
public interface CartService {

    List<Cart> list(String openid);

    Cart findOne(Cart cart);

    int updateNumber(String openid, String productId, int op);

    int saveCart(Cart cart);

    int deleteCart(Cart cart);

}
