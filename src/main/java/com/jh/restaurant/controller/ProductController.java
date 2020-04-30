package com.jh.restaurant.controller;

import com.jh.restaurant.domain.entity.Category;
import com.jh.restaurant.domain.entity.Product;
import com.jh.restaurant.domain.vo.CategoryVO;
import com.jh.restaurant.domain.vo.ProductVO;
import com.jh.restaurant.domain.vo.Result;
import com.jh.restaurant.service.CategoryService;
import com.jh.restaurant.service.ProductService;
import com.jh.restaurant.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/16 14:13
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result list(@RequestParam(value = "status", defaultValue = "0") int status) {

        List<Product> productList = productService.findAllByStatus(status);

        List<String> categoryTypeList = productList.stream()
                .map(Product::getCategoryType)
                .collect(Collectors.toList());

        List<Category> categoryList = categoryService.findCategoryByType(categoryTypeList);

        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryName(category.getCategoryName());
            categoryVO.setCategoryType(category.getCategoryType());

            List<ProductVO> productVOList = new ArrayList<>();
            for (Product product : productList) {
                if (category.getCategoryType().equals(product.getCategoryType())) {
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(product, productVO);
                    productVOList.add(productVO);
                }
            }
            categoryVO.setProductList(productVOList);
            categoryVOList.add(categoryVO);
        }

        return ResultUtil.success(categoryVOList);

    }

}
