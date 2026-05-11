package com.myy.bpds.itemservice.service.impl;

import com.myy.bpds.common.exception.BpdsException;
import com.myy.bpds.itemservice.constants.ItemErrorCode;
import com.myy.bpds.itemservice.entity.ItemEntity;
import com.myy.bpds.itemservice.mapper.ItemMapper;
import com.myy.bpds.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 商品Service实现类
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    @Override
    public void save(ItemEntity item) {
        // 校验商品名称是否重复
        if (StringUtils.hasText(item.getName())) {
            validateNameDuplicate(item.getName());
        }
        itemMapper.save(item);
    }

    @Override
    public void validateNameDuplicate(String name) {
        Long count = itemMapper.selectCount(w -> w.eq(ItemEntity::getName, name));
        if (count > 0) {
            throw new BpdsException(ItemErrorCode.ITEM_NAME_DUPLICATE);
        }
    }

    @Override
    public void saveBatch(List<ItemEntity> items) {
        itemMapper.saveBatch(items);
    }

    @Override
    public void removeById(Serializable id) {
        itemMapper.removeById(id);
    }

    @Override
    public void updateById(ItemEntity item) {
        // 校验商品名称是否重复（排除自身）
        if (StringUtils.hasText(item.getName()) && StringUtils.hasText(item.getId())) {
            validateNameDuplicateForUpdate(item.getName(), item.getId());
        }
        itemMapper.updateById(item);
    }

    @Override
    public void validateNameDuplicateForUpdate(String name, String excludeId) {
        boolean exists = itemMapper.exists(w -> w.eq(ItemEntity::getName, name).ne(ItemEntity::getId, excludeId));
        if (exists) {
            throw new BpdsException(ItemErrorCode.ITEM_NAME_DUPLICATE);
        }
    }

    @Override
    public ItemEntity getById(Serializable id) {
        return itemMapper.getById(id);
    }


    @Override
    public List<ItemEntity> listActiveItems() {
        return itemMapper.list(w -> w.eq(ItemEntity::getStatus, 1).orderByDesc(ItemEntity::getCreateTime));
    }

    @Override
    public List<ItemEntity> listByIds(List<String> ids) {
        return itemMapper.listByIds(ids);
    }
}
