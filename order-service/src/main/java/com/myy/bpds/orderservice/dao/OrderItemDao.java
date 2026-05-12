package com.myy.bpds.orderservice.dao;

import com.myy.bpds.common.mapper.LambdaBaseMapper;
import com.myy.bpds.orderservice.entity.OrderItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单明细Mapper
 */
public interface OrderItemDao extends LambdaBaseMapper<OrderItemEntity> {
}
