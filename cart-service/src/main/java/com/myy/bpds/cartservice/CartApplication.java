package com.myy.bpds.cartservice;

import com.myy.bpds.common.config.DefaultFeignConfig;
import com.myy.bpds.common.config.MyBatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "com.myy.bpds.cartservice.dao")
@EnableFeignClients(defaultConfiguration = DefaultFeignConfig.class)
public class CartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CartApplication.class, args);
        Object bean = context.getBean(
                MyBatisPlusConfig.class);
        System.out.println(bean);
    }
}
