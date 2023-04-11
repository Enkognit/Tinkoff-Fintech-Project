package ru.tinkoff.edu.java.scrapper.DTO.responses.gitHubClient;

import ru.tinkoff.edu.java.scrapper.DTO.infoObjects.github.GitHubOwnerInfo;

public record GitHubRepoInfoResponse(Long id,
                                     String node_id,
                                     String name,
                                     String full_name,
                                     GitHubOwnerInfo owner) {
}
