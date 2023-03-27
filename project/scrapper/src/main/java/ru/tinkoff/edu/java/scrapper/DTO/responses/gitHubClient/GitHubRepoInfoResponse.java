package ru.tinkoff.edu.java.scrapper.DTO.responses.gitHubClient;

public record GitHubRepoInfoResponse(Long id,
                                     String node_id,
                                     String name,
                                     String full_name,
                                     GitHubOwnerInfo owner) {
}
