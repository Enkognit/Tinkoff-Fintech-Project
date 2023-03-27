package ru.tinkoff.edu.java.bot.DTO.respronses;

import java.util.List;

public record ApiErrorResponse(String description, String code, String exceptionName, String exceptionMessage, List<String> stacktrace) {
}