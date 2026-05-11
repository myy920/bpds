package com.myy.bpds.common.config;

import com.myy.bpds.common.dto.BpdsContext;
import com.myy.bpds.common.utils.BpdsContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@ConditionalOnBean(CommonEnableConfig.class)
@EnableAsync
public class AsyncConfig {
    public static final String DEFAULT = "ASYNC_DEFAULT";

    @Bean
    public TaskDecorator taskDecorator() {
        return runnable -> {
            // 在主线程（任务提交时）捕获当前上下文
            BpdsContext context = BpdsContextHolder.get();
            return () -> {
                try {
                    // 在异步线程中还原上下文
                    BpdsContextHolder.set(context);
                    runnable.run();
                } finally {
                    // 清理上下文，避免内存泄漏和线程复用时的数据污染
                    BpdsContextHolder.clear();
                }
            };
        };
    }


    // 配置异步线程池
    @Bean(name = DEFAULT)
    public ThreadPoolTaskExecutor taskExecutor(TaskDecorator taskDecorator) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int cpu = Runtime.getRuntime().availableProcessors() + 1;
        executor.setCorePoolSize(cpu);
        executor.setMaxPoolSize(cpu * 2);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(1000);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.setThreadNamePrefix(DEFAULT + '-');
        executor.setTaskDecorator(taskDecorator);
        executor.initialize();
        return executor;
    }
}
