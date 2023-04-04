package ru.tinkoff.edu.java.bot.clients;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import ru.tinkoff.edu.java.bot.DTO.scrapper.requests.AddLinkRequest;
import ru.tinkoff.edu.java.bot.DTO.scrapper.requests.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.DTO.scrapper.responses.LinkResponse;
import ru.tinkoff.edu.java.bot.DTO.scrapper.responses.ListLinkResponse;

@HttpExchange(url = "")
public interface ScrapperClient {
    @GetExchange(url = "/list")
    ListLinkResponse getAllLinks(@RequestHeader("Tg-Char-Id") Long id);

    @PostExchange(url = "/list")
    LinkResponse addLink(@RequestHeader("Tg-Chat-Id") Long id, @RequestBody AddLinkRequest request);

    @DeleteExchange(url = "/list")
    LinkResponse deleteLink(@RequestHeader("Tg-Chat-Id") Long id, @RequestBody RemoveLinkRequest request);

    @PostExchange(url = "/tg-chat/{id}")
    ResponseEntity registerChat(@PathVariable("id") Long id);

    @DeleteExchange(url = "/tg-chat/{id}")
    ResponseEntity deleteChat(@PathVariable("id") Long id);
}
