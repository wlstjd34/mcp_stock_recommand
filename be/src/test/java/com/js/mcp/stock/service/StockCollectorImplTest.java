package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class StockCollectorImplTest {
    @Autowired
    StockCollector stockCollector;

    @Test
    void getStockSymbolList() throws IOException, InterruptedException {
        // when
        List<String> result = stockCollector.getStockSymbolList();

        // then
        assertThat(result).isNotEmpty();
    }

    @Test
    void getStockInformation() throws IOException, InterruptedException {
        // when
        Optional<StockDto> result = stockCollector.getStockInformation("AAPL");

        // then
        assertThat(result.isPresent()).isTrue(); // 애플이 상폐되지 않는한 성공
    }
}