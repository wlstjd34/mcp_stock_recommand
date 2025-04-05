package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StockApiCollector {
    List<String> getStockSymbolList();
    List<StockDto> getStockInformations(List<String> symbolList);
}
