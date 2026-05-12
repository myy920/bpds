package com.myy.bpds.orderservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myy.bpds.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单明细表
 *
 * @TableName t_order_item
 */
@TableName(value = "t_order_item")
@Data
public class OrderItemEntity extends BaseEntity {
    /**
     * 订单明细ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private String orderId;

    /**
     * 商品ID
     */
    @TableField(value = "item_id")
    private String itemId;

    /**
     * 商品名称
     */
    @TableField(value = "item_name")
    private String itemName;

    /**
     * 商品单价
     */
    @TableField(value = "item_price")
    private BigDecimal itemPrice;

    /**
     * 购买数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 小计金额
     */
    @TableField(value = "subtotal")
    private BigDecimal subtotal;
}
