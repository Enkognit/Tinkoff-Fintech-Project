package ru.tinkoff.edu.java.bot;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class LinkUpdateScheduler {

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {}
}
