package service;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
    private static Command command;
    private static UserMessagesProcessor messagesProcessor = Mockito.mock(UserMessagesProcessor.class);
    private static DataBase emptyDB;
    private static DataBase DB1;
    private static DataBase DB2;
    private static Update upd = Mockito.mock(Update.class);
    private static Message message = Mockito.mock(Message.class);
    private static Chat chat = Mockito.mock(Chat.class);

    private final static String link1 = "http://github.com";
    private final static Long chatNumber = 0L;

    @BeforeAll
    public static void settings() {
        emptyDB = new DataBase();
        DB1 = new DataBase();
        DB1.addLink(0, link1);
        DB2 = new DataBase();
        DB2.addLink(1, link1);
        Mockito.when(upd.message()).thenReturn(message);
        Mockito.when(message.chat()).thenReturn(chat);
        Mockito.when(message.text()).thenReturn("/list");
        Mockito.when(chat.id()).thenReturn(chatNumber);
    }

    @Test
    public void empty_list() {
        ListCommand lst = new ListCommand(messagesProcessor, emptyDB);
        SendMessage msg = lst.handle(upd);
        assertThat(msg.getParameters().get("text")).isEqualTo(ListCommand.NO_LINKS);
    }

    @Test
    public void one_link_in_list() {
        ListCommand lst = new ListCommand(messagesProcessor, DB1);
        SendMessage msg = lst.handle(upd);
        assertThat(msg.getParameters().get("text")).isEqualTo(link1);
    }

    @Test
    public void empty_list_but_others_user_list_is_not_empty() {
        ListCommand lst = new ListCommand(messagesProcessor, DB2);
        SendMessage msg = lst.handle(upd);
        assertThat(msg.getParameters().get("text")).isEqualTo(ListCommand.NO_LINKS);
    }
}
