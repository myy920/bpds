package com.myy.bpds.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myy.bpds.cart.constants.CartErrorCode;
import com.myy.bpds.cart.dto.CartItemDTO;
import com.myy.bpds.cart.entity.CartEntity;
import com.myy.bpds.cart.entity.CartItemEntity;
import com.myy.bpds.cart.mapper.CartItemMapper;
import com.myy.bpds.cart.service.CartItemService;
import com.myy.bpds.cart.service.CartService;
import com.myy.bpds.common.exception.BpdsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车项Service实现类
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItemEntity> implements CartItemService {
    
    @Autowired
    private CartService cartService;
    
    @Override
    @Transactional
    public void addToCart(String userId, String itemId, Integer quantity) {
        // 获取或创建购物车
        CartEntity cart = cartService.getOrCreateCart(userId);
        
        // 检查购物车中是否已存在该商品
        LambdaQueryWrapper<CartItemEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItemEntity::getCartId, cart.getId())
               .eq(CartItemEntity::getItemId, itemId);
        CartItemEntity existingItem = this.getOne(wrapper);
        
        if (existingItem != null) {
            // 如果已存在，增加数量
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            this.updateById(existingItem);
        } else {
            // 如果不存在，新增购物车项
            CartItemEntity cartItem = new CartItemEntity();
            cartItem.setCartId(cart.getId());
            cartItem.setItemId(itemId);
            cartItem.setQuantity(quantity);
            cartItem.setSelected(1); // 默认选中
            this.save(cartItem);
        }
    }
    
    @Override
    public List<CartItemEntity> getCartItemsByUserId(String userId) {
        // 获取用户购物车
        CartEntity cart = cartService.getOrCreateCart(userId);
            
        // 查询购物车项列表
        LambdaQueryWrapper<CartItemEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItemEntity::getCartId, cart.getId())
               .orderByDesc(CartItemEntity::getCreateTime);
            
        return this.list(wrapper);
    }
        
    @Override
    public List<CartItemDTO> getCartDetailsByUserId(String userId) {
        // 获取购物车项列表
        List<CartItemEntity> cartItems = getCartItemsByUserId(userId);
            
        // TODO: 这里需要调用 item-service 获取商品信息
        // 目前返回简单的 DTO，后续可以通过 Feign 或 RestTemplate 调用 item-service
        List<CartItemDTO> result = new ArrayList<>();
        for (CartItemEntity cartItem : cartItems) {
            CartItemDTO dto = new CartItemDTO();
            dto.setCartItem(cartItem);
            // dto.setItem(itemService.getById(cartItem.getItemId())); // 需要注入 ItemService
            result.add(dto);
        }
            
        return result;
    }
    
    @Override
    @Transactional
    public void removeCartItem(String cartItemId) {
        this.removeById(cartItemId);
    }
    
    @Override
    @Transactional
    public void updateQuantity(String cartItemId, Integer quantity) {
        CartItemEntity cartItem = this.getById(cartItemId);
        if (cartItem == null) {
            throw new BpdsException(CartErrorCode.CART_ITEM_NOT_EXIST);
        }
        
        cartItem.setQuantity(quantity);
        this.updateById(cartItem);
    }
    
    @Override
    @Transactional
    public void selectCartItem(String cartItemId, Integer selected) {
        CartItemEntity cartItem = this.getById(cartItemId);
        if (cartItem == null) {
            throw new BpdsException(CartErrorCode.CART_ITEM_NOT_EXIST);
        }
        
        cartItem.setSelected(selected);
        this.updateById(cartItem);
    }
}
