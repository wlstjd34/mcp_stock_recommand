package com.js.mcp.stock.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.mcp.stock.dto.StockDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StockApiCollectorImpl implements StockApiCollector {

    @Value("${alphavantage.key}")
    private String ALPHAVANTAGE_KEY;

    @Override
    public List<String> getStockSymbolList() {
        List<String> result = new ArrayList<>();
        String csvUrl = "https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=" + ALPHAVANTAGE_KEY;

        try {
            URL url = new URL(csvUrl);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line.split(",")[0]);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., log it or throw a custom exception
        }

        return result;
    }

    @Override
    public List<StockDto> getStockInformations(List<String> symbolList) {
        return symbolList.stream()
                .map(this::getStockInformation)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StockDto> getStockInformation(String symbol) {
        try {
            String url = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + ALPHAVANTAGE_KEY;
            System.out.println(url);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            // JSON 문자열을 Map으로 변환
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            StockDto data = mapper.readValue(responseBody, StockDto.class);
            System.out.println(data);

            return Optional.of(data);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
