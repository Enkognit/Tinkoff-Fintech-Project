package ru.tinkoff.edu.java.link_parser.parsers;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.link_parser.AbstractLinkParser;
import ru.tinkoff.edu.java.link_parser.LinkParser;
import ru.tinkoff.edu.java.link_parser.LinkParsersChain;

import java.net.URL;

@Component
public class GithubLinkParser extends AbstractLinkParser {

    public GithubLinkParser(LinkParsersChain chain) {
        super(chain);
    }

    @Override
    public String parseURL(URL url) {
        if (!url.getHost().equals("github.com")) {
            return getNext().parseURL(url);
        }
        String[] paths = url.getPath().split("/", 4);
        if (paths.length < 3 || paths[1].equals("") || paths[2].equals("")) {
            return getNext().parseURL(url);
        }
        return paths[1] + "/" + paths[2];
    }
}
