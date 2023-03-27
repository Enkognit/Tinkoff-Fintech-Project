package ru.tinkoff.edu.java.bot.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.bot.DTO.requests.LinkUpdate;

@RestController
@RequestMapping("/updates")
public class UpdatesController {

    @PostMapping
    public ResponseEntity updateRequest(@RequestBody LinkUpdate linkUpdate) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
