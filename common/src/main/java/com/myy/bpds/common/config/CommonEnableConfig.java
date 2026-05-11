package com.myy.bpds.common.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;


@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Import(CommonEnableConfig.CommonModuleRegistrar.class)
public class CommonEnableConfig {

    public static class CommonModuleRegistrar implements ImportBeanDefinitionRegistrar {
        @Override
        public void registerBeanDefinitions(@NotNull AnnotationMetadata importingClassMetadata,
                                            @NotNull BeanDefinitionRegistry registry) {
            ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
            scanner.scan("com.myy.bpds.common");
        }
    }
}