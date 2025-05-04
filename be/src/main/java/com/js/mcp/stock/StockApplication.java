package com.js.mcp.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

@SpringBootApplication
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

	@Bean
	public CommandLineRunner runJob(JobLauncher jobLauncher, Job collectStockJob) {
		return args -> {
			JobParameters jobParameters = new JobParametersBuilder()
					.toJobParameters();
			jobLauncher.run(collectStockJob, jobParameters);
		};
	}
}
