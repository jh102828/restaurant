package com.jh.restaurant.mapper;

import com.jh.restaurant.domain.entity.Product;
import com.jh.restaurant.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/14 10:10
 */
@Repository
public interface UserMapper {

    List<User> findAll();

    List<User> findByStatus(@Param("status") int status);

    List<User> findByName(@Param("username") String username);

    User findByOpenid(@Param("openid") String openid);

    int saveUser(@Param("user") User user);

    int updateUser(@Param("user") User user);

    int deleteUser(@Param("openid") String openid);

}
