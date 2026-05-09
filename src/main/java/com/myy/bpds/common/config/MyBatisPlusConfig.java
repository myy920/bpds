package com.myy.bpds.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.myy.bpds.common.utils.BpdsContextHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * MyBatis-Plus 配置类
 */
@Configuration
@MapperScan(basePackages = "com.myy.bpds.**.mapper")
public class MyBatisPlusConfig {
    // MyBatis-Plus 3.5.12+ 会自动配置分页插件，无需手动配置
    // 如需自定义配置，可在 application.yml 中配置

    @Component
    public static class AutoFillMetaObjectHandler implements MetaObjectHandler {

        @Override
        public void insertFill(MetaObject metaObject) {
            String currentUserId = BpdsContextHolder.currentUserId();
            LocalDateTime now = LocalDateTime.now();
            strictInsertFill(metaObject, "creator", String.class, currentUserId);
            strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);
            strictInsertFill(metaObject, "updater", String.class, currentUserId);
            strictInsertFill(metaObject, "updateTime", LocalDateTime.class, now);

        }

        public static void main(String[] args) {

            HashSet set = new HashSet() ;
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                iterator.remove();
            }
            Arrays.setAll();
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            String currentUserId = BpdsContextHolder.currentUserId();
            LocalDateTime now = LocalDateTime.now();
            strictUpdateFill(metaObject, "updater", String.class, currentUserId);
            strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, now);
        }
    }
}