package ru.tinkoff.edu.java.link_parser;

import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * Класс представляющий цепочку парсеров, у которой метод parseURL реализует требуемый механизм разбора URL.
 */
@Component
public class LinkParsersChain {

    private LinkParser start;

    public LinkParsersChain(LinkParser.DefaultLinkParser start) {
        this.start = start;
    }

    public void addParser(LinkParser newParser) {
        newParser.setNext(start);
        start = newParser;
    }
    public String parseURL(URL url) {
        return start.parseURL(url);
    }
}
