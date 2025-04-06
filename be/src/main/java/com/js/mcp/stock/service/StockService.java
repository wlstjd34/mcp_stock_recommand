package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;
import com.js.mcp.stock.jpa.entity.Stock;
import com.js.mcp.stock.jpa.repo.StockRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StockService {
    private final StockCollector stockCollector;

    private final StockRepository stockRepository;

    @Transactional
    public void collectStockData() throws IOException, InterruptedException {
        stockRepository.deleteAll();
        List<String> stockCode = stockCollector.getStockSymbolList();
        log.info("Total stock code count : {}", stockCode.size());
        List<StockDto> stockDataList = stockCollector.getStockInformations(stockCode);
        log.info("Total stock data count : {}", stockDataList.size());

        List<Stock> stocks = new ArrayList<>();
        for (StockDto stockData : stockDataList) {
            stocks.add(stockData.toEntity());
        }

        log.info("Insert To db start");
        stockRepository.saveAll(stocks);
        log.info("Insert To db end");
    }

    public void insertSampleData() {
        Stock sample = new Stock();
        sample.setSymbol("SAMPLE");
        stockRepository.save(sample);
    }
}
