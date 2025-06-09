package com.gutkoski.scraper.application;

import com.gutkoski.scraper.infrastructure.http.BlockingScraper;

import java.io.IOException;

public class ScrapingService {
    private final BlockingScraper blockingScraper;


    public ScrapingService(BlockingScraper blockingScraper) {
        this.blockingScraper = blockingScraper;
    }

    public String fetchPrice(String url) throws IOException, InterruptedException {
        return blockingScraper.fetchPrice(url);
    }
}
