package com.js.mcp.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
public class ApiProcessorImpl implements ApiProcessor {

    private static final int MAX_RETRY_COUNT = 5;
    private static final int RETRY_DELAY_MS = 70000;
    private static final int RETRY_CONNECTION_MS = 5000;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    /**
     * 공통 API 호출 로직
     * @param url API URL
     * @return API 응답 결과
     * @throws IOException, InterruptedException
     */
    @Override
    public String callApi(String url) throws InterruptedException {
        int retryCount = 0;
        while (retryCount <= MAX_RETRY_COUNT) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            String responseBody = "";
            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                responseBody = response.body();

                if (responseBody.contains("Error Message") || responseBody.contains("limit reached")) {
                    if (retryCount == MAX_RETRY_COUNT) {
                        throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Max retry attempts exceeded for URL: " + url);
                    }
                    retryCount++;
                    log.warn("Error Message found. Retrying in {} seconds... (Attempt {}/{})", RETRY_DELAY_MS / 1000, retryCount, MAX_RETRY_COUNT);
                    Thread.sleep(RETRY_DELAY_MS);
                    continue; // Retry the API call
                }
            } catch (Exception e) {
                retryCount++;
                log.warn("Unexpected Exception. Retrying in {} seconds... (Attempt {}/{})", RETRY_CONNECTION_MS / 1000, retryCount, MAX_RETRY_COUNT);
                Thread.sleep(RETRY_CONNECTION_MS);
                continue; // Retry the API call
            }

            return responseBody;
        }

        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Service not available" + url);
    }
}
