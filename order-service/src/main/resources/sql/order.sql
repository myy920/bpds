-- 订单表
CREATE TABLE `t_order`
(
    `id`           VARCHAR(64)    NOT NULL COMMENT '订单ID',
    `user_id`      VARCHAR(64)    NOT NULL COMMENT '用户ID',
    `order_no`     VARCHAR(64)    NOT NULL COMMENT '订单编号',
    `total_amount` DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT '订单总金额',
    `status`       TINYINT        NOT NULL DEFAULT 0 COMMENT '订单状态: 0-待支付, 1-已支付, 2-已取消, 3-已完成',
    `remark`       VARCHAR(500) COMMENT '备注',
    `creator`      VARCHAR(64) COMMENT '创建人',
    `create_time`  DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      VARCHAR(64) COMMENT '更新人',
    `update_time`  DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_create_time` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';

-- 订单明细表
CREATE TABLE `t_order_item`
(
    `id`          VARCHAR(64)    NOT NULL COMMENT '订单明细ID',
    `order_id`    VARCHAR(64)    NOT NULL COMMENT '订单ID',
    `item_id`     VARCHAR(64)    NOT NULL COMMENT '商品ID',
    `item_name`   VARCHAR(255)   NOT NULL COMMENT '商品名称',
    `item_price`  DECIMAL(10, 2) NOT NULL COMMENT '商品单价',
    `quantity`    INT            NOT NULL DEFAULT 1 COMMENT '购买数量',
    `subtotal`    DECIMAL(10, 2) NOT NULL COMMENT '小计金额',
    `creator`     VARCHAR(64) COMMENT '创建人',
    `create_time` DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     VARCHAR(64) COMMENT '更新人',
    `update_time` DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_order_id` (`order_id`),
    INDEX `idx_item_id` (`item_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单明细表';
