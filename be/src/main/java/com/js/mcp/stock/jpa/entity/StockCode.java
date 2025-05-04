package com.js.mcp.stock.jpa.entity;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "stock_code")
public class StockCode {

    @Id
    private String code;
}
