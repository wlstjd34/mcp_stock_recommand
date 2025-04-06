package com.js.mcp.stock.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.mcp.stock.dto.StockDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockApiCollectorImpl implements StockApiCollector {

    @Value("${alphavantage.key}")
    private String ALPHAVANTAGE_KEY;

    private static final int MAX_RETRY_COUNT = 5;
    private static final int RETRY_DELAY_MS = 70000;

    @Override
    public List<String> getStockSymbolList() {
        List<String> result = new ArrayList<>();
        String csvUrl = "https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=" + ALPHAVANTAGE_KEY;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(csvUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            // Split the response body into lines and add the first element of each line to the result list
            result = responseBody.lines()
                    .map(line -> line.split(",")[0])
                    .collect(Collectors.toList());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., log it or throw a custom exception
        }

        return result;
    }

    @Override
    public List<StockDto> getStockInformations(List<String> symbolList) {
        List<StockDto> stockDtos = new ArrayList<>();

        for (String stockDto : symbolList) {
            try {
                Optional<StockDto> dto = getStockInformation(stockDto);
                dto.ifPresent(stockDtos::add);
            } catch (Exception e) {
                log.info("API Collecting is over. Insert collected data");
                break;
            }
        }

        return stockDtos;
    }

    @Override
    public Optional<StockDto> getStockInformation(String symbol) throws IOException, InterruptedException {
        int retryCount = 0;
        while (retryCount <= MAX_RETRY_COUNT) {
            String url = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + ALPHAVANTAGE_KEY;
            System.out.println(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            if (responseBody.contains("Error Message")) {
                if (retryCount == MAX_RETRY_COUNT) {
                    throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Max retry attempts exceeded for symbol: " + symbol);
                }
                retryCount++;
                System.out.println("Error Message found. Retrying in 60 seconds... (Attempt " + retryCount + "/" + MAX_RETRY_COUNT + ")");
                Thread.sleep(RETRY_DELAY_MS);
                continue; // Retry the API call
            }
            // JSON 문자열을 Map으로 변환
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            StockDto data = mapper.readValue(responseBody, StockDto.class);
            System.out.println(data);
            return Optional.of(data);
        }
        return Optional.empty(); // Should not reach here, but added for safety
    }
}
