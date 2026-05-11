-- 用户表
drop table if exists t_user;
CREATE TABLE IF NOT EXISTS `t_user`
(
    `id`          VARCHAR(64)  NOT NULL COMMENT '用户ID',
    `username`    VARCHAR(50)  NOT NULL COMMENT '用户名',
    `password`    VARCHAR(100) NOT NULL COMMENT '密码（加密）',
    `phone`       VARCHAR(20)  DEFAULT NULL COMMENT '手机号',
    `email`       VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `status`      TINYINT      DEFAULT 1 COMMENT '用户状态: 0-禁用, 1-正常',
    `creator`     VARCHAR(64)  DEFAULT NULL COMMENT '创建人',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     VARCHAR(64)  DEFAULT NULL COMMENT '更新人',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

