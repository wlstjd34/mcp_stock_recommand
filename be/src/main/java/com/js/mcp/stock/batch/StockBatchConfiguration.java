package com.js.mcp.stock.batch;

import com.js.mcp.stock.dto.StockDto;
import com.js.mcp.stock.jpa.entity.Stock;
import com.js.mcp.stock.jpa.entity.StockCode;
import com.js.mcp.stock.jpa.repo.StockCodeRepository;
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
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StockBatchConfiguration {

    private final StockCollector stockCollector;
    private final StockRepository stockRepository;
    private final StockCodeRepository stockCodeRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    @Autowired
    private StockItemProcessor stockItemProcessor;
    @Autowired
    private StockWriter stockWriter;
    @Autowired
    private DataSource dataSource;

    @Bean
    public ItemReader<StockCode> stockCodeItemReader() throws Exception {
        JdbcPagingItemReader<StockCode> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setPageSize(10);
        reader.setRowMapper(new BeanPropertyRowMapper<>(StockCode.class));
        reader.setQueryProvider(pagingQueryProvider());
        return reader;
    }

    @Bean
    public PagingQueryProvider pagingQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean factoryBean = new SqlPagingQueryProviderFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setSelectClause("SELECT code");
        factoryBean.setFromClause("FROM stock_code");
        factoryBean.setSortKey("code");
        return factoryBean.getObject();
    }

    @Bean
    public Job collectStockJob() throws Exception {
        return new JobBuilder("collectStockJob" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()), jobRepository)
                .start(deleteAllInBatchStep())
                .next(getStockSymbolListStep())
                .next(stockInformationStep(stockCodeItemReader()))
                .build();
    }

    @Bean
    public Step deleteAllInBatchStep() {
        return new StepBuilder("deleteAllInBatchStep", jobRepository)
                .tasklet(deleteAllInBatchTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet deleteAllInBatchTasklet() {
        return (contribution, chunkContext) -> {
            log.info(">>>>> Deleting existing stock data...");
            stockRepository.deleteAllInBatch();
            log.info(">>>>> Existing stock data deleted.");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step getStockSymbolListStep() {
        return new StepBuilder("getStockSymbolListStep", jobRepository)
                .tasklet(getStockSymbolListTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet getStockSymbolListTasklet() {
        return (contribution, chunkContext) -> {
            log.info(">>>>> Fetching stock symbols...");
            List<String> stockCode = stockCollector.getStockSymbolList();
            log.info("Total stock code count : {}", stockCode.size());
            List<StockCode> stockCodeEntities = stockCode.stream().map(code -> {
                StockCode stockCodeEntity = new StockCode();
                stockCodeEntity.setCode(code);
                return stockCodeEntity;
            }).collect(Collectors.toList());
            stockCodeRepository.saveAll(stockCodeEntities);
            log.info(">>>>> Stock symbols fetched and saved to DB.");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step stockInformationStep(ItemReader<StockCode> stockCodeItemReader) throws Exception {
        return new StepBuilder("stockInformationStep", jobRepository)
                .<StockCode, Stock>chunk(10, transactionManager)
                .reader(stockCodeItemReader)
                .processor(stockItemProcessor)
                .writer(stockWriter)
                .build();
    }
}
