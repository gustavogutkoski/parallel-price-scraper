package com.gutkoski.scraper.infrastructure.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BlockingScraper {
    private final HttpClient client;

    public BlockingScraper(HttpClient client) {
        this.client = client;
    }

    public String fetchPrice(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return "Fetched from: " + url + " | status: " + response.statusCode();
    }
}
