package com.myy.bpds.orderservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myy.bpds.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单表
 *
 * @TableName t_order
 */
@TableName(value = "t_order")
@Data
public class OrderEntity extends BaseEntity {
    /**
     * 订单ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 订单总金额
     */
    @TableField(value = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 订单状态: 0-待支付, 1-已支付, 2-已取消, 3-已完成
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}
