package ir.msob.jima.message.email.service;

import ir.msob.jima.core.beans.properties.JimaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@RequiredArgsConstructor
public class EmailProviderConfiguration {

    private final JimaProperties jimaProperties;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(jimaProperties.getMessage().getEmail().getThreadPoolSize());
        threadPoolTaskScheduler.setThreadNamePrefix(jimaProperties.getMessage().getEmail().getThreadNamePrefix());
        return threadPoolTaskScheduler;
    }
}
