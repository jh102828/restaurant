package com.jh.restaurant.controller;

import com.jh.restaurant.domain.entity.User;
import com.jh.restaurant.domain.vo.Result;
import com.jh.restaurant.enums.UserEnum;
import com.jh.restaurant.exception.MyException;
import com.jh.restaurant.service.UserService;
import com.jh.restaurant.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/28 22:57
 */
@RequestMapping("/admin")
@RestController
@Slf4j
public class AdminController {

    @Resource
    UserService userService;

    @GetMapping("/selectUser")
    public Result<List<User>> selectUser(@RequestParam(value = "status", defaultValue = "1")
                                                 int status) {
        List<User> userList = userService
                .findAllByStatus(status);
        return ResultUtil.success(userList);
    }

    @PostMapping("/saveUser")
    public Result addUser(User user) {
        int result = 0;
        try {
            result = userService.saveUser(user);
            return ResultUtil.success(result);
        } catch (Exception e) {
            log.error("[AdminController:addUser]" + UserEnum.NAME_IS_EXIST.getMessage());
            return ResultUtil.error();
        }

    }

    @GetMapping("/deleteUser")
    public Result deleteUser(String[] ids) {
        try {
            for (String openid : ids) {
                userService.deleteUser(openid);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error();
        }

    }

    public void selectProduct() {

    }

    public void addProduct() {

    }

    public void deleteProduct() {

    }


}
