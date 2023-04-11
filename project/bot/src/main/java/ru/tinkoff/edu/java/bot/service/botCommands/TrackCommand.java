package ru.tinkoff.edu.java.bot.service.botCommands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.AbstractCommand;
import ru.tinkoff.edu.java.bot.service.Command;
import ru.tinkoff.edu.java.bot.service.DataBase;
import ru.tinkoff.edu.java.bot.service.UserMessagesProcessor;

import java.util.function.Function;

@Component
public class TrackCommand extends AbstractCommand implements Command {

    private DataBase dataBase;
    public TrackCommand(UserMessagesProcessor messagesProcessor, DataBase dataBase) {
        super(messagesProcessor);
        this.dataBase = dataBase;
    }

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "this command adds new link";
    }

    private SendMessage getLink(Update update) {
        if (dataBase.addLink(update.message().chat().id(), update.message().text())) {
            return new SendMessage(update.message().chat().id(), "Link: " + update.message().text() + " is added");
        } else {
            return new SendMessage(update.message().chat().id(), "Cannot add link: " + update.message().text());
        }
    }

    @Override
    public SendMessage handle(Update update) {
        if (!supports(update)) {
            throw new IllegalArgumentException("");
        }
        commandProcessor().putOnStack(update.message().chat().id(), this::getLink);
        return new SendMessage(update.message().chat().id(), "Give link");
    }
}

