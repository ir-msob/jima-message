package ir.msob.jima.message.notification.service;

import ir.msob.jima.core.beans.properties.JimaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@RequiredArgsConstructor
public class NotificationProviderConfiguration {

    private final JimaProperties jimaProperties;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(jimaProperties.getMessage().getNotification().getThreadPoolSize());
        threadPoolTaskScheduler.setThreadNamePrefix(jimaProperties.getMessage().getNotification().getThreadNamePrefix());
        return threadPoolTaskScheduler;
    }
}
