package com.jh.restaurant.mapper;

import com.jh.restaurant.domain.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/15 22:22
 */
public interface ProductMapper {

    Product findProductById(@Param("productId") String productId);

    List<Product> findProductAll();

    List<Product> findProductByStatus(@Param("productStatus") int productStatus);

    int saveProduct(@Param("product") Product product);

    int updateProduct(@Param("product") Product product);

    int deleteProduct(@Param("productId") String productId);

    int unlockProduct(@Param("productId") String productId);

    List<Product> findByProductIds(@Param("ids") List<String> ids);

}
