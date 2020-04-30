package com.jh.restaurant.service.impl;

import com.jh.restaurant.domain.entity.User;
import com.jh.restaurant.mapper.UserMapper;
import com.jh.restaurant.service.UserService;
import com.jh.restaurant.util.KeyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/14 21:54
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<User> login(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public int register(User user) {
        user.setOpenid(KeyUtil.getUniqueKey());
        return userMapper.saveUser(user);
    }

    @Override
    public User findUserByOpenid(String openid) {
        return userMapper.findByOpenid(openid);
    }

    @Override
    public int saveUser(User user) {
        user.setOpenid(KeyUtil.getUniqueKey());
        return userMapper.saveUser(user);
    }

    @Override
    public List<User> findAllByStatus(int status) {
        return userMapper.findByStatus(status);
    }

    @Override
    public int deleteUser(String openid) {
        return userMapper.deleteUser(openid);
    }
}
