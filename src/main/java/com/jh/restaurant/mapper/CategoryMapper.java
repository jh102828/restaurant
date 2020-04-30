package com.jh.restaurant.mapper;

import com.jh.restaurant.domain.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/16 14:14
 */
public interface CategoryMapper {

    List<Category> findCategoryByType(@Param("typeList") List<String> typeList);

    List<Category> findAll();

}
