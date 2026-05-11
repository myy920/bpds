package com.myy.bpds.cartservice.dto;

import com.myy.bpds.cartservice.entity.CartEntity;
import lombok.Data;

import java.util.List;

/**
 * 购物车DTO（包含购物车信息和购物车项列表）
 */
@Data
public class CartDTO {
    /**
     * 购物车信息
     */
    private CartEntity cart;

    /**
     * 购物车项列表
     */
    private List<CartItemDTO> items;
}
