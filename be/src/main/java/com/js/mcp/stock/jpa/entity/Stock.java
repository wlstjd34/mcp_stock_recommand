package com.js.mcp.stock.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Index;

import java.time.LocalDateTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@NoArgsConstructor
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double tenDayAverageTradingVolume;
    private Double thirteenWeekPriceReturnDaily;
    private Double twentySixWeekPriceReturnDaily;
    private Double threeMonthADReturnStd;
    private Double threeMonthAverageTradingVolume;
    private Double fiftyTwoWeekHigh;
    private LocalDate fiftyTwoWeekHighDate;
    private Double fiftyTwoWeekLow;
    private LocalDate fiftyTwoWeekLowDate;
    private Double fiftyTwoWeekPriceReturnDaily;
    private Double fiveDayPriceReturnDaily;
    private Double assetTurnoverAnnual;
    private Double assetTurnoverTTM;
    private Double beta;
    private Double bookValuePerShareAnnual;
    private Double bookValuePerShareQuarterly;
    private Double bookValueShareGrowth5Y;
    private Double capexCagr5Y;
    private Double cashFlowPerShareAnnual;
    private Double cashFlowPerShareQuarterly;
    private Double cashFlowPerShareTTM;
    private Double cashPerSharePerShareAnnual;
    private Double cashPerSharePerShareQuarterly;
    private Double currentDividendYieldTTM;
    private Double currentEvFreeCashFlowAnnual;
    private Double currentEvFreeCashFlowTTM;
    private Double currentRatioAnnual;
    private Double currentRatioQuarterly;
    private Double dividendGrowthRate5Y;
    private Double dividendPerShareAnnual;
    private Double dividendPerShareTTM;
    private Double dividendYieldIndicatedAnnual;
    private Double ebitdPerShareAnnual;
    private Double ebitdPerShareTTM;
    private Double ebitdaCagr5Y;
    private Double ebitdaInterimCagr5Y;
    private Double enterpriseValue;
    private Double epsAnnual;
    private Double epsBasicExclExtraItemsAnnual;
    private Double epsBasicExclExtraItemsTTM;
    private Double epsExclExtraItemsAnnual;
    private Double epsExclExtraItemsTTM;
    private Double epsGrowth3Y;
    private Double epsGrowth5Y;
    private Double epsGrowthQuarterlyYoy;
    private Double epsGrowthTTMYoy;
    private Double epsInclExtraItemsAnnual;
    private Double epsInclExtraItemsTTM;
    private Double epsNormalizedAnnual;
    private Double epsTTM;
    private Double focfCagr5Y;
    private Double grossMargin5Y;
    private Double grossMarginAnnual;
    private Double grossMarginTTM;
    private Double inventoryTurnoverAnnual;
    private Double inventoryTurnoverTTM;
    private Double longTermDebtEquityAnnual;
    private Double longTermDebtEquityQuarterly;
    private Double marketCapitalization;
    private Double monthToDatePriceReturnDaily;
    private Double netIncomeEmployeeAnnual;
    private Double netIncomeEmployeeTTM;
    private Double netInterestCoverageAnnual;
    private Double netInterestCoverageTTM;
    private Double netMarginGrowth5Y;
    private Double netProfitMargin5Y;
    private Double netProfitMarginAnnual;
    private Double netProfitMarginTTM;
    private Double operatingMargin5Y;
    private Double operatingMarginAnnual;
    private Double operatingMarginTTM;
    private Double payoutRatioAnnual;
    private Double payoutRatioTTM;
    private Double pbAnnual;
    private Double pbQuarterly;
    private Double pcfShareAnnual;
    private Double pcfShareTTM;
    private Double peAnnual;
    private Double peBasicExclExtraTTM;
    private Double peExclExtraAnnual;
    private Double peExclExtraTTM;
    private Double peInclExtraTTM;
    private Double peNormalizedAnnual;
    private Double peTTM;
    private Double pfcfShareAnnual;
    private Double pfcfShareTTM;
    private Double pretaxMargin5Y;
    private Double pretaxMarginAnnual;
    private Double pretaxMarginTTM;
    private Double priceRelativeToSP50013Week;
    private Double priceRelativeToSP50026Week;
    private Double priceRelativeToSP5004Week;
    private Double priceRelativeToSP50052Week;
    private Double priceRelativeToSP500Ytd;
    private Double psAnnual;
    private Double psTTM;
    private Double ptbvAnnual;
    private Double ptbvQuarterly;
    private Double quickRatioAnnual;
    private Double quickRatioQuarterly;
    private Double receivablesTurnoverAnnual;
    private Double receivablesTurnoverTTM;
    private Double revenueEmployeeAnnual;
    private Double revenueEmployeeTTM;
    private Double revenueGrowth3Y;
    private Double revenueGrowth5Y;
    private Double revenueGrowthQuarterlyYoy;
    private Double revenueGrowthTTMYoy;
    private Double revenuePerShareAnnual;
    private Double revenuePerShareTTM;
    private Double revenueShareGrowth5Y;
    private Double roa5Y;
    private Double roaRfy;
    private Double roaTTM;
    private Double roe5Y;
    private Double roeRfy;
    private Double roeTTM;
    private Double roi5Y;
    private Double roiAnnual;
    private Double roiTTM;
    private Double tangibleBookValuePerShareAnnual;
    private Double tangibleBookValuePerShareQuarterly;
    private Double tbvCagr5Y;
    private Double totalDebtTotalEquityAnnual;
    private Double totalDebtTotalEquityQuarterly;
    private Double yearToDatePriceReturnDaily;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
