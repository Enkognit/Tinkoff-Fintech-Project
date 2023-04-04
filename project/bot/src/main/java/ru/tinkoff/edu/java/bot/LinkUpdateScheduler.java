package ru.tinkoff.edu.java.bot;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class LinkUpdateScheduler {

    private final List<String> logs;

    public LinkUpdateScheduler() {
        logs = new ArrayList<>();
    }

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {
        logs.add("Link update");
    }
}
