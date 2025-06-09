package com.gutkoski.scraper.web;

import com.gutkoski.scraper.application.ScrapingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ScraperController {

    private final ScrapingService scrapingService;

    public ScraperController(ScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/scrape/reactive")
    public Mono<String> fetchReactive(@RequestParam String url) {
        return scrapingService.fetchPriceReactive(url);
    }

    @GetMapping("/scrape/blocking")
    public String fetchBlocking(@RequestParam String url) throws Exception {
        return scrapingService.fetchPriceBlocking(url);
    }
}
