package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;

public abstract class AbstractCommand implements Command {

    protected MessagesProcessor commandProcessor;

    protected AbstractCommand(MessagesProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
        commandProcessor.addCommand(this);
    }
    
    public MessagesProcessor commandProcessor() {
        return commandProcessor;
    }

    public boolean supports(Update update) {
        return update.message().text().equals(command());
    }

    public BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}
