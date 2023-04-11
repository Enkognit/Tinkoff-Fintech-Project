package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import ru.tinkoff.edu.java.scrapper.DTO.responses.stackOverflowClient.STOQuestionInfoResponse;

@HttpExchange
public interface StackOverflowClient {
    @GetExchange(url = "/questions/{ids}?order=desc&sort=activity&site=stackoverflow")
    STOQuestionInfoResponse getQuestionInfo(@PathVariable("ids") Integer ids);
}
