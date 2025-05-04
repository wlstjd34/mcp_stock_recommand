package com.js.mcp.stock.jpa.repo;

import com.js.mcp.stock.jpa.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
