package com.myy.bpds.orderservice.dao;

import com.myy.bpds.common.mapper.LambdaBaseMapper;
import com.myy.bpds.orderservice.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单Mapper
 */
public interface OrderDao extends LambdaBaseMapper<OrderEntity> {
}
