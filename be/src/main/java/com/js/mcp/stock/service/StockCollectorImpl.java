package com.js.mcp.stock.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.mcp.stock.dto.StockDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StockCollectorImpl implements StockCollector {
    @Value("${finnhub.token}")
    private String finnhubToken;

    private final ApiProcessor apiProcessor;

    private static final String FINNHUB_BASE_URL = "https://finnhub.io/api/v1";

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public StockCollectorImpl(ApiProcessor apiProcessor) {
        this.apiProcessor = apiProcessor;
    }

    @Override
    public List<String> getStockSymbolList() throws IOException, InterruptedException {
        List<String> nasdaqSymbols = getSymbolList("XNAS");
        List<String> nyseSymbols = getSymbolList("XNYS");

        nasdaqSymbols.addAll(nyseSymbols);
        return nasdaqSymbols;
    }

    private List<String> getSymbolList(String mic) throws IOException, InterruptedException {
        String url = FINNHUB_BASE_URL + "/stock/symbol?exchange=US&currency=USD&mic=" + mic + "&token=" + finnhubToken;
        String responseBody = apiProcessor.callApi(url);

        try {
            JsonNode root = objectMapper.readTree(responseBody);
            List<String> symbolList = new ArrayList<>();
            for (JsonNode result : root) {
                symbolList.add(result.path("symbol").asText());
            }
            return symbolList;
        } catch (JsonProcessingException e) {
            log.error("Error parsing Finnhub API response", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error parsing Finnhub API response", e);
        }
    }

    @Override
    public List<StockDto> getStockInformations(List<String> symbolList) {
        List<StockDto> stockDtos = new ArrayList<>();

        int remain = symbolList.size();
        for (String symbol : symbolList) {
            try {
                Optional<StockDto> dto = getStockInformation(symbol);
                dto.ifPresent(stockDtos::add);
            } catch (Exception e) {
                log.error(e.getMessage());
                log.error("API Collecting is over. Insert collected data");
                break;
            }
            log.info("processing remain : [{} / {}]", remain--, symbolList.size());
        }

        return stockDtos;
    }

    @Override
    public Optional<StockDto> getStockInformation(String symbol) throws IOException, InterruptedException {
        String url = FINNHUB_BASE_URL + "/stock/metric?symbol=" + symbol + "&metric=all&token=" + finnhubToken;

        String responseBody = apiProcessor.callApi(url);

        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode metric = root.path("metric");

            if (metric.isEmpty()) {
                log.warn("No metric data found for symbol: {}", symbol);
                return Optional.empty();
            }

            StockDto stockDto = objectMapper.readValue(metric.toString(), StockDto.class);
            stockDto.setSymbol(symbol);
            log.debug("Stock information for symbol {}: {}", symbol, stockDto);
            return Optional.of(stockDto);

        } catch (JsonProcessingException ex) {
            log.error("Error processing stock information for symbol: {}", symbol, ex);
            return Optional.empty();
        }
    }
}
