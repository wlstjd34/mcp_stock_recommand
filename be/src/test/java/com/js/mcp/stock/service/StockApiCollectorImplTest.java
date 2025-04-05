package com.js.mcp.stock.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class StockApiCollectorImplTest {
    @Autowired
    StockApiCollector stockApiCollector;

    @Test
    void getStockSymbolList() {
        // when
        List<String> result = stockApiCollector.getStockSymbolList();

        // then
        assertThat(result).isNotEmpty();
    }
}