package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.DTO.requests.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.DTO.requests.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.DTO.responses.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.DTO.responses.LinkResponse;
import ru.tinkoff.edu.java.scrapper.DTO.responses.ListLinkResponse;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinksController {

    @GetMapping
    public ListLinkResponse getAllLinks(@RequestHeader("Tg-Chat-Id") Long id) {
        return new ListLinkResponse(List.of(), 0);
    }

    @PostMapping
    public LinkResponse addLink(@RequestHeader("Tg-Chat-Id") Long id, @RequestBody AddLinkRequest request) {
        return new LinkResponse(id, request.link());
    }

    @DeleteMapping
    public LinkResponse deleteLink(@RequestHeader("Tg-Chat-Id") Long id, @RequestBody RemoveLinkRequest request) {
        if (false) {
            throw new IllegalArgumentException("No such link");
        }
        return new LinkResponse(id, request.link());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse("Error in links controlling", "404", exception.getClass().getName(), exception.getMessage(), List.of()));
    }
}
