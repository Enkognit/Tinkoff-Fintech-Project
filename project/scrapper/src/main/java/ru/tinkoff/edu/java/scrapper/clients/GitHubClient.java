package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import ru.tinkoff.edu.java.scrapper.DTO.responses.gitHubClient.GitHubRepoInfoResponse;

@HttpExchange(url = "", accept = "application/vnd.github+json")
public interface GitHubClient {
    @GetExchange(url = "/repos/{owner}/{repo}")
    GitHubRepoInfoResponse getRepositoryInfo(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}
