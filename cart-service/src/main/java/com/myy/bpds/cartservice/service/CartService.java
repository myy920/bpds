package com.myy.bpds.cartservice.service;

import com.myy.bpds.cartservice.entity.CartEntity;

/**
 * 购物车Service
 */
public interface CartService {
    
    /**
     * 获取或创建用户购物车
     */
    CartEntity getOrCreateCart(String userId);
}