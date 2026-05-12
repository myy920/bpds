package com.myy.bpds.cartservice.service.impl;

import com.myy.bpds.cartservice.dao.CartItemDao;
import com.myy.bpds.cartservice.dto.AddCartItemDTO;
import com.myy.bpds.cartservice.entity.CartEntity;
import com.myy.bpds.cartservice.entity.CartItemEntity;
import com.myy.bpds.cartservice.service.CartItemService;
import com.myy.bpds.cartservice.service.CartService;
import com.myy.bpds.common.utils.BpdsContextHolder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 购物车项Service实现类
 */
@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartService cartService;
    private final CartItemDao cartItemDao;

    @Override
    @Transactional
    public void addToCart(String itemId, Integer quantity) {
        // 获取或创建购物车
        String userId = BpdsContextHolder.currentUserId();
        CartEntity cart = cartService.getOrCreateCart(userId);

        // 检查购物车中是否已存在该商品
        CartItemEntity cartItem = cartItemDao.getOne(
                ew -> ew.eq(CartItemEntity::getCartId, cart.getId()).eq(CartItemEntity::getItemId, itemId));

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemDao.updateById(cartItem);
        } else {
            cartItem = new CartItemEntity();
            cartItem.setCartId(cart.getId());
            cartItem.setItemId(itemId);
            cartItem.setQuantity(quantity);
            cartItem.setSelected(1);
            cartItemDao.save(cartItem);
        }
    }

    @Override
    @Transactional
    public void batchAddToCart(List<AddCartItemDTO> addCartItems) {
        String userId = BpdsContextHolder.currentUserId();
        CartEntity cart = cartService.getOrCreateCart(userId);
        List<CartItemEntity> dbCartItems = cartItemDao.list(ew -> ew.eq(CartItemEntity::getCartId, cart.getId()));
        Map<String, CartItemEntity> dbCartItemMap = ListUtils.emptyIfNull(dbCartItems).stream()
                .collect(Collectors.toMap(CartItemEntity::getItemId, a -> a, (v1, v2) -> v1));
        List<CartItemEntity> insertOrUpdates = addCartItems.stream().map(add -> {
            CartItemEntity db = dbCartItemMap.get(add.getItemId());
            if (db == null) {
                db = new CartItemEntity();
                db.setCartId(cart.getId());
                db.setItemId(add.getItemId());
                db.setQuantity(add.getQuantity());
                db.setSelected(1);
            } else {
                db.setQuantity(db.getQuantity() + add.getQuantity());
            }
            return db;
        }).toList();
        cartItemDao.saveOrUpdateBatch(insertOrUpdates);
    }
}
