package ru.tinkoff.edu.java.bot.clients_conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;

@Configuration
public class ClientConfiguration {

    @Value("${clients.scrapper}")
    private String baseScrapperURL;

    private HttpServiceProxyFactory createFactory(String url) {
        return HttpServiceProxyFactory
                .builder(WebClientAdapter
                        .forClient(WebClient
                                .builder()
                                .baseUrl(url)
                                .build()))
                .build();
    }

    @Bean(name = "scrapperClient")
    public ScrapperClient scrapperClient() {
        return createFactory(baseScrapperURL).createClient(ScrapperClient.class);
    }

    public ScrapperClient scrapperClient(String url) {
        return createFactory(url).createClient(ScrapperClient.class);
    }
}

