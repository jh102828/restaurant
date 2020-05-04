package com.jh.restaurant.service;

import com.jh.restaurant.domain.entity.User;

import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/14 21:53
 */
public interface UserService {

    /**
     * 用户登录
     * @param username 用户名
     * @return 返回查询的用户
     */
    List<User> login(String username);

    /**
     * 用户注册
     * @param user 注册的用户
     * @return 返回注册成功条目
     */
    int register(User user);

    User findUserByOpenid(String openid);

    int saveUser(User user);

    List<User> findAllByStatus(int status);

    int deleteUser(String openid);

    int unlockUser(String openid);

    User findOne(String openid);

    List<User> searchUser(String keyword);
}
