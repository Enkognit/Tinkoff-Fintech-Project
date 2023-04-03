import org.junit.Test;
import org.mockito.Mockito;
import ru.tinkoff.edu.java.link_parser.LinkParser;
import ru.tinkoff.edu.java.link_parser.LinkParsersChain;
import ru.tinkoff.edu.java.link_parser.parsers.GithubLinkParser;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

public class GithubLinksParserTests {

    @Test
    public void valid_links() throws MalformedURLException {

        URL link1 = new URL("http://github.com/gg/tt");
        URL link2 = new URL("http://github.com/gg/tt/pull/1");
        URL link3 = new URL("http://github.com/Enkognit/Tinkoff-Fintech-Project");

        LinkParsersChain def = new LinkParsersChain(new LinkParser.DefaultLinkParser());

        GithubLinkParser parser = new GithubLinkParser(def);

        assertThat(parser.parseURL(link1)).isEqualTo("gg/tt");
        assertThat(parser.parseURL(link2)).isEqualTo("gg/tt");
        assertThat(parser.parseURL(link3)).isEqualTo("Enkognit/Tinkoff-Fintech-Project");
    }

    @Test
    public void invalid_links() throws MalformedURLException {

        URL link1 = new URL("http://github.com/gg");
        URL link2 = new URL("http://github.com/gg//pull/1");
        URL link3 = new URL("http://stackoverflow.com/Enkognit/MathLog");
        URL link4 = new URL("http://githu.com/Enkognit/MathLog");

        LinkParsersChain def = new LinkParsersChain(new LinkParser.DefaultLinkParser());

        GithubLinkParser parser = new GithubLinkParser(def);
        assertThat(parser.parseURL(link1)).isEqualTo(null);
        assertThat(parser.parseURL(link2)).isEqualTo(null);
        assertThat(parser.parseURL(link3)).isEqualTo(null);
        assertThat(parser.parseURL(link4)).isEqualTo(null);
    }
}
