-- 商品表
CREATE TABLE `t_item` (
    `id` VARCHAR(64) NOT NULL COMMENT '商品ID',
    `name` VARCHAR(255) NOT NULL COMMENT '商品名称',
    `description` TEXT COMMENT '商品描述',
    `price` DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
    `stock` INT NOT NULL DEFAULT 0 COMMENT '库存数量',
    `image_url` VARCHAR(500) COMMENT '商品图片URL',
    `category` VARCHAR(100) COMMENT '商品分类',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '商品状态: 0-下架, 1-上架',
    `creator` VARCHAR(64) COMMENT '创建人',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) COMMENT '更新人',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_category` (`category`),
    INDEX `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 购物车表
CREATE TABLE `t_cart` (
    `id` VARCHAR(64) NOT NULL COMMENT '购物车ID',
    `user_id` VARCHAR(64) NOT NULL COMMENT '用户ID',
    `creator` VARCHAR(64) COMMENT '创建人',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) COMMENT '更新人',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`),
    INDEX `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- 购物车项表
CREATE TABLE `t_cart_item` (
    `id` VARCHAR(64) NOT NULL COMMENT '购物车项ID',
    `cart_id` VARCHAR(64) NOT NULL COMMENT '购物车ID',
    `item_id` VARCHAR(64) NOT NULL COMMENT '商品ID',
    `quantity` INT NOT NULL DEFAULT 1 COMMENT '商品数量',
    `selected` TINYINT NOT NULL DEFAULT 1 COMMENT '是否选中: 0-未选中, 1-选中',
    `creator` VARCHAR(64) COMMENT '创建人',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) COMMENT '更新人',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_cart_item` (`cart_id`, `item_id`),
    INDEX `idx_cart_id` (`cart_id`),
    INDEX `idx_item_id` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车项表';
