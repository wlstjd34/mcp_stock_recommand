package com.js.mcp.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.js.mcp.stock.jpa.entity")
@EnableJpaRepositories(basePackages = "com.js.mcp.stock.jpa.repo")
@ComponentScan(basePackages = "com.js.mcp.stock")
@SpringBootApplication
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

}
