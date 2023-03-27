package ru.tinkoff.edu.java.bot;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;

@Component
@EnableScheduling
@EnableConfigurationProperties(ApplicationConfig.class)
public class LinkUpdateScheduler {

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {}
}
