package com.jh.restaurant.service;

import com.jh.restaurant.domain.entity.Category;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/16 23:36
 */
public interface CategoryService {

    Category findOne(String categoryId);

    List<Category> findAll();

    List<Category> findCategoryByType(List<String> typeList);

    int saveCategory(Category category);

}
