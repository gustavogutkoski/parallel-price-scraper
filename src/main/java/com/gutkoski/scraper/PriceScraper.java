package com.gutkoski.scraper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PriceScraper {
    private final HttpClient client;
    private final String url;

    public PriceScraper(HttpClient client, String url) {
        this.client = client;
        this.url = url;
    }

    public String fetchPrice() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return "Fetched from: " + url + " | status: " + response.statusCode();
    }
}