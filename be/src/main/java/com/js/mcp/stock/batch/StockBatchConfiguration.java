package com.js.mcp.stock.batch;

import com.js.mcp.stock.dto.StockDto;
import com.js.mcp.stock.jpa.entity.Stock;
import com.js.mcp.stock.jpa.repo.StockRepository;
import com.js.mcp.stock.service.StockCollector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StockBatchConfiguration {

    private final StockCollector stockCollector;
    private final StockRepository stockRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job collectStockJob() {
        return new JobBuilder("collectStockJob", jobRepository)
                .start(collectStockStep())
                .build();
    }

    @Bean
    public Step collectStockStep() {
        return new StepBuilder("collectStockStep", jobRepository)
                .tasklet(collectStockTasklet(), transactionManager) // Pass transactionManager
                .build();
    }

    @Bean
    public Tasklet collectStockTasklet() {
        return (contribution, chunkContext) -> {
            log.info(">>>>> Stock data collection batch job started");

            // 1. Delete existing data (within a transaction managed by the Tasklet)
            log.info("Deleting existing stock data...");
            stockRepository.deleteAllInBatch(); // Use deleteAllInBatch for efficiency
            log.info("Existing stock data deleted.");

            // 2. Fetch stock symbols
            log.info("Fetching stock symbols...");
            List<String> stockCode = stockCollector.getStockSymbolList();
            log.info("Total stock code count : {}", stockCode.size());

            // 3. Fetch stock information
            log.info("Fetching stock information...");
            List<StockDto> stockDataList = stockCollector.getStockInformations(stockCode);
            log.info("Total stock data count : {}", stockDataList.size());

            // 4. Convert DTOs to Entities
            List<Stock> stocks = new ArrayList<>();
            for (StockDto stockData : stockDataList) {
                stocks.add(stockData.toEntity());
            }

            // 5. Save entities (within the same transaction)
            log.info("Saving new stock data to DB...");
            stockRepository.saveAll(stocks);
            log.info("New stock data saved. Total {} records.", stocks.size());

            log.info(">>>>> Stock data collection batch job finished");
            return RepeatStatus.FINISHED;
        };
    }
}
