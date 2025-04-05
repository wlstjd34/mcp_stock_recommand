package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;

import java.util.List;

public interface StockApiCollector {
    List<String> getStockSymbolList();
    List<StockDto> getStockInformations(List<String> symbolList);
}
