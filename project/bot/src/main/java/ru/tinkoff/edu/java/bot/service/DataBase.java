package ru.tinkoff.edu.java.bot.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataBase {
    private Map<Long, HashSet<String>> linkBase;

    public DataBase() {
        linkBase = new HashMap<>();
    }
    private int linksAmount;

    public boolean existsChat(long chatId) {
        return linkBase.containsKey(chatId);
    }

    public boolean existsLink(long chatId, String link) {
        return linkBase.get(chatId).contains(link);
    }

    public boolean addLink(long chatId, String link) {
        if (!existsChat(chatId)) {
            linkBase.put(chatId, new HashSet<>());
        } else if (existsLink(chatId, link)) {
            return false;
        }
        linkBase.get(chatId).add(link);
        linksAmount++;
        return true;
    }

    public boolean removeLink(long chatId, String link) {
        if (!existsChat(chatId) || !existsLink(chatId, link)) {
            return false;
        }
        linkBase.get(chatId).add(link);
        linksAmount--;
        return true;
    }

    public boolean isEmpty() {
        return linksAmount == 0;
    }

    public boolean isEmpty(Long chatId) {
        return !linkBase.containsKey(chatId) || linkBase.get(chatId).size() == 0;
    }

    public int size() {
        return linksAmount;
    }

    public int size(Long chatId) {
        return linkBase.containsKey(chatId) ? linkBase.get(chatId).size() : 0;
    }

    public Stream<String> links(long chatId) {
        return linkBase.get(chatId).stream();
    }
}
