package com.js.mcp.stock.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.mcp.stock.dto.StockDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockCollectorImpl implements StockCollector {
    @Value("${alphavantage.key}")
    private String alphavantageKey;

    private final ApiProcessor apiProcessor;

    private static final String ALPHAVANTAGE_BASE_URL = "https://www.alphavantage.co/query";

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public StockCollectorImpl(ApiProcessor apiProcessor) {
        this.apiProcessor = apiProcessor;
    }

    @Override
    public List<String> getStockSymbolList() throws IOException, InterruptedException {
        String url = ALPHAVANTAGE_BASE_URL + "?function=LISTING_STATUS&apikey=" + alphavantageKey;
        String responseBody = apiProcessor.callApi(url);

        // Split the response body into lines and add the first element of each line to the result list
        return responseBody.lines()
                .map(line -> line.split(",")[0])
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDto> getStockInformations(List<String> symbolList) {
        List<StockDto> stockDtos = new ArrayList<>();

        for (String symbol : symbolList) {
            try {
                Optional<StockDto> dto = getStockInformation(symbol);
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
        String url = ALPHAVANTAGE_BASE_URL + "?function=OVERVIEW&symbol=" + symbol + "&apikey=" + alphavantageKey;
        log.info("Requesting stock information for symbol: {} from URL: {}", symbol, url);

        String responseBody = apiProcessor.callApi(url);

        try {
            StockDto data = objectMapper.readValue(responseBody, StockDto.class);
            log.debug("Stock information for symbol {}: {}", symbol, data);
            return Optional.of(data);
        } catch (JsonProcessingException ex) {
            return Optional.empty();
        }
    }
}
