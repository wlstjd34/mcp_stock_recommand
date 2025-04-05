package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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

    @Test
    void getStockInformation() {
        // when
        Optional<StockDto> result = stockApiCollector.getStockInformation("AAPL");

        // then
        assertThat(result.isPresent()).isTrue(); // 애플이 상폐되지 않는한 성공
        assertThat(result.get().getName()).isEqualTo("Apple Inc");
    }
}