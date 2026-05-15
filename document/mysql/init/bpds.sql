-- MySQL dump 10.13  Distrib 5.7.44, for Win64 (x86_64)
--
-- Host: 192.168.11.130    Database: bpds
-- ------------------------------------------------------
-- Server version	8.0.45-0ubuntu0.24.04.1
drop database if exists bpds;
create database bpds character set utf8mb4 collate utf8mb4_unicode_ci;
use bpds;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `a`
--

DROP TABLE IF EXISTS `a`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `a`
(
    `id`   int NOT NULL,
    `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a`
--

LOCK TABLES `a` WRITE;
/*!40000 ALTER TABLE `a`
    DISABLE KEYS */;
INSERT INTO `a`
VALUES (1, 'a1'),
       (2, 'a2'),
       (3, 'a3');
/*!40000 ALTER TABLE `a`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b`
--

DROP TABLE IF EXISTS `b`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b`
(
    `id`   int NOT NULL,
    `aid`  int                                     DEFAULT NULL,
    `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b`
--

LOCK TABLES `b` WRITE;
/*!40000 ALTER TABLE `b`
    DISABLE KEYS */;
INSERT INTO `b`
VALUES (1, 1, 'b1'),
       (2, 1, 'b2'),
       (3, 4, 'b3'),
       (4, 5, 'b4'),
       (5, 5, 'b5');
/*!40000 ALTER TABLE `b`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_cart`
--

DROP TABLE IF EXISTS `t_cart`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cart`
(
    `id`          varchar(64) NOT NULL COMMENT '购物车ID',
    `user_id`     varchar(64) NOT NULL COMMENT '用户ID',
    `creator`     varchar(64)          DEFAULT NULL COMMENT '创建人',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64)          DEFAULT NULL COMMENT '更新人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='购物车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_cart`
--

LOCK TABLES `t_cart` WRITE;
/*!40000 ALTER TABLE `t_cart`
    DISABLE KEYS */;
INSERT INTO `t_cart`
VALUES ('2053401166978371586', 'myy', 'myy', '2026-05-10 17:06:03', 'myy', '2026-05-10 17:06:03'),
       ('2053728350582501377', 'System', 'System', '2026-05-11 14:46:10', 'System', '2026-05-11 14:46:10'),
       ('2053849557273309186', '2053848607234088962', '2053848607234088962', '2026-05-11 22:47:48',
        '2053848607234088962', '2026-05-11 22:47:48'),
       ('2053850579240628226', '2053850479344906242', '2053850479344906242', '2026-05-11 22:51:51',
        '2053850479344906242', '2026-05-11 22:51:51');
/*!40000 ALTER TABLE `t_cart`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_cart_item`
--

DROP TABLE IF EXISTS `t_cart_item`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cart_item`
(
    `id`          varchar(64) NOT NULL COMMENT '购物车项ID',
    `cart_id`     varchar(64) NOT NULL COMMENT '购物车ID',
    `item_id`     varchar(64) NOT NULL COMMENT '商品ID',
    `quantity`    int         NOT NULL DEFAULT '1' COMMENT '商品数量',
    `selected`    tinyint     NOT NULL DEFAULT '1' COMMENT '是否选中: 0-未选中, 1-选中',
    `creator`     varchar(64)          DEFAULT NULL COMMENT '创建人',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64)          DEFAULT NULL COMMENT '更新人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_cart_item` (`cart_id`, `item_id`),
    KEY `idx_cart_id` (`cart_id`),
    KEY `idx_item_id` (`item_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='购物车项表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_cart_item`
--

LOCK TABLES `t_cart_item` WRITE;
/*!40000 ALTER TABLE `t_cart_item`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `t_cart_item`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_item`
--

DROP TABLE IF EXISTS `t_item`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_item`
(
    `id`          varchar(64)    NOT NULL COMMENT '商品ID',
    `name`        varchar(255)   NOT NULL COMMENT '商品名称',
    `description` text COMMENT '商品描述',
    `price`       decimal(10, 2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
    `stock`       int            NOT NULL DEFAULT '0' COMMENT '库存数量',
    `image_url`   varchar(500)            DEFAULT NULL COMMENT '商品图片URL',
    `category`    varchar(100)            DEFAULT NULL COMMENT '商品分类',
    `status`      tinyint        NOT NULL DEFAULT '1' COMMENT '商品状态: 0-下架, 1-上架',
    `creator`     varchar(64)             DEFAULT NULL COMMENT '创建人',
    `create_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64)             DEFAULT NULL COMMENT '更新人',
    `update_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_category` (`category`),
    KEY `idx_status` (`status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_item`
--

LOCK TABLES `t_item` WRITE;
/*!40000 ALTER TABLE `t_item`
    DISABLE KEYS */;
INSERT INTO `t_item`
VALUES ('2053405661103828993', 'iPhone 15 Pro', 'Apple iPhone 15 Pro 256GB 钛金属设计 A17 Pro芯片', 8999.00, 858,
        'https://example.com/images/iphone15pro.jpg', '手机数码', 1, 'myy', '2026-05-10 17:23:55', 'myy',
        '2026-05-10 17:23:55'),
       ('2053405661221269505', 'MacBook Pro 14英寸', 'Apple MacBook Pro 14英寸 M3 Pro芯片 18GB内存 512GB存储', 14999.00,
        27, 'https://example.com/images/macbook-pro-14.jpg', '电脑办公', 1, 'myy', '2026-05-10 17:23:55', 'myy',
        '2026-05-10 17:23:55'),
       ('2053405661221269506', 'AirPods Pro 2', 'Apple AirPods Pro 第二代 主动降噪 自适应透明模式', 1899.00, 956,
        'https://example.com/images/airpods-pro-2.jpg', '影音娱乐', 1, 'myy', '2026-05-10 17:23:55', 'myy',
        '2026-05-10 17:23:55'),
       ('2053405661221269507', 'iPad Air 5', 'Apple iPad Air 5 10.9英寸 M1芯片 64GB WiFi版', 4799.00, 909,
        'https://example.com/images/ipad-air-5.jpg', '平板电脑', 1, 'myy', '2026-05-10 17:23:55', 'myy',
        '2026-05-10 17:23:55'),
       ('2053405661221269508', 'Sony WH-1000XM5', '索尼 WH-1000XM5 无线降噪耳机 30小时续航', 2499.00, 840,
        'https://example.com/images/sony-wh1000xm5.jpg', '影音娱乐', 1, 'myy', '2026-05-10 17:23:55', 'myy',
        '2026-05-10 17:23:55'),
       ('2053405661221269509', '小米14 Ultra', '小米14 Ultra 徕卡光学镜头 骁龙8 Gen3 16GB+512GB', 6499.00, 880,
        'https://example.com/images/mi14-ultra.jpg', '手机数码', 1, 'myy', '2026-05-10 17:23:55', 'myy',
        '2026-05-10 17:23:55'),
       ('2053405661221269510', '华为MateBook X Pro', '华为 MateBook X Pro 14.2英寸 酷睿Ultra 32GB 1TB', 11999.00, 820,
        'https://example.com/images/matebook-x-pro.jpg', '电脑办公', 1, 'myy', '2026-05-10 17:23:55', 'myy',
        '2026-05-10 17:23:55'),
       ('2053405661221269511', 'Switch OLED游戏机', 'Nintendo Switch OLED型号 7英寸屏幕 64GB存储', 2599.00, 850,
        'https://example.com/images/switch-oled.jpg', '游戏设备', 1, 'myy', '2026-05-10 17:23:55', 'myy',
        '2026-05-10 17:23:55'),
       ('2053405661221269512', '戴森V15吸尘器', 'Dyson V15 Detect 智能无绳吸尘器 LCD显示屏', 4990.00, 830,
        'https://example.com/images/dyson-v15.jpg', '家用电器', 1, 'myy', '2026-05-10 17:23:55', 'myy',
        '2026-05-10 17:23:55'),
       ('2053405661221269513', 'Kindle Paperwhite 5', '亚马逊 Kindle Paperwhite 5 6.8英寸 防水电子书阅读器', 1099.00,
        280, 'https://example.com/images/kindle-pw5.jpg', '电子阅读', 1, 'myy', '2026-05-10 17:23:55', 'myy',
        '2026-05-10 17:23:55'),
       ('2053732476624232450', 'iPhone 15 ProMax', 'Apple iPhone 15 Pro 256GB 钛金属', 8999.00, 80,
        'https://example.com/images/iphone15pro.jpg', '手机数码', 1, 'myy', '2026-05-11 15:02:34', 'myy',
        '2026-05-11 15:02:34');
/*!40000 ALTER TABLE `t_item`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order`
(
    `id`           varchar(64)    NOT NULL COMMENT '订单ID',
    `user_id`      varchar(64)    NOT NULL COMMENT '用户ID',
    `order_no`     varchar(64)    NOT NULL COMMENT '订单编号',
    `total_amount` decimal(10, 2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `status`       tinyint        NOT NULL DEFAULT '0' COMMENT '订单状态: 0-待支付, 1-已支付, 2-已取消, 3-已完成',
    `remark`       varchar(500)            DEFAULT NULL COMMENT '备注',
    `creator`      varchar(64)             DEFAULT NULL COMMENT '创建人',
    `create_time`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64)             DEFAULT NULL COMMENT '更新人',
    `update_time`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_create_time` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order`
    DISABLE KEYS */;
INSERT INTO `t_order`
VALUES ('2054137719237267457', 'myy', 'ORD20260512175250749370', 75390.00, 0, '买单', 'myy', '2026-05-12 17:52:51',
        'myy', '2026-05-12 17:52:51'),
       ('2054137880835411970', 'myy', 'ORD20260512175323170010', 75390.00, 0, '买单', 'myy', '2026-05-12 17:53:29',
        'myy', '2026-05-12 17:53:29'),
       ('2054138528041676801', 'myy', 'ORD20260512175603579249', 75390.00, 0, '买单', 'myy', '2026-05-12 17:56:04',
        'myy', '2026-05-12 17:56:04'),
       ('2054179090534932482', 'myy', 'ORD20260512203714617134', 3262610.00, 0, '123', 'myy', '2026-05-12 20:37:15',
        'myy', '2026-05-12 20:37:15'),
       ('2054179167773040641', 'myy', 'ORD20260512203733850706', 1505820.00, 0, '123', 'myy', '2026-05-12 20:37:33',
        'myy', '2026-05-12 20:37:33'),
       ('2054181361956036609', 'myy', 'ORD20260512204616468805', 250970.00, 0, '123', 'myy', '2026-05-12 20:46:16',
        'myy', '2026-05-12 20:46:16'),
       ('2054222196206678018', 'myy', 'ORD20260512232831825137', 1254850.00, 0, '333', 'myy', '2026-05-12 23:28:32',
        'myy', '2026-05-12 23:28:32'),
       ('2054222286866558977', 'myy', 'ORD20260512232853295328', 2007760.00, 0, '333', 'myy', '2026-05-12 23:28:53',
        'myy', '2026-05-12 23:28:53'),
       ('2054222343732932609', 'myy', 'ORD20260512232907294835', 2760670.00, 0, '333', 'myy', '2026-05-12 23:29:07',
        'myy', '2026-05-12 23:29:07'),
       ('2054222673036128258', 'myy', 'ORD20260512233025296252', 1003880.00, 0, '333', 'myy', '2026-05-12 23:30:26',
        'myy', '2026-05-12 23:30:26'),
       ('2054222760814522369', 'myy', 'ORD20260512233046890742', 752910.00, 0, '333', 'myy', '2026-05-12 23:30:46',
        'myy', '2026-05-12 23:30:46'),
       ('2054223037764419586', 'myy', 'ORD20260512233152351689', 250970.00, 0, '333', 'myy', '2026-05-12 23:31:52',
        'myy', '2026-05-12 23:31:52'),
       ('2054224171736449025', 'myy', 'ORD20260512233622324666', 250970.00, 0, '333', 'myy', '2026-05-12 23:36:23',
        'myy', '2026-05-12 23:36:23'),
       ('2054225293867958274', 'myy', 'ORD20260512234050469179', 250970.00, 0, '333', 'myy', '2026-05-12 23:40:50',
        'myy', '2026-05-12 23:40:50'),
       ('2054226514288787458', 'myy', 'ORD20260512234541139489', 250970.00, 0, '333', 'myy', '2026-05-12 23:45:41',
        'myy', '2026-05-12 23:45:41'),
       ('2054227598415646722', 'myy', 'ORD20260512234959377567', 250970.00, 0, '333', 'myy', '2026-05-12 23:50:00',
        'myy', '2026-05-12 23:50:00'),
       ('2054231707680116738', 'myy', 'ORD20260513000619867002', 501940.00, 0, '333', 'myy', '2026-05-13 00:06:20',
        'myy', '2026-05-13 00:06:20'),
       ('2054232031673360385', 'myy', 'ORD20260513000736886488', 5772310.00, 0, '333', 'myy', '2026-05-13 00:07:37',
        'myy', '2026-05-13 00:07:37'),
       ('2054232080486670337', 'myy', 'ORD20260513000748861477', 4015520.00, 0, '333', 'myy', '2026-05-13 00:07:48',
        'myy', '2026-05-13 00:07:48'),
       ('2054232318412759041', 'myy', 'ORD20260513000845652789', 6776190.00, 0, '333', 'myy', '2026-05-13 00:08:45',
        'myy', '2026-05-13 00:08:45');
/*!40000 ALTER TABLE `t_order`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order_item`
--

DROP TABLE IF EXISTS `t_order_item`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_item`
(
    `id`          varchar(64)    NOT NULL COMMENT '订单明细ID',
    `order_id`    varchar(64)    NOT NULL COMMENT '订单ID',
    `item_id`     varchar(64)    NOT NULL COMMENT '商品ID',
    `item_name`   varchar(255)   NOT NULL COMMENT '商品名称',
    `item_price`  decimal(10, 2) NOT NULL COMMENT '商品单价',
    `quantity`    int            NOT NULL DEFAULT '1' COMMENT '购买数量',
    `subtotal`    decimal(10, 2) NOT NULL COMMENT '小计金额',
    `creator`     varchar(64)             DEFAULT NULL COMMENT '创建人',
    `create_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64)             DEFAULT NULL COMMENT '更新人',
    `update_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_item_id` (`item_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='订单明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_item`
--

LOCK TABLES `t_order_item` WRITE;
/*!40000 ALTER TABLE `t_order_item`
    DISABLE KEYS */;
INSERT INTO `t_order_item`
VALUES ('2054138528230420482', '2054138528041676801', '2053405661103828993', 'iPhone 15 Pro', 8999.00, 2, 17998.00,
        'myy', '2026-05-12 17:56:04', 'myy', '2026-05-12 17:56:04'),
       ('2054138528238809089', '2054138528041676801', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 3,
        44997.00, 'myy', '2026-05-12 17:56:04', 'myy', '2026-05-12 17:56:04'),
       ('2054138528238809090', '2054138528041676801', '2053405661221269506', 'AirPods Pro 2', 1899.00, 4, 7596.00,
        'myy', '2026-05-12 17:56:04', 'myy', '2026-05-12 17:56:04'),
       ('2054138528247197698', '2054138528041676801', '2053405661221269507', 'iPad Air 5', 4799.00, 1, 4799.00, 'myy',
        '2026-05-12 17:56:04', 'myy', '2026-05-12 17:56:04'),
       ('2054179090719481857', '2054179090534932482', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 130,
        1949870.00, 'myy', '2026-05-12 20:37:15', 'myy', '2026-05-12 20:37:15'),
       ('2054179090736259073', '2054179090534932482', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 130,
        142870.00, 'myy', '2026-05-12 20:37:15', 'myy', '2026-05-12 20:37:15'),
       ('2054179090740453378', '2054179090534932482', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 130,
        1169870.00, 'myy', '2026-05-12 20:37:15', 'myy', '2026-05-12 20:37:15'),
       ('2054179167802400769', '2054179167773040641', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 60,
        899940.00, 'myy', '2026-05-12 20:37:33', 'myy', '2026-05-12 20:37:33'),
       ('2054179167802400770', '2054179167773040641', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 60,
        65940.00, 'myy', '2026-05-12 20:37:33', 'myy', '2026-05-12 20:37:33'),
       ('2054179167806595074', '2054179167773040641', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 60, 539940.00,
        'myy', '2026-05-12 20:37:33', 'myy', '2026-05-12 20:37:33'),
       ('2054181362023145474', '2054181361956036609', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 10,
        149990.00, 'myy', '2026-05-12 20:46:16', 'myy', '2026-05-12 20:46:16'),
       ('2054181362081865730', '2054181361956036609', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 10,
        10990.00, 'myy', '2026-05-12 20:46:16', 'myy', '2026-05-12 20:46:16'),
       ('2054181362081865731', '2054181361956036609', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 10, 89990.00,
        'myy', '2026-05-12 20:46:16', 'myy', '2026-05-12 20:46:16'),
       ('2054222197766959105', '2054222196206678018', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 50,
        749950.00, 'myy', '2026-05-12 23:28:32', 'myy', '2026-05-12 23:28:32'),
       ('2054222197842456577', '2054222196206678018', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 50,
        54950.00, 'myy', '2026-05-12 23:28:32', 'myy', '2026-05-12 23:28:32'),
       ('2054222197842456578', '2054222196206678018', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 50, 449950.00,
        'myy', '2026-05-12 23:28:32', 'myy', '2026-05-12 23:28:32'),
       ('2054222286933667842', '2054222286866558977', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 80,
        1199920.00, 'myy', '2026-05-12 23:28:53', 'myy', '2026-05-12 23:28:53'),
       ('2054222286933667843', '2054222286866558977', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 80,
        87920.00, 'myy', '2026-05-12 23:28:53', 'myy', '2026-05-12 23:28:53'),
       ('2054222286933667844', '2054222286866558977', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 80, 719920.00,
        'myy', '2026-05-12 23:28:53', 'myy', '2026-05-12 23:28:53'),
       ('2054222343783264258', '2054222343732932609', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 110,
        1649890.00, 'myy', '2026-05-12 23:29:07', 'myy', '2026-05-12 23:29:07'),
       ('2054222343787458561', '2054222343732932609', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 110,
        120890.00, 'myy', '2026-05-12 23:29:07', 'myy', '2026-05-12 23:29:07'),
       ('2054222343795847170', '2054222343732932609', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 110,
        989890.00, 'myy', '2026-05-12 23:29:07', 'myy', '2026-05-12 23:29:07'),
       ('2054222673317146626', '2054222673036128258', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 40,
        599960.00, 'myy', '2026-05-12 23:30:26', 'myy', '2026-05-12 23:30:26'),
       ('2054222673359089666', '2054222673036128258', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 40,
        43960.00, 'myy', '2026-05-12 23:30:26', 'myy', '2026-05-12 23:30:26'),
       ('2054222673375866881', '2054222673036128258', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 40, 359960.00,
        'myy', '2026-05-12 23:30:26', 'myy', '2026-05-12 23:30:26'),
       ('2054222760839688193', '2054222760814522369', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 30,
        449970.00, 'myy', '2026-05-12 23:30:46', 'myy', '2026-05-12 23:30:46'),
       ('2054222760839688194', '2054222760814522369', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 30,
        32970.00, 'myy', '2026-05-12 23:30:46', 'myy', '2026-05-12 23:30:46'),
       ('2054222760839688195', '2054222760814522369', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 30, 269970.00,
        'myy', '2026-05-12 23:30:46', 'myy', '2026-05-12 23:30:46'),
       ('2054223039010127874', '2054223037764419586', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 10,
        149990.00, 'myy', '2026-05-12 23:31:53', 'myy', '2026-05-12 23:31:53'),
       ('2054223039056265217', '2054223037764419586', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 10,
        10990.00, 'myy', '2026-05-12 23:31:53', 'myy', '2026-05-12 23:31:53'),
       ('2054223039060459521', '2054223037764419586', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 10, 89990.00,
        'myy', '2026-05-12 23:31:53', 'myy', '2026-05-12 23:31:53'),
       ('2054224171799363585', '2054224171736449025', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 10,
        149990.00, 'myy', '2026-05-12 23:36:23', 'myy', '2026-05-12 23:36:23'),
       ('2054224171799363586', '2054224171736449025', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 10,
        10990.00, 'myy', '2026-05-12 23:36:23', 'myy', '2026-05-12 23:36:23'),
       ('2054224171799363587', '2054224171736449025', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 10, 89990.00,
        'myy', '2026-05-12 23:36:23', 'myy', '2026-05-12 23:36:23'),
       ('2054225293930872833', '2054225293867958274', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 10,
        149990.00, 'myy', '2026-05-12 23:40:50', 'myy', '2026-05-12 23:40:50'),
       ('2054225293930872834', '2054225293867958274', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 10,
        10990.00, 'myy', '2026-05-12 23:40:50', 'myy', '2026-05-12 23:40:50'),
       ('2054225293930872835', '2054225293867958274', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 10, 89990.00,
        'myy', '2026-05-12 23:40:50', 'myy', '2026-05-12 23:40:50'),
       ('2054226514397839361', '2054226514288787458', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 10,
        149990.00, 'myy', '2026-05-12 23:45:41', 'myy', '2026-05-12 23:45:41'),
       ('2054226514397839362', '2054226514288787458', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 10,
        10990.00, 'myy', '2026-05-12 23:45:41', 'myy', '2026-05-12 23:45:41'),
       ('2054226514397839363', '2054226514288787458', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 10, 89990.00,
        'myy', '2026-05-12 23:45:41', 'myy', '2026-05-12 23:45:41'),
       ('2054227599648772098', '2054227598415646722', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 10,
        149990.00, 'myy', '2026-05-12 23:50:00', 'myy', '2026-05-12 23:50:00'),
       ('2054227599724269569', '2054227598415646722', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 10,
        10990.00, 'myy', '2026-05-12 23:50:00', 'myy', '2026-05-12 23:50:00'),
       ('2054227599724269570', '2054227598415646722', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 10, 89990.00,
        'myy', '2026-05-12 23:50:00', 'myy', '2026-05-12 23:50:00'),
       ('2054231707684311042', '2054231707680116738', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 20,
        299980.00, 'myy', '2026-05-13 00:06:20', 'myy', '2026-05-13 00:06:20'),
       ('2054231707684311043', '2054231707680116738', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 20,
        21980.00, 'myy', '2026-05-13 00:06:20', 'myy', '2026-05-13 00:06:20'),
       ('2054231707684311044', '2054231707680116738', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 20, 179980.00,
        'myy', '2026-05-13 00:06:20', 'myy', '2026-05-13 00:06:20'),
       ('2054232033053286401', '2054232031673360385', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 230,
        3449770.00, 'myy', '2026-05-13 00:07:37', 'myy', '2026-05-13 00:07:37'),
       ('2054232033053286402', '2054232031673360385', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 230,
        252770.00, 'myy', '2026-05-13 00:07:37', 'myy', '2026-05-13 00:07:37'),
       ('2054232033053286403', '2054232031673360385', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 230,
        2069770.00, 'myy', '2026-05-13 00:07:37', 'myy', '2026-05-13 00:07:37'),
       ('2054232080574750721', '2054232080486670337', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 160,
        2399840.00, 'myy', '2026-05-13 00:07:48', 'myy', '2026-05-13 00:07:48'),
       ('2054232080578945025', '2054232080486670337', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 160,
        175840.00, 'myy', '2026-05-13 00:07:48', 'myy', '2026-05-13 00:07:48'),
       ('2054232080578945026', '2054232080486670337', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 160,
        1439840.00, 'myy', '2026-05-13 00:07:48', 'myy', '2026-05-13 00:07:48'),
       ('2054232318471479298', '2054232318412759041', '2053405661221269505', 'MacBook Pro 14英寸', 14999.00, 270,
        4049730.00, 'myy', '2026-05-13 00:08:45', 'myy', '2026-05-13 00:08:45'),
       ('2054232318471479299', '2054232318412759041', '2053405661221269513', 'Kindle Paperwhite 5', 1099.00, 270,
        296730.00, 'myy', '2026-05-13 00:08:45', 'myy', '2026-05-13 00:08:45'),
       ('2054232318471479300', '2054232318412759041', '2053732476624232450', 'iPhone 15 ProMax', 8999.00, 270,
        2429730.00, 'myy', '2026-05-13 00:08:45', 'myy', '2026-05-13 00:08:45');
/*!40000 ALTER TABLE `t_order_item`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_project`
--

DROP TABLE IF EXISTS `t_project`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_project`
(
    `id`          varchar(32) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '项目ID',
    `name`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目名称',
    `description` varchar(1024) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '项目描述',
    `creator`     varchar(32) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '创建人',
    `create_time` timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(32) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '更新人',
    `update_time` timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_project`
--

LOCK TABLES `t_project` WRITE;
/*!40000 ALTER TABLE `t_project`
    DISABLE KEYS */;
INSERT INTO `t_project`
VALUES ('2052675777788248065', '深海信服V01', '深海信服项目', 'myy', '2026-05-08 17:03:37', 'myy',
        '2026-05-08 17:03:37'),
       ('2052675876933206017', '深海信服V02', '深海信服项目66', 'myy', '2026-05-08 17:04:01', 'myy',
        '2026-05-08 17:32:28'),
       ('2052676701382479873', '深海信服V03', '深海信服项目', 'myy', '2026-05-08 17:07:17', 'myy',
        '2026-05-08 17:07:17'),
       ('2052681928835796993', '深海信服V03', '深海信服项目', 'myy', '2026-05-08 17:28:03', 'myy',
        '2026-05-08 17:28:03'),
       ('2052681938545610753', '深海信服V03', '深海信服项目', 'myy', '2026-05-08 17:28:06', 'myy',
        '2026-05-08 17:28:06'),
       ('2052681956363014146', '深海信服V03', '深海信服项目', 'myy', '2026-05-08 17:28:10', 'myy',
        '2026-05-08 17:28:10'),
       ('2052681981608529922', '深海信服V03', '深海信服项目', 'myy', '2026-05-08 17:28:16', 'myy',
        '2026-05-08 17:28:16'),
       ('2053400942306344961', '123', '66', 'myy', '2026-05-10 17:05:10', 'myy', '2026-05-10 17:05:10');
/*!40000 ALTER TABLE `t_project`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user`
(
    `id`          varchar(64)  NOT NULL COMMENT '用户ID',
    `username`    varchar(50)  NOT NULL COMMENT '用户名',
    `password`    varchar(100) NOT NULL COMMENT '密码（加密）',
    `phone`       varchar(20)  DEFAULT NULL COMMENT '手机号',
    `email`       varchar(100) DEFAULT NULL COMMENT '邮箱',
    `status`      tinyint      DEFAULT '1' COMMENT '用户状态: 0-禁用, 1-正常',
    `creator`     varchar(64)  DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64)  DEFAULT NULL COMMENT '更新人',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user`
    DISABLE KEYS */;
INSERT INTO `t_user`
VALUES ('2053776823797366786', 'myy', '5bbb16ac99f636dfac36a7c644732c88', NULL, NULL, 1, 'myy', '2026-05-11 17:58:47',
        'myy', '2026-05-11 17:58:47'),
       ('2053777840064643073', 'admin', '21232f297a57a5a743894a0e4a801fc3', NULL, NULL, 1, 'myy', '2026-05-11 18:02:49',
        'myy', '2026-05-11 18:02:49'),
       ('2053848607234088962', 'jojo', '7510d498f23f5815d3376ea7bad64e29', NULL, NULL, 1, 'System',
        '2026-05-11 22:44:01', 'System', '2026-05-11 22:44:01'),
       ('2053850479344906242', 'jojo1', '229fe1d3de6fec10d1946dca7a15289c', NULL, NULL, 1, 'System',
        '2026-05-11 22:51:28', 'System', '2026-05-11 22:51:28');
/*!40000 ALTER TABLE `t_user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `undo_log`
(
    `branch_id`     bigint       NOT NULL COMMENT '分支事务ID',
    `xid`           varchar(128) NOT NULL COMMENT '全局事务ID',
    `context`       varchar(128) NOT NULL COMMENT '上下文',
    `rollback_info` longblob     NOT NULL COMMENT '回滚信息',
    `log_status`    int          NOT NULL COMMENT '状态：0正常，1全局已完成',
    `log_created`   datetime(6)  NOT NULL COMMENT '创建时间',
    `log_modified`  datetime(6)  NOT NULL COMMENT '修改时间',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='AT模式回滚日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2026-05-15 17:33:49
