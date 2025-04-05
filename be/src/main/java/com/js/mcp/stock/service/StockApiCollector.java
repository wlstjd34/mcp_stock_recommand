package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;

import java.util.List;
import java.util.Optional;

public interface StockApiCollector {
    List<String> getStockSymbolList();
    List<StockDto> getStockInformations(List<String> symbolList);
    Optional<StockDto>  getStockInformation(String symbol);
}
