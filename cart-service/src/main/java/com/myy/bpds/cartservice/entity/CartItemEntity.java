package com.myy.bpds.cartservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myy.bpds.common.entity.BaseEntity;
import lombok.Data;

/**
 * 购物车项表
 *
 * @TableName t_cart_item
 */
@TableName(value = "t_cart_item")
@Data
public class CartItemEntity extends BaseEntity {
    /**
     * 购物车项ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 购物车ID
     */
    @TableField(value = "cart_id")
    private String cartId;

    /**
     * 商品ID
     */
    @TableField(value = "item_id")
    private String itemId;

    /**
     * 商品数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 是否选中: 0-未选中, 1-选中
     */
    @TableField(value = "selected")
    private Integer selected;
}
