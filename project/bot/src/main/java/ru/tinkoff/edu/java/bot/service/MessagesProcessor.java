package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface MessagesProcessor {

    void putOnStack(long chatId, Function<Update, SendMessage> hh);

    BotInterface getBot();

    void addCommand(Command com);

    List<? extends BotCommand> commands();

    SendMessage process(Update update);
}