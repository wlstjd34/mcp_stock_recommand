package com.js.mcp.stock.jpa;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stock", indexes = {
        @Index(name = "idx_stock_name", columnList = "name"),
        @Index(name = "idx_stock_sector", columnList = "sector"),
        @Index(name = "idx_stock_industry", columnList = "industry"),
        @Index(name = "idx_stock_peRatio", columnList = "peRatio"),
        @Index(name = "idx_stock_pegRatio", columnList = "pegRatio"),
        @Index(name = "idx_stock_exchange", columnList = "exchange"),
        @Index(name = "idx_stock_currency", columnList = "currency")
})
public class Stock {

    @Id
    private String symbol;
    private String assetType;
    private String name;
    private String description;
    private String cik;
    private String exchange;
    private String currency;
    private String country;
    private String sector;
    private String industry;
    private String address;
    private String fiscalYearEnd;
    private String latestQuarter;
    private Long marketCapitalization;
    private Long ebitda;
    private Double peRatio;
    private Double pegRatio;
    private Double bookValue;
    private Double dividendPerShare;
    private Double dividendYield;
    private Double eps;
    private Double revenuePerShareTTM;
    private Double profitMargin;
    private Double operatingMarginTTM;
    private Double returnOnAssetsTTM;
    private Double returnOnEquityTTM;
    private Long revenueTTM;
    private Long grossProfitTTM;
    private Double dilutedEPSTTM;
    private Double quarterlyEarningsGrowthYOY;
    private Double quarterlyRevenueGrowthYOY;
    private Double analystTargetPrice;
    private Integer analystRatingStrongBuy;
    private Integer analystRatingBuy;
    private Integer analystRatingHold;
    private Integer analystRatingSell;
    private Integer analystRatingStrongSell;
    private Double trailingPE;
    private Double forwardPE;
    private Double priceToSalesRatioTTM;
    private Double priceToBookRatio;
    private Double evToRevenue;
    private Double evToEBITDA;
    private Double beta;
    private Double _52WeekHigh;
    private Double _52WeekLow;
    private Double _50DayMovingAverage;
    private Double _200DayMovingAverage;
    private Long sharesOutstanding;
    private String dividendDate;
    private String exDividendDate;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
