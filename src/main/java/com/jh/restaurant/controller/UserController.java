package com.jh.restaurant.controller;

import com.jh.restaurant.domain.entity.User;
import com.jh.restaurant.domain.vo.Result;
import com.jh.restaurant.service.UserService;
import com.jh.restaurant.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/14 21:59
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        try {
            List<User> userList = userService.login(username);
            if (userList.size() > 0) {
                for (User user : userList) {
                    if ((user.getPassword().equals(password))) {
                        return ResultUtil.success(user);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResultUtil.error();
    }

    @PostMapping("/register")
    public Result register(User user) {
        try {
            int register = userService.register(user);
            return ResultUtil.success(register);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error();
        }
    }

    @GetMapping("/check")
    public Result check(String username) {
        List<User> userList = userService.login(username);
        if (userList.size() > 0) {
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }
}
