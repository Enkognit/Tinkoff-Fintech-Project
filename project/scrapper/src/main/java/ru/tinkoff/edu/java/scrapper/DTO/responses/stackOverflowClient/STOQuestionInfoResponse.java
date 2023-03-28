package ru.tinkoff.edu.java.scrapper.DTO.responses.stackOverflowClient;

import ru.tinkoff.edu.java.scrapper.DTO.infoObjects.stackOverflow.StackOverflowQuestionInfo;

import java.util.List;

public record STOQuestionInfoResponse(List<StackOverflowQuestionInfo> items) {}
