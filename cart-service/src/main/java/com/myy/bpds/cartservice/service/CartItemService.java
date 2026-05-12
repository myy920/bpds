package com.myy.bpds.cartservice.service;

import com.myy.bpds.cartservice.dto.AddCartItemDTO;
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
    void addToCart(String itemId, Integer quantity);

    void batchAddToCart(List<AddCartItemDTO> addCartItems);
}
