package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class UserMessagesProcessor implements MessagesProcessor {

    public final static String UNKNOWN_COMMAND = "Unknown command";
    private Map<Long, Deque<Function<Update, SendMessage>>> chatsCommandStacks = new HashMap<>();
    private Map<String, Command> commands = new HashMap<>();
    private BotInterface bot;

    public UserMessagesProcessor(Bot bot) {
        this.bot = bot;
        bot.setMessagesProcessor(this);
    }

    @Override
    public void putOnStack(long chatId, Function<Update, SendMessage> func) {
        getStack(chatId).addFirst(func);
    }

    public BotInterface getBot() {
        return bot;
    }

    public Deque<Function<Update, SendMessage>> getStack(long chatId) {
        if (!chatsCommandStacks.containsKey(chatId)) {
            chatsCommandStacks.put(chatId, new ArrayDeque<>());
        }
        return chatsCommandStacks.get(chatId);
    }

    @Override
    public void addCommand(Command com) {
        commands.put(com.command(), com);
    }

    @Override
    public List<? extends BotCommand> commands() {
        return commands.values().stream().map(Command::toApiCommand).toList();
    }

    @Override
    public SendMessage process(Update update) {
        if (update == null || update.message() == null) {
            return new SendMessage(0, "bad request");
        }
        if (!getStack(update.message().chat().id()).isEmpty()) {
            return getStack(update.message().chat().id()).pop().apply(update);
        }
        for (Command com : commands.values()) {
            if (com.supports(update)) {
                return com.handle(update);
            }
        }
        return new SendMessage(update.message().chat().id(), UNKNOWN_COMMAND);
    }
}

