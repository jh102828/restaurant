package com.jh.restaurant.service.impl;

import com.jh.restaurant.domain.entity.Cart;
import com.jh.restaurant.mapper.CartMapper;
import com.jh.restaurant.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/5/2 16:08
 */
@Service("cartService")
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;

    @Override
    public List<Cart> list(String openid) {
        return cartMapper.findCartByOpenid(openid);
    }

    @Override
    public Cart findOne(Cart cart) {
        return cartMapper.findOne(cart);
    }

    @Override
    public int updateNumber(String openid, String productId, int op) {
        if (op == 1) {
            return cartMapper.increaseCartNum(openid, productId);
        }
        return cartMapper.decreaseCartNum(openid, productId);
    }

    @Override
    public int saveCart(Cart cart) {
        int result;
        Cart one = cartMapper.findOne(cart);
        if (one != null) {
            result = cartMapper.increaseCartNum(cart.getOpenid(),cart.getProductId());
        } else {
            result = cartMapper.saveCart(cart);
        }
        return result;
    }

    @Override
    public int deleteCart(Cart cart) {
        return cartMapper.deleteCart(cart);
    }
}
