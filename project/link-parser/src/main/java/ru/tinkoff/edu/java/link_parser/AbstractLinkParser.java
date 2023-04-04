package ru.tinkoff.edu.java.link_parser;

/**
 * Абстрактный класс, реализующий необходимые методы для добавления парсера в цепочку.
 * Чтобы реализовать новый парсер достаточно отнаследовать его от данного абстрактного класса,
 * добавить конструктор от объекта цепочки парсеров, вызывающий конструктор абстрактного класса
 * и реализовать метод parseURL, который при невозможности парсинга URL вызывает соответствующую
 * функцию парсинга URL у следующего парсера в цепочке посредством getNext().parseURL(url)
 */
public abstract class AbstractLinkParser implements LinkParser {

    private LinkParser next;

    protected AbstractLinkParser(LinkParsersChain chain) {
        chain.addParser(this);
    }

    public final void setNext(LinkParser next) {
        this.next = next;
    }

    public final LinkParser getNext() {
        return next;
    }
}
