package com.jh.restaurant.controller;

import com.jh.restaurant.domain.entity.Product;
import com.jh.restaurant.domain.entity.User;
import com.jh.restaurant.domain.vo.Result;
import com.jh.restaurant.enums.UserEnum;
import com.jh.restaurant.service.ProductService;
import com.jh.restaurant.service.UserService;
import com.jh.restaurant.util.ResultUtil;
import com.jh.restaurant.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Resource
    private ProductService productService;

    @GetMapping("/checkUser")
    public Result checkUser(String[] openids) {
        for (String openid : openids) {
            User user = userService.findOne(openid);
            if (user.getIsAdmin() == 1) {
                return ResultUtil.error();
            }
        }
        return ResultUtil.success();
    }

    @GetMapping("/selectUser")
    public Result<List<User>> selectUser(@RequestParam(value = "status", defaultValue = "1")
                                                 int status) {
        List<User> userList = userService
                .findAllByStatus(status);
        return ResultUtil.success(userList);
    }

    @PostMapping("/saveUser")
    public Result addUser(User user) {
        int result;
        try {
            result = userService.saveUser(user);
            return ResultUtil.success(result);
        } catch (Exception e) {
            log.error("[AdminController:addUser]" + UserEnum.NAME_IS_EXIST.getMessage());
            return ResultUtil.error();
        }

    }

    @GetMapping("/deleteUser")
    public Result deleteUser(@RequestParam("ids") String[] ids) {
        try {
            for (String openid : ids) {
                userService.deleteUser(openid);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error();
        }

    }

    @GetMapping("/unlockUser")
    public Result unlockUser(@RequestParam("ids") String[] ids) {
        try {
            for (String openid : ids) {
                userService.unlockUser(openid);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error();
        }

    }

    @RequestMapping("/searchUser")
    public Result<List<User>> search(@RequestParam("keyword") String keyword) {
        List<User> userList = userService.searchUser(keyword);
        return ResultUtil.success(userList);
    }

    @GetMapping("/selectProduct")
    public Result<List<Product>> selectProduct(@RequestParam(value = "status", defaultValue = "1") int status) {
        List<Product> productList = productService.findAllByStatus(status);
        return ResultUtil.success(productList);
    }

    public void addProduct() {

    }

    @GetMapping("/deleteProduct")
    public Result deleteProduct(@RequestParam("ids") String[] ids) {
        try {
            for (String productId : ids) {
                productService.deleteProduct(productId);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error();
        }
    }

    @GetMapping("/unlockProduct")
    public Result unlockProduct(@RequestParam("ids") String[] ids) {
        try {
            for (String productId : ids) {
                productService.unlockProduct(productId);
            }
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error();
        }

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result uploadFile(@RequestParam("img") MultipartFile multipartFile, Product product) throws IOException {
        if (multipartFile.getSize() != 0 && product != null) {
            String fileName = UploadUtil.saveFile(multipartFile);
            product.setProductImage(fileName);
            productService.saveProduct(product);
            return ResultUtil.success(product);
        }
        return ResultUtil.error();
    }

    @GetMapping("/checkProduct")
    public Result checkProduct(@RequestParam("productIds") String[] productIds) {
        List<String> ids = Arrays.asList(productIds);
        List<Product> productList = productService.findByIds(ids);
        if (productList.size() == 0) {
            return ResultUtil.success();
        }
        return ResultUtil.error();
    }

}
