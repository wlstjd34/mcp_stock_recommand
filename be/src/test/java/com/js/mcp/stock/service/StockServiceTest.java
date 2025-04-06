package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;
import com.js.mcp.stock.jpa.entity.Stock;
import com.js.mcp.stock.jpa.repo.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
class StockServiceTest {
    @Autowired
    StockService stockService;
    @Autowired
    StockRepository stockRepository;
    @MockitoBean
    StockCollector stockCollector;

    @Test
    void serviceTest() throws IOException, InterruptedException {
        // given
        when(stockCollector.getStockSymbolList()).thenReturn(List.of("AAPL"));
        when(stockCollector.getStockInformations(any())).thenReturn(List.of(makeDummyDto()));

        // when
        stockService.collectStockData();

        // then
        Collection<Stock> result = stockRepository.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next().getSymbol()).isEqualTo("AAPL");
    }

    StockDto makeDummyDto() {
        StockDto stockDto = new StockDto();
        stockDto.setSymbol("AAPL");
        return stockDto;
    }
}
