package com.myy.bpds.itemservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myy.bpds.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品表
 *
 * @TableName t_item
 */
@TableName(value = "t_item")
@Data
public class ItemEntity extends BaseEntity {
    /**
     * 商品ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 商品名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 商品描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 商品价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 库存数量
     */
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 商品图片URL
     */
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 商品分类
     */
    @TableField(value = "category")
    private String category;

    /**
     * 商品状态: 0-下架, 1-上架
     */
    @TableField(value = "status")
    private Integer status;
}