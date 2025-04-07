package com.js.mcp.stock.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "stock", indexes = {
        @Index(name = "idx_stock_peTTM", columnList = "peTTM"),
        @Index(name = "idx_stock_pbQuarterly", columnList = "pbQuarterly"),
        @Index(name = "idx_stock_dividendYieldIndicatedAnnual", columnList = "dividendYieldIndicatedAnnual"),
        @Index(name = "idx_stock_roeTTM", columnList = "roeTTM")
})
public class Stock {

    @Id
    private String symbol;

    private Double fiftyTwoWeekPriceReturnDaily;
    private Double fiveDayPriceReturnDaily;
    private Double beta;
    private Double currentRatioQuarterly;
    private Double dividendYieldIndicatedAnnual;
    private Double epsGrowthTTMYoy;
    private Double marketCapitalization;
    private Double pbQuarterly;
    private Double peTTM;
    private Double revenueGrowthTTMYoy;
    private Double roaTTM;
    private Double roeTTM;
    private Double totalDebtTotalEquityQuarterly;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
