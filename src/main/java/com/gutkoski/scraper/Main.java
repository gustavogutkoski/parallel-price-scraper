package com.gutkoski.scraper;

import com.gutkoski.scraper.application.ScrapingService;
import com.gutkoski.scraper.infrastructure.http.BlockingScraper;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        List<String> urls = List.of(
                //TODO search in URLs from products to extract data ex: prices, models, etc.
                "https://httpbin.org/delay/2",
                "https://httpbin.org/delay/3",
                "https://httpbin.org/delay/4"
        );

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            HttpClient client = HttpClient.newHttpClient();
            BlockingScraper scraper = new BlockingScraper(client);
            ScrapingService service = new ScrapingService(scraper);

            var results = submitBlockingScrapingTasks(urls, service, executor);
            printResults(results);

            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Future<String>> submitBlockingScrapingTasks(List<String> urls, ScrapingService scrapingService, ExecutorService executor) {
        return urls.stream()
                .map(url -> executor.submit(() -> scrapingService.fetchPrice(url)))
                .toList();
    }

    private static void printResults(List<Future<String>> results) {
        for (Future<String> result : results) {
            try {
                System.out.println(result.get());
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}