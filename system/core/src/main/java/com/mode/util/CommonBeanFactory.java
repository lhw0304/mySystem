package com.mode.util;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Common singleton object generate factory
 *
 * Created by zhaoweiwei on 16/3/24.
 */
@Configuration
public class CommonBeanFactory {

    @Value("${thread.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${thread.corePoolSize}")
    private Integer corePoolSize;

    @Value("${thread.queueCapacity}")
    private Integer queueCapacity;

    @Value("${thread.keepAliveSeconds}")
    private Integer keepAliveSeconds;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    // Inject task executor bean
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }

    // Inject task manager bean
    @Bean
    public TaskManager taskManager() {
        TaskManager taskManager = new TaskManager(taskExecutor());
        return taskManager;
    }
}
