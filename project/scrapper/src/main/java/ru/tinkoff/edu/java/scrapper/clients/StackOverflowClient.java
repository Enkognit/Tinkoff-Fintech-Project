package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import ru.tinkoff.edu.java.scrapper.DTO.responses.stackOverflowClient.STOQuestionInfoResponse;

@HttpExchange(url = "", accept = "application/vnd.github+json", contentType = "application/json")
public interface StackOverflowClient {
    @GetExchange(url = "/questions/{id}")
    STOQuestionInfoResponse getQuestionInfo(@PathVariable("id") Long id, @RequestParam(name = "site", defaultValue = "stackoverflow") String site);
}
