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
     * 校验商品名称是否重复（新增时）
     */
    void validateNameDuplicate(String name);

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
     * 校验商品名称是否重复（更新时，排除自身）
     */
    void validateNameDuplicateForUpdate(String name, String excludeId);

    /**
     * 查询商品详情
     */
    ItemEntity getById(Serializable id);

    /**
     * 查询所有上架商品
     */
    List<ItemEntity> listActiveItems();

    /**
     * 根据ID列表查询商品
     */
    List<ItemEntity> listByIds(List<String> ids);
}
