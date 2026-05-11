package com.myy.bpds.cartservice.controller;

import com.myy.bpds.cartservice.dto.CartDTO;
import com.myy.bpds.cartservice.service.CartItemService;
import com.myy.bpds.common.dto.Result;
import com.myy.bpds.common.utils.BpdsContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车Controller
 */

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;

    /**
     * 添加商品到购物车
     */
    @PostMapping("/add")
    public Result<Void> addToCart(
            @RequestParam String itemId,
            @RequestParam(defaultValue = "1") Integer quantity) {
        String userId = BpdsContextHolder.currentUserId();
        cartItemService.addToCart(userId, itemId, quantity);
        return Result.ok();
    }

    /**
     * 查询用户完整购物车信息（包含购物车和购物车项列表）
     */
    @GetMapping("/info")
    public Result<CartDTO> getCartInfo() {
        String userId = BpdsContextHolder.currentUserId();
        CartDTO cartInfo = cartItemService.getCartByUserId(userId);
        return Result.ok(cartInfo);
    }
}
