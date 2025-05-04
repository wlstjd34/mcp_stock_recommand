package com.js.mcp.stock.batch;

import com.js.mcp.stock.jpa.entity.Stock;
import com.js.mcp.stock.jpa.repo.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockWriter implements ItemWriter<Stock> {

    private final StockRepository stockRepository;

    @Override
    public void write(Chunk<? extends Stock> items) throws Exception {
        log.info("Saving new stock data to DB...");
        stockRepository.saveAll(items);
        log.info("New stock data saved. Total {} records.", items.size());
    }
}
