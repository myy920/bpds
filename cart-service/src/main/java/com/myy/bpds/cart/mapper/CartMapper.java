package com.myy.bpds.cart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myy.bpds.cart.entity.CartEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车Mapper
 */
@Mapper
public interface CartMapper extends BaseMapper<CartEntity> {
}