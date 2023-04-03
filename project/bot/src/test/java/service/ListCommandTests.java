package service;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.edu.java.bot.service.Command;
import ru.tinkoff.edu.java.bot.service.DataBase;
import ru.tinkoff.edu.java.bot.service.MessagesProcessor;
import ru.tinkoff.edu.java.bot.service.UserMessagesProcessor;
import ru.tinkoff.edu.java.bot.service.botCommands.ListCommand;

import javax.xml.crypto.Data;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class ListCommandTests {

    @Mock
    private Command command;
    private UserMessagesProcessor messagesProcessor = Mockito.mock(UserMessagesProcessor.class);
    private DataBase emptyDB;
    private DataBase DB1;
    private DataBase DB2;
    private Update upd = Mockito.mock(Update.class);
    private Message message = Mockito.mock(Message.class);
    private Chat chat = Mockito.mock(Chat.class);

    private final static String link1 = "http://github.com";
    private final static Long chatNumber = 0L;

    public void settings() {
        emptyDB = new DataBase();
        DB1 = new DataBase();
        DB2 = new DataBase();
        DB1.addLink(0, link1);
        DB2.addLink(1, link1);
        Mockito.when(upd.message()).thenReturn(message);
        Mockito.when(message.chat()).thenReturn(chat);
        Mockito.when(message.text()).thenReturn("/list");
        Mockito.when(chat.id()).thenReturn(chatNumber);
    }

    @Test
    public void empty_list() {
        settings();
        // Setting
        ListCommand lst1 = new ListCommand(messagesProcessor, emptyDB);
        ListCommand lst2 = new ListCommand(messagesProcessor, DB1);
        ListCommand lst3 = new ListCommand(messagesProcessor, DB2);
        // App
        SendMessage msg1 = lst1.handle(upd);
        assertThat(msg1.getParameters().get("text")).isEqualTo(ListCommand.NO_LINKS);

        SendMessage msg2 = lst2.handle(upd);
        assertThat(msg2.getParameters().get("text")).isEqualTo(link1);

        SendMessage msg3 = lst3.handle(upd);
        assertThat(msg3.getParameters().get("text")).isEqualTo(ListCommand.NO_LINKS);
    }
}
