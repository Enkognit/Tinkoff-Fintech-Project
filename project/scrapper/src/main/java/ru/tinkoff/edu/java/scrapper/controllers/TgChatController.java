package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tg-chat/{id}")
public class TgChatController {

    @PostMapping
    public ResponseEntity registerChat(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity deleteChat(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
