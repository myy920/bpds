package com.myy.bpds.cartservice.service;

import com.myy.bpds.cartservice.dto.CartDTO;
import com.myy.bpds.cartservice.dto.CartItemDTO;
import com.myy.bpds.cartservice.entity.CartItemEntity;

import java.util.List;

/**
 * 购物车项Service
 */
public interface CartItemService {

    /**
     * 添加商品到购物车
     */
    void addToCart(String userId, String itemId, Integer quantity);

    /**
     * 查询用户购物车项列表
     */
    List<CartItemEntity> getCartItemsByUserId(String userId);

    /**
     * 查询用户购物车详情（包含商品信息）
     */
    List<CartItemDTO> getCartDetailsByUserId(String userId);

    /**
     * 查询用户完整购物车信息（包含购物车和购物车项列表）
     */
    CartDTO getCartByUserId(String userId);

    /**
     * 删除购物车项
     */
    void removeCartItem(String cartItemId);

    /**
     * 更新购物车项数量
     */
    void updateQuantity(String cartItemId, Integer quantity);

    /**
     * 选中/取消选中购物车项
     */
    void selectCartItem(String cartItemId, Integer selected);
}
