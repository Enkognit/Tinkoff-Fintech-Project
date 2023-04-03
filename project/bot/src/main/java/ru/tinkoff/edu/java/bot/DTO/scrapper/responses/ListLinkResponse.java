package ru.tinkoff.edu.java.bot.DTO.scrapper.responses;

import java.util.List;

public record ListLinkResponse(List<LinkResponse> links, Integer size) {
}
