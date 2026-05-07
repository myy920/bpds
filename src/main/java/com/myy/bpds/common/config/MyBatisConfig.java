package com.myy.bpds.common.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.myy.bpds.**.mapper")
public class MyBatisConfig {

}