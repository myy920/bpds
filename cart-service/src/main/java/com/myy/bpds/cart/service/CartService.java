package com.myy.bpds.cart.service;

import com.myy.bpds.cart.entity.CartEntity;

/**
 * 购物车Service
 */
public interface CartService {
    
    /**
     * 获取或创建用户购物车
     */
    CartEntity getOrCreateCart(String userId);
}