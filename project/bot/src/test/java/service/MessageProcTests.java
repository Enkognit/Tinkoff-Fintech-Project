package service;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.Test;
import org.mockito.Mockito;
import ru.tinkoff.edu.java.bot.service.Bot;
import ru.tinkoff.edu.java.bot.service.DataBase;
import ru.tinkoff.edu.java.bot.service.MessagesProcessor;
import ru.tinkoff.edu.java.bot.service.UserMessagesProcessor;
import ru.tinkoff.edu.java.bot.service.botCommands.ListCommand;

import static org.assertj.core.api.Assertions.*;

public class MessageProcTests {

    private Update upd1 = Mockito.mock(Update.class);
    private Message message1 = Mockito.mock(Message.class);
    private Chat chat1 = Mockito.mock(Chat.class);
    private Update upd2 = Mockito.mock(Update.class);
    private Message message2 = Mockito.mock(Message.class);
    private Chat chat2 = Mockito.mock(Chat.class);
    private Bot bot = Mockito.mock(Bot.class);
    private static Long chatNumber = 0L;

    public void settings() {
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
    public void unknown_command() {
        settings();
        MessagesProcessor proc = new UserMessagesProcessor(bot);
        SendMessage msg1 = proc.process(upd1);
        assertThat(msg1.getParameters().get("text")).isEqualTo(UserMessagesProcessor.UNKNOWN_COMMAND);
        SendMessage msg2 = proc.process(upd2);
        assertThat(msg2.getParameters().get("text")).isEqualTo(UserMessagesProcessor.UNKNOWN_COMMAND);
    }

}
