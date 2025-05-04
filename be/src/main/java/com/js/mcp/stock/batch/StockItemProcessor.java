package com.js.mcp.stock.batch;

import com.js.mcp.stock.dto.StockDto;
import com.js.mcp.stock.jpa.entity.Stock;
import com.js.mcp.stock.jpa.entity.StockCode;
import com.js.mcp.stock.service.StockCollector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockItemProcessor implements ItemProcessor<StockCode, Stock> {

    private final StockCollector stockCollector;

    @Override
    public Stock process(StockCode stockCode) throws Exception {
        log.info("Fetching stock information for code: {}", stockCode.getCode());
        StockDto stockDto = stockCollector.getStockInformations(java.util.Collections.singletonList(stockCode.getCode())).get(0);
        return stockDto.toEntity();
    }
}
