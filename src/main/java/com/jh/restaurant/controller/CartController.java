package com.jh.restaurant.controller;

import com.jh.restaurant.domain.entity.Cart;
import com.jh.restaurant.domain.vo.Result;
import com.jh.restaurant.service.CartService;
import com.jh.restaurant.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/5/2 16:11
 */
@RequestMapping("/cart")
@RestController
public class CartController {

    @Resource
    private CartService cartService;

    @GetMapping("/list")
    public Result<List<Cart>> list(@RequestParam("openid") String openid) {
        return ResultUtil.success(cartService.list(openid));
    }


    @PostMapping("/findOne")
    public Result findOne(@RequestParam("cart") Cart cart) {
        try {
            Cart result = cartService.findOne(cart);
            return ResultUtil.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error();
        }
    }

    @PostMapping("/addCart")
    public Result addCart(Cart cart) {
        try {
            int result = cartService.saveCart(cart);
            return ResultUtil.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error();
        }

    }

    @PostMapping("/countNum")
    public Result updataCart(@RequestParam("openid") String openid,
                             @RequestParam("productId") String productId,
                             @RequestParam("op") int op) {
        int result;
        try {
            result = cartService.updateNumber(openid, productId, op);
            return ResultUtil.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error();
        }
    }

    @PostMapping("/deleteCart")
    public Result deleteCart(Cart cart) {
        try {
            int result = cartService.deleteCart(cart);
            return ResultUtil.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error();
        }

    }

}
