package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface Command {

    MessagesProcessor commandProcessor();

    String command();

    String description();

    SendMessage handle(Update update);

    boolean supports(Update update);

    BotCommand toApiCommand();
}
