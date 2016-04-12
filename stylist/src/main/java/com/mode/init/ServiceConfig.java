package com.mode.init;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.mode.util.TaskManager;

/**
 * Java annotation based configuration for thread pool and service beans (via @ComponentScan).
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"com.mode.service", "com.mode.security", "com.mode.scheduler"})
public class ServiceConfig implements SchedulingConfigurer {

    @Value("${thread.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${thread.corePoolSize}")
    private Integer corePoolSize;

    @Value("${thread.queueCapacity}")
    private Integer queueCapacity;

    @Value("${thread.keepAliveSeconds}")
    private Integer keepAliveSeconds;

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

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("stylist-commission-stats-task-");
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        TaskScheduler scheduler = this.taskScheduler();
        registrar.setTaskScheduler(scheduler);
    }

}
