package com.myy.bpds.itemservice.mapper;

import com.myy.bpds.common.mapper.BaseMapperPlus3;
import com.myy.bpds.itemservice.entity.ItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品Mapper
 */
@Mapper
public interface ItemMapper extends BaseMapperPlus3<ItemEntity> {
}