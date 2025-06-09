package com.gutkoski.scraper.infrastructure.http;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ReactiveScraper {

    private final WebClient webClient;

    public ReactiveScraper(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> fetchPrice(String url) {
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> "Fetched from: " + url + " | response length: " + body.length());
    }
}
