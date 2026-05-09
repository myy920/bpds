package com.myy.bpds.cart.dto;

import com.myy.bpds.cart.entity.CartItemEntity;
import com.myy.bpds.itemservice.entity.ItemEntity;
import lombok.Data;

/**
 * 购物车项DTO（包含商品信息）
 */
@Data
public class CartItemDTO {
    /**
     * 购物车项信息
     */
    private CartItemEntity cartItem;
    
    /**
     * 商品信息
     */
    private ItemEntity item;
}
