package ru.tinkoff.edu.java.bot.service.botCommands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.AbstractCommand;
import ru.tinkoff.edu.java.bot.service.Command;
import ru.tinkoff.edu.java.bot.service.UserMessagesProcessor;

import java.util.stream.Collectors;

@Component
public class HelpCommand extends AbstractCommand implements Command {

    public HelpCommand(UserMessagesProcessor messagesProcessor) {
        super(messagesProcessor);
    }

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "this command prints all available command to use";
    }

    @Override
    public SendMessage handle(Update update) {
        if (!supports(update)) {
            throw new IllegalArgumentException("");
        }
        return new SendMessage(update.message().chat().id(),
                        commandProcessor().commands().stream()
                        .map(v -> v.command() + " - " + v.description())
                        .collect(Collectors.joining(System.lineSeparator())));
    }
}

