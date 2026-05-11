package com.myy.bpds.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CommonEnableConfig {
    // 这个类作为 common 模块配置的总开关
    // 其他配置类通过 @ConditionalOnBean(CommonEnableConfig.class) 依赖此类的存在
}