package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

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
        return List.of();
    }
}
