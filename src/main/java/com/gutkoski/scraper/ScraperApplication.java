package com.gutkoski.scraper;

import com.gutkoski.scraper.application.ScrapingService;
import com.gutkoski.scraper.infrastructure.http.BlockingScraper;
import com.gutkoski.scraper.infrastructure.http.ReactiveScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpClient;

@SpringBootApplication
public class ScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScraperApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public ReactiveScraper reactiveScraper(WebClient webClient) {
        return new ReactiveScraper(webClient);
    }

    @Bean
    public BlockingScraper blockingScraper() {
        return new BlockingScraper(HttpClient.newHttpClient());
    }

    @Bean
    public ScrapingService scrapingService(BlockingScraper blockingScraper, ReactiveScraper reactiveScraper) {
        return new ScrapingService(blockingScraper, reactiveScraper);
    }
}
