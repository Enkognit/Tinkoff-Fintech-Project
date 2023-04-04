package service;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.edu.java.bot.service.Bot;
import ru.tinkoff.edu.java.bot.service.DataBase;
import ru.tinkoff.edu.java.bot.service.MessagesProcessor;
import ru.tinkoff.edu.java.bot.service.UserMessagesProcessor;
import ru.tinkoff.edu.java.bot.service.botCommands.*;

import static org.assertj.core.api.Assertions.*;

public class MessageProcTests {

    private static Update upd1 = Mockito.mock(Update.class);
    private static Message message1 = Mockito.mock(Message.class);
    private static Chat chat1 = Mockito.mock(Chat.class);
    private static Update upd2 = Mockito.mock(Update.class);
    private static Message message2 = Mockito.mock(Message.class);
    private static Chat chat2 = Mockito.mock(Chat.class);
    private static Bot bot = Mockito.mock(Bot.class);
    private static Long chatNumber = 0L;

    @BeforeAll
    public static void settings() {
        Mockito.when(upd1.message()).thenReturn(message1);
        Mockito.when(message1.chat()).thenReturn(chat1);
        Mockito.when(message1.text()).thenReturn("/lis");
        Mockito.when(chat1.id()).thenReturn(chatNumber);

        Mockito.when(upd2.message()).thenReturn(message2);
        Mockito.when(message2.chat()).thenReturn(chat2);
        Mockito.when(message2.text()).thenReturn("gg");
        Mockito.when(chat2.id()).thenReturn(chatNumber);
    }

    @Test
    public void unknown_command_with_begin_slash_will_return_UNKNOWN_COMMAND() {
        MessagesProcessor proc = new UserMessagesProcessor(bot);
        SendMessage msg1 = proc.process(upd1);
        assertThat(msg1.getParameters().get("text")).isEqualTo(UserMessagesProcessor.UNKNOWN_COMMAND);
    }

    @Test
    public void unknown_command_without_begin_slash_will_return_UNKNOWN_COMMAND() {
        MessagesProcessor proc = new UserMessagesProcessor(bot);
        SendMessage msg = proc.process(upd2);
        assertThat(msg.getParameters().get("text")).isEqualTo(UserMessagesProcessor.UNKNOWN_COMMAND);
    }
    @SpringBootTest(classes = {
            UserMessagesProcessor.class,
            Bot.class,
            DataBase.class,
            ListCommand.class,
            StartCommand.class,
            TrackCommand.class,
            UnTrackCommand.class,
            HelpCommand.class
    }
    )
    public static class ValidLinks {

        @Autowired
        public UserMessagesProcessor proc;

        @ParameterizedTest
        @ValueSource(strings = {
                "/start",
                "/track",
                "/untrack",
                "/help",
                "/list",
        })
        public void valid_commands_will_be_recognized(String command) {
            Update upd = Mockito.mock(Update.class);
            Message message = Mockito.mock(Message.class);
            Chat chat = Mockito.mock(Chat.class);

            Mockito.when(upd.message()).thenReturn(message);
            Mockito.when(message.chat()).thenReturn(chat);
            Mockito.when(message.text()).thenReturn(command);
            Mockito.when(chat.id()).thenReturn(0L);
            Mockito.when(chat.username()).thenReturn("Enkognit");

            SendMessage msg = proc.process(upd);
            assertThat(msg.getParameters().get("text")).isNotEqualTo(UserMessagesProcessor.UNKNOWN_COMMAND);
        }
    }

}
