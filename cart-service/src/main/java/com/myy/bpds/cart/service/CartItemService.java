package com.myy.bpds.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myy.bpds.cart.dto.CartItemDTO;
import com.myy.bpds.cart.entity.CartItemEntity;

import java.util.List;

/**
 * 购物车项Service
 */
public interface CartItemService extends IService<CartItemEntity> {
    
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
