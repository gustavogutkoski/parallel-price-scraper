package com.gutkoski.scraper.application;

import com.gutkoski.scraper.infrastructure.http.BlockingScraper;
import com.gutkoski.scraper.infrastructure.http.ReactiveScraper;
import reactor.core.publisher.Mono;

import java.io.IOException;

public class ScrapingService {
    private final BlockingScraper blockingScraper;
    private final ReactiveScraper reactiveScraper;


    public ScrapingService(BlockingScraper blockingScraper, ReactiveScraper reactiveScraper) {
        this.blockingScraper = blockingScraper;
        this.reactiveScraper = reactiveScraper;
    }

    public String fetchPriceBlocking(String url) throws IOException, InterruptedException {
        return blockingScraper.fetchPrice(url);
    }

    public Mono<String> fetchPriceReactive(String url) {
        return reactiveScraper.fetchPrice(url);
    }
}
