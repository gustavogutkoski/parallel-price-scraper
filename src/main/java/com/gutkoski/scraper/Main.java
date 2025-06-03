package com.gutkoski.scraper;

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

            var results = submitScrapingTasks(urls, client, executor);

            printResults(results);

            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Future<String>> submitScrapingTasks(List<String> urls, HttpClient client, ExecutorService executor) {
        return urls.stream()
                .map(url -> executor.submit(() -> {
                    PriceScraper scraper = new PriceScraper(client, url);
                    return scraper.fetchPrice();
                }))
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