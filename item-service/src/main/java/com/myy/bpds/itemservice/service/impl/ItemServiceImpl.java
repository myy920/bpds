package com.myy.bpds.itemservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myy.bpds.itemservice.entity.ItemEntity;
import com.myy.bpds.itemservice.mapper.ItemMapper;
import com.myy.bpds.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        itemMapper.save(item);
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
        itemMapper.updateById(item);
    }

    @Override
    public ItemEntity getById(Serializable id) {
        return itemMapper.getById(id);
    }

    @Override
    public List<ItemEntity> list(LambdaQueryWrapper<ItemEntity> wrapper) {
        return itemMapper.list(wrapper);
    }

    @Override
    public List<ItemEntity> listActiveItems() {
        LambdaQueryWrapper<ItemEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ItemEntity::getStatus, 1)
                .orderByDesc(ItemEntity::getCreateTime);
        return itemMapper.list(wrapper);
    }

    @Override
    public List<ItemEntity> listByIds(List<String> ids) {
        return itemMapper.listByIds(ids);
    }
}
