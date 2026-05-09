package com.myy.bpds.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myy.bpds.cart.entity.CartEntity;

/**
 * 购物车Service
 */
public interface CartService extends IService<CartEntity> {
    
    /**
     * 获取或创建用户购物车
     */
    CartEntity getOrCreateCart(String userId);
}