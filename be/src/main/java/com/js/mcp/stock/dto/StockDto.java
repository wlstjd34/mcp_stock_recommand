package com.js.mcp.stock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.js.mcp.stock.jpa.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    @JsonProperty("Symbol")
    private String symbol;
    @JsonProperty("AssetType")
    private String assetType;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("CIK")
    private String cik;
    @JsonProperty("Exchange")
    private String exchange;
    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Sector")
    private String sector;
    @JsonProperty("Industry")
    private String industry;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("FiscalYearEnd")
    private String fiscalYearEnd;
    @JsonProperty("LatestQuarter")
    private String latestQuarter;
    @JsonProperty("MarketCapitalization")
    private Long marketCapitalization;
    @JsonProperty("EBITDA")
    private Long ebitda;
    @JsonProperty("PERatio")
    private Double peRatio;
    @JsonProperty("PEGRatio")
    private Double pegRatio;
    @JsonProperty("BookValue")
    private Double bookValue;
    @JsonProperty("DividendPerShare")
    private Double dividendPerShare;
    @JsonProperty("DividendYield")
    private Double dividendYield;
    @JsonProperty("EPS")
    private Double eps;
    @JsonProperty("RevenuePerShareTTM")
    private Double revenuePerShareTTM;
    @JsonProperty("ProfitMargin")
    private Double profitMargin;
    @JsonProperty("OperatingMarginTTM")
    private Double operatingMarginTTM;
    @JsonProperty("ReturnOnAssetsTTM")
    private Double returnOnAssetsTTM;
    @JsonProperty("ReturnOnEquityTTM")
    private Double returnOnEquityTTM;
    @JsonProperty("RevenueTTM")
    private Long revenueTTM;
    @JsonProperty("GrossProfitTTM")
    private Long grossProfitTTM;
    @JsonProperty("DilutedEPSTTM")
    private Double dilutedEPSTTM;
    @JsonProperty("QuarterlyEarningsGrowthYOY")
    private Double quarterlyEarningsGrowthYOY;
    @JsonProperty("QuarterlyRevenueGrowthYOY")
    private Double quarterlyRevenueGrowthYOY;
    @JsonProperty("AnalystTargetPrice")
    private Double analystTargetPrice;
    @JsonProperty("AnalystRatingStrongBuy")
    private Integer analystRatingStrongBuy;
    @JsonProperty("AnalystRatingBuy")
    private Integer analystRatingBuy;
    @JsonProperty("AnalystRatingHold")
    private Integer analystRatingHold;
    @JsonProperty("AnalystRatingSell")
    private Integer analystRatingSell;
    @JsonProperty("AnalystRatingStrongSell")
    private Integer analystRatingStrongSell;
    @JsonProperty("TrailingPE")
    private Double trailingPE;
    @JsonProperty("ForwardPE")
    private Double forwardPE;
    @JsonProperty("PriceToSalesRatioTTM")
    private Double priceToSalesRatioTTM;
    @JsonProperty("PriceToBookRatio")
    private Double priceToBookRatio;
    @JsonProperty("EVToRevenue")
    private Double evToRevenue;
    @JsonProperty("EVToEBITDA")
    private Double evToEBITDA;
    @JsonProperty("Beta")
    private Double beta;
    @JsonProperty("52WeekHigh")
    private Double _52WeekHigh;
    @JsonProperty("52WeekLow")
    private Double _52WeekLow;
    @JsonProperty("50DayMovingAverage")
    private Double _50DayMovingAverage;
    @JsonProperty("200DayMovingAverage")
    private Double _200DayMovingAverage;
    @JsonProperty("SharesOutstanding")
    private Long sharesOutstanding;
    @JsonProperty("DividendDate")
    private String dividendDate;
    @JsonProperty("ExDividendDate")
    private String exDividendDate;

    public Stock toEntity() {
        Stock stock = new Stock();
        stock.setSymbol(this.symbol);
        stock.setAssetType(this.assetType);
        stock.setName(this.name);
        stock.setDescription(this.description);
        stock.setCik(this.cik);
        stock.setExchange(this.exchange);
        stock.setCurrency(this.currency);
        stock.setCountry(this.country);
        stock.setSector(this.sector);
        stock.setIndustry(this.industry);
        stock.setAddress(this.address);
        stock.setFiscalYearEnd(this.fiscalYearEnd);
        stock.setLatestQuarter(this.latestQuarter);
        stock.setMarketCapitalization(this.marketCapitalization);
        stock.setEbitda(this.ebitda);
        stock.setPeRatio(this.peRatio);
        stock.setPegRatio(this.pegRatio);
        stock.setBookValue(this.bookValue);
        stock.setDividendPerShare(this.dividendPerShare);
        stock.setDividendYield(this.dividendYield);
        stock.setEps(this.eps);
        stock.setRevenuePerShareTTM(this.revenuePerShareTTM);
        stock.setProfitMargin(this.profitMargin);
        stock.setOperatingMarginTTM(this.operatingMarginTTM);
        stock.setReturnOnAssetsTTM(this.returnOnAssetsTTM);
        stock.setReturnOnEquityTTM(this.returnOnEquityTTM);
        stock.setRevenueTTM(this.revenueTTM);
        stock.setGrossProfitTTM(this.grossProfitTTM);
        stock.setDilutedEPSTTM(this.dilutedEPSTTM);
        stock.setQuarterlyEarningsGrowthYOY(this.quarterlyEarningsGrowthYOY);
        stock.setQuarterlyRevenueGrowthYOY(this.quarterlyRevenueGrowthYOY);
        stock.setAnalystTargetPrice(this.analystTargetPrice);
        stock.setAnalystRatingStrongBuy(this.analystRatingStrongBuy);
        stock.setAnalystRatingBuy(this.analystRatingBuy);
        stock.setAnalystRatingHold(this.analystRatingHold);
        stock.setAnalystRatingSell(this.analystRatingSell);
        stock.setAnalystRatingStrongSell(this.analystRatingStrongSell);
        stock.setTrailingPE(this.trailingPE);
        stock.setForwardPE(this.forwardPE);
        stock.setPriceToSalesRatioTTM(this.priceToSalesRatioTTM);
        stock.setPriceToBookRatio(this.priceToBookRatio);
        stock.setEvToRevenue(this.evToRevenue);
        stock.setEvToEBITDA(this.evToEBITDA);
        stock.setBeta(this.beta);
        stock.set_52WeekHigh(this._52WeekHigh);
        stock.set_52WeekLow(this._52WeekLow);
        stock.set_50DayMovingAverage(this._50DayMovingAverage);
        stock.set_200DayMovingAverage(this._200DayMovingAverage);
        stock.setSharesOutstanding(this.sharesOutstanding);
        stock.setDividendDate(this.dividendDate);
        stock.setExDividendDate(this.exDividendDate);
        return stock;
    }
}
