package com.myy.bpds.itemservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myy.bpds.itemservice.entity.ItemEntity;
import com.myy.bpds.itemservice.mapper.ItemMapper;
import com.myy.bpds.itemservice.service.ItemService;
import org.springframework.stereotype.Service;

/**
 * 商品Service实现类
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, ItemEntity> implements ItemService {
}