package com.myy.bpds.itemservice.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myy.bpds.itemservice.entity.ItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 商品Service
 */
public interface ItemService {
    /**
     * 新增商品
     */
    void save(ItemEntity item);

    /**
     * 批量新增商品
     */
    void saveBatch(List<ItemEntity> items);

    /**
     * 删除商品
     */
    void removeById(Serializable id);

    /**
     * 更新商品
     */
    void updateById(ItemEntity item);

    /**
     * 查询商品详情
     */
    ItemEntity getById(Serializable id);

    /**
     * 查询商品列表
     */
    List<ItemEntity> list(LambdaQueryWrapper<ItemEntity> wrapper);

    /**
     * 查询所有上架商品
     */
    List<ItemEntity> listActiveItems();

    /**
     * 根据ID列表查询商品
     */
    List<ItemEntity> listByIds(List<String> ids);
}
