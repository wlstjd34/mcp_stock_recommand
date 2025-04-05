package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;
import com.js.mcp.stock.jpa.entity.Stock;
import com.js.mcp.stock.jpa.repo.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StockService {
    private final StockApiCollector stockApiCollector;

    private final StockRepository stockRepository;

    public void collectStockData() {
        List<String> stockCode = stockApiCollector.getStockSymbolList();
        List<StockDto> stockDataList = stockApiCollector.getStockInformations(stockCode);

        List<Stock> stocks = new ArrayList<>();
        for (StockDto stockData : stockDataList) {
            stocks.add(stockData.toEntity());
        }

        stockRepository.saveAll(stocks);
    }
}
