package com.js.mcp.stock.controller;

import com.js.mcp.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PutMapping("/collectStock")
    public String collectStock() throws IOException, InterruptedException {
        stockService.collectStockData();
        return "Stock data collected and saved!";
    }

    @PutMapping("/insertSample")
    public void insertSample() {
        stockService.insertSampleData();
    }
}
