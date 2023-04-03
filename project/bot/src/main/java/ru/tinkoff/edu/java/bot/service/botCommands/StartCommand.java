package ru.tinkoff.edu.java.bot.service.botCommands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.AbstractCommand;
import ru.tinkoff.edu.java.bot.service.Command;
import ru.tinkoff.edu.java.bot.service.UserMessagesProcessor;

@Component
public class StartCommand extends AbstractCommand implements Command {

    public StartCommand(UserMessagesProcessor messagesProcessor) {
        super(messagesProcessor);
    }

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "this command registers new user";
    }

    @Override
    public SendMessage handle(Update update) {
        if (!supports(update)) {
            throw new IllegalArgumentException("");
        }

        // register user
        return new SendMessage(update.message().chat().id(), "New user: " + update.message().chat().username() + " is registered");
    }
}
