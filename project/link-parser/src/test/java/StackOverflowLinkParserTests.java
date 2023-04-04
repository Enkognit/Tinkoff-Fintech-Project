import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.tinkoff.edu.java.link_parser.LinkParser;
import ru.tinkoff.edu.java.link_parser.LinkParsersChain;
import ru.tinkoff.edu.java.link_parser.parsers.GithubLinkParser;
import ru.tinkoff.edu.java.link_parser.parsers.StackOverflowLinkParser;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

public class StackOverflowLinkParserTests {

    @ParameterizedTest
    @ValueSource(strings = {
            "http://stackoverflow.com/questions/123",
            "https://stackoverflow.com/questions/420/comments",
            "http://stackoverflow.com/questions/228/dskglldmkgb/sdvsw/wmqgmqgklwdd/dddd"
    })
    public void valid_links(String link) throws MalformedURLException {
        URL url = new URL(link);

        LinkParsersChain def = new LinkParsersChain(new LinkParser.DefaultLinkParser());

        StackOverflowLinkParser parser = new StackOverflowLinkParser(def);

        String[] path = link
                .substring(link.indexOf(":") + "://stackoverflow.com/questions/".length())
                .split("/", 2);
        String result = path[0];

        assertThat(parser.parseURL(url)).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "http://stackoverflow.com/question/123",
            "https://stackoverflow.com/questions/gg/comments",
            "http://github.com/Enkognit/MathLog"
    })
    public void invalid_links(String link) throws MalformedURLException {

        URL url = new URL(link);

        LinkParsersChain def = new LinkParsersChain(new LinkParser.DefaultLinkParser());

        StackOverflowLinkParser parser = new StackOverflowLinkParser(def);

        assertThat(parser.parseURL(url)).isEqualTo(null);
    }
}
