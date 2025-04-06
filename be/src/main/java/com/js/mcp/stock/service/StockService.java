package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;
import com.js.mcp.stock.jpa.entity.Stock;
import com.js.mcp.stock.jpa.repo.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StockService {
    private final StockCollector stockCollector;

    private final StockRepository stockRepository;

    public void collectStockData() throws IOException, InterruptedException {
        List<String> stockCode = stockCollector.getStockSymbolList();
        List<StockDto> stockDataList = stockCollector.getStockInformations(stockCode);

        List<Stock> stocks = new ArrayList<>();
        for (StockDto stockData : stockDataList) {
            stocks.add(stockData.toEntity());
        }

        stockRepository.saveAll(stocks);
    }
}
