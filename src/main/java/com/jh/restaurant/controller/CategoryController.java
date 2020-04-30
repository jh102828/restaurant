package com.jh.restaurant.controller;

import com.jh.restaurant.domain.entity.Category;
import com.jh.restaurant.domain.vo.Result;
import com.jh.restaurant.service.CategoryService;
import com.jh.restaurant.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/27 16:11
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result<List<Category>> getAll() {
        List<Category> list = categoryService.findAll();
        return ResultUtil.success(list);
    }

}
