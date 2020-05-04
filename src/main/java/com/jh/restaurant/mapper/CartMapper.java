package com.jh.restaurant.mapper;

import com.jh.restaurant.domain.entity.Cart;
import com.jh.restaurant.domain.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/5/2 12:53
 */
public interface CartMapper {

    List<Cart> findCartByOpenid(@Param("openid") String openid);

    int increaseCartNum(@Param("openid") String openid,
                        @Param("productId") String productId);

    int decreaseCartNum(@Param("openid") String openid,
                        @Param("productId") String productId);

    Cart findOne(@Param("cart") Cart cart);

    int deleteCart(@Param("cart") Cart cart);

    int saveCart(@Param("cart") Cart cart);
}
