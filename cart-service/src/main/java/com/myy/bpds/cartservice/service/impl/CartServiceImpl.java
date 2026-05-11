package com.myy.bpds.cartservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myy.bpds.cartservice.dao.CartDao;
import com.myy.bpds.cartservice.entity.CartEntity;
import com.myy.bpds.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 购物车Service实现类
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;

    @Override
    @Transactional
    public CartEntity getOrCreateCart(String userId) {
        // 查询用户是否已有购物车
        LambdaQueryWrapper<CartEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartEntity::getUserId, userId);
        CartEntity cart = cartDao.getOne(wrapper);

        if (cart != null) {
            return cart;
        }

        // 创建新购物车
        cart = new CartEntity();
        cart.setUserId(userId);
        cartDao.save(cart);

        return cart;
    }
}