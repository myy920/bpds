package com.myy.bpds.cart.controller;

import com.myy.bpds.cart.dto.CartItemDTO;
import com.myy.bpds.cart.entity.CartItemEntity;
import com.myy.bpds.cart.service.CartItemService;
import com.myy.bpds.common.dto.Result;
import com.myy.bpds.common.utils.BpdsContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 从购物车删除商品
     */
    @DeleteMapping("/{cartItemId}")
    public Result<Void> removeFromCart(@PathVariable String cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return Result.ok();
    }

    /**
     * 更新购物车商品数量
     */
    @PutMapping("/quantity")
    public Result<Void> updateQuantity(
            @RequestParam String cartItemId,
            @RequestParam Integer quantity) {
        cartItemService.updateQuantity(cartItemId, quantity);
        return Result.ok();
    }

    /**
     * 选中/取消选中购物车商品
     */
    @PutMapping("/select")
    public Result<Void> selectCartItem(
            @RequestParam String cartItemId,
            @RequestParam Integer selected) {
        cartItemService.selectCartItem(cartItemId, selected);
        return Result.ok();
    }

    /**
     * 查询用户购物车列表
     */
    @GetMapping("/list")
    public Result<List<CartItemEntity>> getCartList() {
        String userId = BpdsContextHolder.currentUserId();
        List<CartItemEntity> cartItems = cartItemService.getCartItemsByUserId(userId);
        return Result.ok(cartItems);
    }

    /**
     * 查询用户购物车详情（包含商品信息）
     */
    @GetMapping("/detail")
    public Result<List<CartItemDTO>> getCartDetail() {
        String userId = BpdsContextHolder.currentUserId();
        List<CartItemDTO> cartDetails = cartItemService.getCartDetailsByUserId(userId);
        return Result.ok(cartDetails);
    }
}
