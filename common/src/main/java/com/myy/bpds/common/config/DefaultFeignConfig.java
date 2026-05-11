package com.myy.bpds.common.config;

import com.myy.bpds.common.interceptor.FeignUserInfoInterceptor;
import feign.Logger;
import org.springframework.context.annotation.Bean;


public class DefaultFeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public FeignUserInfoInterceptor feignUserInfoInterceptor() {
        return new FeignUserInfoInterceptor();
    }
}
