package ru.tinkoff.edu.java.scrapper.DTO.responses.gitHubClient;

import java.net.URL;

public record GitHubOwnerInfo(String login,
                              Integer id,
                              String node_id,
                              URL url,
                              URL http_url) {
}
