import org.junit.Test;
import ru.tinkoff.edu.java.link_parser.LinkParser;
import ru.tinkoff.edu.java.link_parser.LinkParsersChain;
import ru.tinkoff.edu.java.link_parser.parsers.StackOverflowLinkParser;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

public class StackOverflowLinkParserTests {

    @Test
    public void valid_links() throws MalformedURLException {

        URL link1 = new URL("http://stackoverflow.com/questions/123");
        URL link2 = new URL("https://stackoverflow.com/questions/420/comments");
        URL link3 = new URL("http://stackoverflow.com/questions/228/dskglldmkgb/sdvsw/wmqgmqgklwdd/dddd");

        LinkParsersChain def = new LinkParsersChain(new LinkParser.DefaultLinkParser());

        StackOverflowLinkParser parser = new StackOverflowLinkParser(def);

        assertThat(parser.parseURL(link1)).isEqualTo("123");
        assertThat(parser.parseURL(link2)).isEqualTo("420");
        assertThat(parser.parseURL(link3)).isEqualTo("228");
    }

    @Test
    public void invalid_links() throws MalformedURLException {

        URL link1 = new URL("http://stackoverflow.com/question/123");
        URL link2 = new URL("https://stackoverflow.com/questions/gg/comments");
        URL link3 = new URL("http://github.com/Enkognit/MathLog");

        LinkParsersChain def = new LinkParsersChain(new LinkParser.DefaultLinkParser());

        StackOverflowLinkParser parser = new StackOverflowLinkParser(def);

        assertThat(parser.parseURL(link1)).isEqualTo(null);
        assertThat(parser.parseURL(link2)).isEqualTo(null);
        assertThat(parser.parseURL(link3)).isEqualTo(null);
    }
}
