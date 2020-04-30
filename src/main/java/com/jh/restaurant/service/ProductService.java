package com.jh.restaurant.service;

import com.jh.restaurant.domain.entity.Cart;
import com.jh.restaurant.domain.entity.Product;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/15 23:16
 */
public interface ProductService {

    Product findOne(String productId);

    List<Product> findAllByStatus(int status);

    List<Product> findAll();

    int saveProduct(Product product);

    int updateProduct(Product product);

    int deleteProduct(String productId);

    void increaseStock(List<Cart> cartList);

    void decreaseStock(List<Cart> cartList);

}
