package com.myy.bpds.itemservice.service.impl;

import com.myy.bpds.common.exception.BpdsException;
import com.myy.bpds.itemservice.constants.ItemErrorCode;
import com.myy.bpds.itemservice.dao.ItemDao;
import com.myy.bpds.itemservice.dto.StockDeductionRequest;
import com.myy.bpds.itemservice.entity.ItemEntity;
import com.myy.bpds.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 商品Service实现类
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    @Override
    public void save(ItemEntity item) {
        // 校验商品名称是否重复
        if (StringUtils.hasText(item.getName())) {
            validateNameDuplicate(item.getName());
        }
        itemDao.save(item);
    }

    @Override
    public void validateNameDuplicate(String name) {
        Long count = itemDao.selectCount(w -> w.eq(ItemEntity::getName, name));
        if (count > 0) {
            throw new BpdsException(ItemErrorCode.ITEM_NAME_DUPLICATE);
        }
    }

    @Override
    public void saveBatch(List<ItemEntity> items) {
        itemDao.saveBatch(items);
    }

    @Override
    public void removeById(Serializable id) {
        itemDao.removeById(id);
    }

    @Override
    public void updateById(ItemEntity item) {
        // 校验商品名称是否重复（排除自身）
        if (StringUtils.hasText(item.getName()) && StringUtils.hasText(item.getId())) {
            validateNameDuplicateForUpdate(item.getName(), item.getId());
        }
        itemDao.updateById(item);
    }

    @Override
    public void validateNameDuplicateForUpdate(String name, String excludeId) {
        boolean exists = itemDao.exists(w -> w.eq(ItemEntity::getName, name).ne(ItemEntity::getId, excludeId));
        if (exists) {
            throw new BpdsException(ItemErrorCode.ITEM_NAME_DUPLICATE);
        }
    }

    @Override
    public ItemEntity getById(Serializable id) {
        return itemDao.getById(id);
    }


    @Override
    public List<ItemEntity> listActiveItems() {
        return itemDao.list(w -> w.eq(ItemEntity::getStatus, 1).orderByDesc(ItemEntity::getCreateTime));
    }

    @Override
    public List<ItemEntity> listByIds(List<String> ids) {
        return ListUtils.emptyIfNull(itemDao.listByIds(ids));
    }

    @Override
    public void deductStock(String itemId, Integer quantity) {
        // 查询商品
        ItemEntity item = itemDao.getById(itemId);
        if (item == null) {
            throw new BpdsException(ItemErrorCode.ITEM_NOT_EXIST);
        }

        // 检查库存
        if (item.getStock() < quantity) {
            throw new BpdsException(ItemErrorCode.ITEM_STOCK_INSUFFICIENT);
        }

        // 扣减库存
        item.setStock(item.getStock() - quantity);
        itemDao.updateById(item);
    }

    @Override
    @Transactional
    public void batchDeductStock(List<StockDeductionRequest> requests) {
        // 首先验证所有商品是否存在且库存充足
        for (StockDeductionRequest request : requests) {
            // 查询商品
            ItemEntity item = itemDao.getById(request.getItemId());
            if (item == null) {
                throw new BpdsException(ItemErrorCode.ITEM_NOT_EXIST);
            }

            // 检查库存
            if (item.getStock() < request.getQuantity()) {
                throw new BpdsException(ItemErrorCode.ITEM_STOCK_INSUFFICIENT);
            }
        }

        // 验证通过后，执行扣减操作
        for (StockDeductionRequest request : requests) {
            ItemEntity item = itemDao.getById(request.getItemId());
            item.setStock(item.getStock() - request.getQuantity());
            itemDao.updateById(item);
        }
    }
}
