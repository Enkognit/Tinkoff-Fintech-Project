package ru.tinkoff.edu.java.bot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.bot.DTO.requests.LinkUpdateRequest;

@RestController
@RequestMapping("/updates")
public class UpdatesController {

    @PostMapping
    public ResponseEntity updateRequest(@RequestBody LinkUpdateRequest linkUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
