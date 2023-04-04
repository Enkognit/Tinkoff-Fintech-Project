package ru.tinkoff.edu.java.bot.service.botCommands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.AbstractCommand;
import ru.tinkoff.edu.java.bot.service.Command;
import ru.tinkoff.edu.java.bot.service.DataBase;
import ru.tinkoff.edu.java.bot.service.UserMessagesProcessor;

import javax.xml.crypto.Data;
import java.util.stream.Collectors;

@Component
public class ListCommand extends AbstractCommand implements Command {

    public static final String NO_LINKS = "No tracked links";

    private DataBase dataBase;

    public ListCommand(UserMessagesProcessor messagesProcessor, DataBase dataBase) {
        super(messagesProcessor);
        this.dataBase = dataBase;
    }

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "this command prints all tracked links";
    }

    @Override
    public SendMessage handle(Update update) {
        if (!supports(update)) {
            throw new IllegalArgumentException("");
        }
        if (dataBase.isEmpty(update.message().chat().id())) {
            return new SendMessage(update.message().chat().id(), NO_LINKS);
        } else {
            return new SendMessage(update.message().chat().id(),
                    dataBase.links(update.message().chat().id())
                            .collect(Collectors.joining("\n")));
        }
    }
}

