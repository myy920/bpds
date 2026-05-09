drop database if exists bpds;
create database bpds charset = utf8mb4 collate = utf8mb4_unicode_ci;
use bpds;

drop table if exists t_project;
create table t_project
(
    id          varchar(32)   not null primary key comment '项目ID',
    name        varchar(255)  not null comment '项目名称',
    description varchar(1024) null comment '项目描述',
    creator     varchar(32)   not null comment '创建人',
    create_time timestamp     not null default current_timestamp comment '创建时间',
    updater     varchar(32)   null comment '更新人',
    update_time timestamp     not null default current_timestamp comment '更新时间'
) comment '项目表';