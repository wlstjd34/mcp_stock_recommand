package com.js.mcp.stock.service;

import com.js.mcp.stock.dto.StockDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockCollectorImplMockTest {
    @InjectMocks
    StockCollectorImpl stockCollector;

    @Mock
    ApiProcessor apiProcessor;

    @Test
    void getStockInformation() throws IOException, InterruptedException {
        // given
        when(apiProcessor.callApi(any())).thenReturn("Error");

        // when
        Optional<StockDto> result = stockCollector.getStockInformation("AAPL");

        // then
        assertThat(result.isPresent()).isFalse(); // 애플이 상폐되지 않는한 성공
    }
}
