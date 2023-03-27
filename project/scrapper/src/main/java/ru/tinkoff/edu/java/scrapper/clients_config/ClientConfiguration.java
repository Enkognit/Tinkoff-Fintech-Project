package ru.tinkoff.edu.java.scrapper.clients_config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.scrapper.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.clients.StackOverflowClient;

@Configuration
public class ClientConfiguration {

    @Value("${clients.github}")
    private String baseGitHubURL;
    @Value("${clients.stackoverflow}")
    private String baseStackOverflowURL;

    private HttpServiceProxyFactory createFactory(String url) {
        return HttpServiceProxyFactory
                .builder(WebClientAdapter
                        .forClient(WebClient
                                .builder()
                                .baseUrl(url)
                                .build()))
                .build();
    }

    @Bean(name = "gitHubClient")
    public GitHubClient gitHubClient() {
        return createFactory(baseGitHubURL).createClient(GitHubClient.class);
    }

    @Bean(name = "stackOverflowClient")
    public StackOverflowClient stackOverflowClient() {
        return createFactory(baseStackOverflowURL).createClient(StackOverflowClient.class);
    }

    public GitHubClient gitHubClient(String url) {
        return createFactory(url).createClient(GitHubClient.class);
    }

    public StackOverflowClient stackOverflowClient(String url) {
        return createFactory(url).createClient(StackOverflowClient.class);
    }
}
