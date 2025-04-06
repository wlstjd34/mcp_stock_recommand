package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface StockCollector {
    List<String> getStockSymbolList() throws IOException, InterruptedException;
    List<StockDto> getStockInformations(List<String> symbolList);
    Optional<StockDto>  getStockInformation(String symbol) throws IOException, InterruptedException;
}
