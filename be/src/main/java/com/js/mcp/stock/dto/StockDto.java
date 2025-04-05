package com.js.mcp.stock.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockDto {
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
}
