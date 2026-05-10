package com.myy.bpds.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myy.bpds.cart.entity.CartEntity;
import com.myy.bpds.cart.mapper.CartMapper;
import com.myy.bpds.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 购物车Service实现类
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    
    private final CartMapper cartMapper;
    
    @Override
    @Transactional
    public CartEntity getOrCreateCart(String userId) {
        // 查询用户是否已有购物车
        LambdaQueryWrapper<CartEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartEntity::getUserId, userId);
        CartEntity cart = cartMapper.getOne(wrapper);
        
        if (cart != null) {
            return cart;
        }
        
        // 创建新购物车
        cart = new CartEntity();
        cart.setUserId(userId);
        cartMapper.save(cart);
        
        return cart;
    }
}