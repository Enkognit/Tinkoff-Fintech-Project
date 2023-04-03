package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;

import java.util.List;

@Service
@EnableConfigurationProperties(ApplicationConfig.class)
public class Bot implements BotInterface {

    private MessagesProcessor messagesProcessor;
    private TelegramBot bot;

    public Bot(@Value("${app.bottoken}") String botToken) {
        bot = new TelegramBot(botToken);
        bot.setUpdatesListener(this);

    }

    public void setMessagesProcessor(MessagesProcessor messagesProcessor) {
        this.messagesProcessor = messagesProcessor;
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> R execute(BaseRequest<T, R> request) {
        return bot.execute(request);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update upd : updates) {
            bot.execute(messagesProcessor.process(upd));
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void start() {
    }

    @Override
    public void close() {
    }
}