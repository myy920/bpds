package com.myy.bpds.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myy.bpds.cart.entity.CartEntity;
import com.myy.bpds.cart.mapper.CartMapper;
import com.myy.bpds.cart.service.CartService;
import com.myy.bpds.common.exception.BpdsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 购物车Service实现类
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, CartEntity> implements CartService {
    
    @Override
    @Transactional
    public CartEntity getOrCreateCart(String userId) {
        // 查询用户是否已有购物车
        LambdaQueryWrapper<CartEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartEntity::getUserId, userId);
        CartEntity cart = this.getOne(wrapper);
        
        if (cart != null) {
            return cart;
        }
        
        // 创建新购物车
        cart = new CartEntity();
        cart.setUserId(userId);
        this.save(cart);
        
        return cart;
    }
}