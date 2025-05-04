package com.js.mcp.stock.jpa.repo;

import com.js.mcp.stock.jpa.entity.StockCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockCodeRepository extends JpaRepository<StockCode, String> {
}
