package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;
import com.js.mcp.stock.jpa.Stock;
import com.js.mcp.stock.jpa.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Service
public class StockService {
    @Autowired
    private StockApiCollector stockApiCollector;

    @Autowired
    private StockRepository stockRepository;

    private final String externalApiUrl = "/* TODO: Get the external API URL from the user */";

    public void collectStockData() {
        List<String> stockCode = stockApiCollector.getStockSymbolList();
        List<StockDto> stockDataList = stockApiCollector.getStockInformations(stockCode);

        List<Stock> stocks = new ArrayList<>();
        for (StockDto stockData : stockDataList) {
            Stock stock = stockData.toEntity();
        }

        stockRepository.saveAll(stocks);
    }
}
