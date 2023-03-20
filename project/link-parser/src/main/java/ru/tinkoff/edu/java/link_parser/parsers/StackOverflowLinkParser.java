package ru.tinkoff.edu.java.link_parser.parsers;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.link_parser.AbstractLinkParser;
import ru.tinkoff.edu.java.link_parser.LinkParser;
import ru.tinkoff.edu.java.link_parser.LinkParsersChain;

import java.net.URL;

@Component
public class StackOverflowLinkParser extends AbstractLinkParser {

    public StackOverflowLinkParser(LinkParsersChain chain) {
        super(chain);
    }

    @Override
    public String parseURL(URL url) {
        if (!url.getHost().equals("stackoverflow.com")) {
            return getNext().parseURL(url);
        }
        String[] paths = url.getPath().split("/", 4);
        if (paths.length < 3
                || !paths[1].equals("questions")
                || !paths[2].matches("\\d+")) {
            return getNext().parseURL(url);
        }
        return paths[2];
    }
}
