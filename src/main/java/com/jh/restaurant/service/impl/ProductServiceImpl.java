package com.jh.restaurant.service.impl;

import com.jh.restaurant.domain.entity.Cart;
import com.jh.restaurant.domain.entity.Product;
import com.jh.restaurant.enums.ResultEnum;
import com.jh.restaurant.exception.MyException;
import com.jh.restaurant.mapper.ProductMapper;
import com.jh.restaurant.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/16 14:04
 */
@Slf4j
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public Product findOne(String productId) {
        return productMapper.findProductById(productId);
    }

    @Override
    public List<Product> findAllByStatus(int status) {
        return productMapper.findProductByStatus(status);
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findProductAll();
    }

    @Override
    public int saveProduct(Product product) {
        return productMapper.saveProduct(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public int deleteProduct(String productId) {
        return productMapper.deleteProduct(productId);
    }

    @Override
    public void increaseStock(List<Cart> cartList) {
        for (Cart cart : cartList) {
            Product product = productMapper.findProductById(cart.getProductId());
            if (product == null) {
                log.error("[ProductServiceImpl:increaseStock] 商品不能为空");
                throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = cart.getProductQuantity() + product.getProductStock();
            product.setProductStock(stock);
            productMapper.saveProduct(product);
        }
    }

    @Override
    public void decreaseStock(List<Cart> cartList) {
        for (Cart cart : cartList) {
            Product product = productMapper.findProductById(cart.getProductId());
            if (product == null) {
                log.error("[ProductServiceImpl:decreaseStock] 商品不能为空");
                throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int stock = product.getProductStock() - cart.getProductQuantity();
            if (stock < 0) {
                log.error("[ProductServiceImpl:decreaseStock] 库存不足");
                throw new MyException(ResultEnum.PRODUCT_STOCK_LOW);
            }
            product.setProductStock(stock);
            productMapper.saveProduct(product);
        }
    }

    @Override
    public int unlockProduct(String productId) {
        return productMapper.unlockProduct(productId);
    }

    @Override
    public List<Product> findByIds(List<String> ids) {
        return productMapper.findByProductIds(ids);
    }
}
