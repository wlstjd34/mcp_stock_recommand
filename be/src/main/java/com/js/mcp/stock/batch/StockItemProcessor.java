package com.js.mcp.stock.batch;

import com.js.mcp.stock.dto.StockDto;
import com.js.mcp.stock.jpa.entity.Stock;
import com.js.mcp.stock.jpa.entity.StockCode;
import com.js.mcp.stock.service.StockCollector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockItemProcessor implements ItemProcessor<StockCode, Stock> {

    private final StockCollector stockCollector;

    @Override
    public Stock process(StockCode stockCode) throws Exception {
        log.info("Fetching stock information for code: {}", stockCode.getCode());
        Optional<StockDto> stockDto = stockCollector.getStockInformation(stockCode.getCode());
        if (stockDto.isPresent()) {
            return stockDto.get().toEntity();
        } else {
            log.warn("Stock code [{}] not found. Skipping...", stockCode.getCode());
            return null; // 데이터가 없을 경우 null 반환
        }
    }
}
