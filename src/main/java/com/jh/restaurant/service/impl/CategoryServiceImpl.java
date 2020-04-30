package com.jh.restaurant.service.impl;

import com.jh.restaurant.domain.entity.Category;
import com.jh.restaurant.mapper.CategoryMapper;
import com.jh.restaurant.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/16 23:40
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Category findOne(String categoryId) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public List<Category> findCategoryByType(List<String> typeList) {
        return categoryMapper.findCategoryByType(typeList);
    }

    @Override
    public int saveCategory(Category category) {
        return 0;
    }
}
