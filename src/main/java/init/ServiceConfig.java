package init;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mode.util.TaskManager;

/**
 * Java annotation based configuration for thread pool and service beans (via @ComponentScan).
 */
@Configuration
@PropertySource("classpath:config/thread.properties")
@ComponentScan(basePackages = {"com.mode.service", "com.mode.security", "com.mode.scheduler"})
public class ServiceConfig {

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

}
