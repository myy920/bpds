package com.myy.bpds.cartservice.service.impl;

import com.myy.bpds.cartservice.client.ItemClient;
import com.myy.bpds.cartservice.dao.CartDao;
import com.myy.bpds.cartservice.dao.CartItemDao;
import com.myy.bpds.cartservice.dto.CartDTO;
import com.myy.bpds.cartservice.dto.CartItemDTO;
import com.myy.bpds.cartservice.entity.CartEntity;
import com.myy.bpds.cartservice.entity.CartItemEntity;
import com.myy.bpds.cartservice.service.CartService;
import com.myy.bpds.common.utils.BpdsContextHolder;
import com.myy.bpds.itemservice.entity.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 购物车Service实现类
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;

    private final ObjectFactory<CartServiceImpl> thatFactory;
    private final CartItemDao cartItemDao;
    private final ItemClient itemClient;

    @Override
    @Transactional
    public CartEntity getOrCreateCart(String userId) {
        // 查询用户是否已有购物车
        CartEntity cart = cartDao.getOne(ew -> ew.eq(CartEntity::getUserId, userId));

        if (cart != null) {
            return cart;
        }

        // 创建新购物车
        cart = new CartEntity();
        cart.setUserId(userId);
        cartDao.save(cart);

        return cart;
    }

    @Override
    public CartDTO queryUserCartDetails() {
        String userId = BpdsContextHolder.currentUserId();
        CartEntity cart = getOrCreateCart(userId);
        List<CartItemEntity> cartItems = cartItemDao.list(ew -> ew.eq(CartItemEntity::getCartId, cart.getId()));
        List<String> itemIds = cartItems.stream().map(CartItemEntity::getItemId).toList();
        List<ItemEntity> items = List.of();
        if (CollectionUtils.isNotEmpty(itemIds)) {
            items = itemClient.getItem(itemIds).getData();
        }
        Map<String, ItemEntity> itemMap = items.stream().collect(Collectors.toMap(ItemEntity::getId, item -> item));
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCart(cart);
        cartDTO.setItems(cartItems.stream().map(cartItem -> {
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setCartItem(cartItem);
            itemDTO.setItem(itemMap.get(cartItem.getItemId()));
            return itemDTO;
        }).toList());
        return cartDTO;
    }

    @Override
    @Transactional
    public void clearCart() {
        String userId = BpdsContextHolder.currentUserId();
        CartEntity cart = thatFactory.getObject().getOrCreateCart(userId);
        // 删除购物车中的所有商品项
        cartItemDao.remove(ew -> ew.eq(CartItemEntity::getCartId, cart.getId()));
    }
}