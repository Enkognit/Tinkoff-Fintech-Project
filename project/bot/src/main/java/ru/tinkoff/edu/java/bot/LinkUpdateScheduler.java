package ru.tinkoff.edu.java.bot;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class LinkUpdateScheduler {

    private final List<String> logger;

    public LinkUpdateScheduler() {
        logger = new ArrayList<>();
    }

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {
        logger.add("Link update");
    }
}
