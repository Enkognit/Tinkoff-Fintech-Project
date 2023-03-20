package ru.tinkoff.edu.java.link_parser;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Интерфейс, представляющий парсер в цепочке.
 */
public interface LinkParser {
    default String parseURL(URL url) {
        return null;
    }
    default void setNext(LinkParser next) {}
    default LinkParser getNext() {
        return null;
    }

    @Component
    record DefaultLinkParser() implements LinkParser {}
}
