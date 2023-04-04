import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.tinkoff.edu.java.link_parser.LinkParser;
import ru.tinkoff.edu.java.link_parser.LinkParsersChain;
import ru.tinkoff.edu.java.link_parser.parsers.GithubLinkParser;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

public class GithubLinksParserTests {

    @ParameterizedTest
    @ValueSource(strings = {
            "http://github.com/gg/tt",
            "http://github.com/gg/tt/pull/1",
            "https://github.com/Enkognit/Tinkoff-Fintech-Project"
            })
    public void valid_links(String link) throws MalformedURLException {
        URL url = new URL(link);

        LinkParsersChain def = new LinkParsersChain(new LinkParser.DefaultLinkParser());

        GithubLinkParser parser = new GithubLinkParser(def);

        String[] path = link
                .substring(link.indexOf(":") + "://github.com/".length())
                .split("/", 3);
        String result = path[0] + "/" + path[1];

        assertThat(parser.parseURL(url)).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "http://github.com/gg/",
            "http://github.com/gg",
            "http://github.com/gg//pull/1",
            "http://stackoverflow.com/Enkognit/MathLog",
            "http://githu.com/Enkognit/MathLog"
    })
    public void invalid_links(String link) throws MalformedURLException {

        URL url = new URL(link);

        LinkParsersChain def = new LinkParsersChain(new LinkParser.DefaultLinkParser());

        GithubLinkParser parser = new GithubLinkParser(def);
        assertThat(parser.parseURL(url)).isEqualTo(null);
    }
}
