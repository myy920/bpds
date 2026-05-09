package com.myy.bpds.itemservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myy.bpds.itemservice.entity.ItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品Mapper
 */
@Mapper
public interface ItemMapper extends BaseMapper<ItemEntity> {
}