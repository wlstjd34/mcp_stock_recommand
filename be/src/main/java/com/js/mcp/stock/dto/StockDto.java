package com.js.mcp.stock.dto;

import com.js.mcp.stock.jpa.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private String symbol;
    @JsonProperty("10DayAverageTradingVolume")
    private Double tenDayAverageTradingVolume;
    @JsonProperty("13WeekPriceReturnDaily")
    private Double thirteenWeekPriceReturnDaily;
    @JsonProperty("26WeekPriceReturnDaily")
    private Double twentySixWeekPriceReturnDaily;
    @JsonProperty("3MonthADReturnStd")
    private Double threeMonthADReturnStd;
    @JsonProperty("3MonthAverageTradingVolume")
    private Double threeMonthAverageTradingVolume;
    @JsonProperty("52WeekHigh")
    private Double fiftyTwoWeekHigh;
    private LocalDate fiftyTwoWeekHighDate;
    @JsonProperty("52WeekLow")
    private Double fiftyTwoWeekLow;
    private LocalDate fiftyTwoWeekLowDate;
    @JsonProperty("52WeekPriceReturnDaily")
    private Double fiftyTwoWeekPriceReturnDaily;
    @JsonProperty("5DayPriceReturnDaily")
    private Double fiveDayPriceReturnDaily;
    @JsonProperty("assetTurnoverAnnual")
    private Double assetTurnoverAnnual;
    @JsonProperty("assetTurnoverTTM")
    private Double assetTurnoverTTM;
    @JsonProperty("beta")
    private Double beta;
    @JsonProperty("bookValuePerShareAnnual")
    private Double bookValuePerShareAnnual;
    @JsonProperty("bookValuePerShareQuarterly")
    private Double bookValuePerShareQuarterly;
    @JsonProperty("bookValueShareGrowth5Y")
    private Double bookValueShareGrowth5Y;
    @JsonProperty("capexCagr5Y")
    private Double capexCagr5Y;
    @JsonProperty("cashFlowPerShareAnnual")
    private Double cashFlowPerShareAnnual;
    @JsonProperty("cashFlowPerShareQuarterly")
    private Double cashFlowPerShareQuarterly;
    @JsonProperty("cashFlowPerShareTTM")
    private Double cashFlowPerShareTTM;
     @JsonProperty("cashPerSharePerShareAnnual")
    private Double cashPerSharePerShareAnnual;
     @JsonProperty("cashPerSharePerShareQuarterly")
    private Double cashPerSharePerShareQuarterly;
    @JsonProperty("currentDividendYieldTTM")
    private Double currentDividendYieldTTM;
    @JsonProperty("currentEv/freeCashFlowAnnual")
    private Double currentEvFreeCashFlowAnnual;
    @JsonProperty("currentEv/freeCashFlowTTM")
    private Double currentEvFreeCashFlowTTM;
    @JsonProperty("currentRatioAnnual")
    private Double currentRatioAnnual;
    @JsonProperty("currentRatioQuarterly")
    private Double currentRatioQuarterly;
    @JsonProperty("dividendGrowthRate5Y")
    private Double dividendGrowthRate5Y;
    @JsonProperty("dividendPerShareAnnual")
    private Double dividendPerShareAnnual;
    @JsonProperty("dividendPerShareTTM")
    private Double dividendPerShareTTM;
    @JsonProperty("dividendYieldIndicatedAnnual")
    private Double dividendYieldIndicatedAnnual;
    @JsonProperty("ebitdPerShareAnnual")
    private Double ebitdPerShareAnnual;
    @JsonProperty("ebitdPerShareTTM")
    private Double ebitdPerShareTTM;
    @JsonProperty("ebitdaCagr5Y")
    private Double ebitdaCagr5Y;
    @JsonProperty("ebitdaInterimCagr5Y")
    private Double ebitdaInterimCagr5Y;
    @JsonProperty("enterpriseValue")
    private Double enterpriseValue;
    @JsonProperty("epsAnnual")
    private Double epsAnnual;
    @JsonProperty("epsBasicExclExtraItemsAnnual")
    private Double epsBasicExclExtraItemsAnnual;
    @JsonProperty("epsBasicExclExtraItemsTTM")
    private Double epsBasicExclExtraItemsTTM;
    @JsonProperty("epsExclExtraItemsAnnual")
    private Double epsExclExtraItemsAnnual;
    @JsonProperty("epsExclExtraItemsTTM")
    private Double epsExclExtraItemsTTM;
    @JsonProperty("epsGrowth3Y")
    private Double epsGrowth3Y;
    @JsonProperty("epsGrowth5Y")
    private Double epsGrowth5Y;
    @JsonProperty("epsGrowthQuarterlyYoy")
    private Double epsGrowthQuarterlyYoy;
    @JsonProperty("epsGrowthTTMYoy")
    private Double epsGrowthTTMYoy;
    @JsonProperty("epsInclExtraItemsAnnual")
    private Double epsInclExtraItemsAnnual;
    @JsonProperty("epsInclExtraItemsTTM")
    private Double epsInclExtraItemsTTM;
    @JsonProperty("epsNormalizedAnnual")
    private Double epsNormalizedAnnual;
    @JsonProperty("epsTTM")
    private Double epsTTM;
    @JsonProperty("focfCagr5Y")
    private Double focfCagr5Y;
    @JsonProperty("grossMargin5Y")
    private Double grossMargin5Y;
    @JsonProperty("grossMarginAnnual")
    private Double grossMarginAnnual;
    @JsonProperty("grossMarginTTM")
    private Double grossMarginTTM;
    @JsonProperty("inventoryTurnoverAnnual")
    private Double inventoryTurnoverAnnual;
    @JsonProperty("inventoryTurnoverTTM")
    private Double inventoryTurnoverTTM;
    @JsonProperty("longTermDebt/equityAnnual")
    private Double longTermDebtEquityAnnual;
    @JsonProperty("longTermDebt/equityQuarterly")
    private Double longTermDebtEquityQuarterly;
    @JsonProperty("marketCapitalization")
    private Double marketCapitalization;
    @JsonProperty("monthToDatePriceReturnDaily")
    private Double monthToDatePriceReturnDaily;
    @JsonProperty("netIncomeEmployeeAnnual")
    private Double netIncomeEmployeeAnnual;
    @JsonProperty("netIncomeEmployeeTTM")
    private Double netIncomeEmployeeTTM;
    @JsonProperty("netInterestCoverageAnnual")
    private Double netInterestCoverageAnnual;
    @JsonProperty("netInterestCoverageTTM")
    private Double netInterestCoverageTTM;
    @JsonProperty("netMarginGrowth5Y")
    private Double netMarginGrowth5Y;
    @JsonProperty("netProfitMargin5Y")
    private Double netProfitMargin5Y;
    @JsonProperty("netProfitMarginAnnual")
    private Double netProfitMarginAnnual;
    @JsonProperty("netProfitMarginTTM")
    private Double netProfitMarginTTM;
    @JsonProperty("operatingMargin5Y")
    private Double operatingMargin5Y;
    @JsonProperty("operatingMarginAnnual")
    private Double operatingMarginAnnual;
    @JsonProperty("operatingMarginTTM")
    private Double operatingMarginTTM;
    @JsonProperty("payoutRatioAnnual")
    private Double payoutRatioAnnual;
    @JsonProperty("payoutRatioTTM")
    private Double payoutRatioTTM;
    @JsonProperty("pbAnnual")
    private Double pbAnnual;
    @JsonProperty("pbQuarterly")
    private Double pbQuarterly;
    @JsonProperty("pcfShareAnnual")
    private Double pcfShareAnnual;
    @JsonProperty("pcfShareTTM")
    private Double pcfShareTTM;
    @JsonProperty("peAnnual")
    private Double peAnnual;
    @JsonProperty("peBasicExclExtraTTM")
    private Double peBasicExclExtraTTM;
    @JsonProperty("peExclExtraAnnual")
    private Double peExclExtraAnnual;
    @JsonProperty("peExclExtraTTM")
    private Double peExclExtraTTM;
    @JsonProperty("peInclExtraTTM")
    private Double peInclExtraTTM;
    @JsonProperty("peNormalizedAnnual")
    private Double peNormalizedAnnual;
    @JsonProperty("peTTM")
    private Double peTTM;
    @JsonProperty("pfcfShareAnnual")
    private Double pfcfShareAnnual;
    @JsonProperty("pfcfShareTTM")
    private Double pfcfShareTTM;
    @JsonProperty("pretaxMargin5Y")
    private Double pretaxMargin5Y;
    @JsonProperty("pretaxMarginAnnual")
    private Double pretaxMarginAnnual;
    @JsonProperty("pretaxMarginTTM")
    private Double pretaxMarginTTM;
    @JsonProperty("priceRelativeToS&P50013Week")
    private Double priceRelativeToSP50013Week;
    @JsonProperty("priceRelativeToS&P50026Week")
    private Double priceRelativeToSP50026Week;
    @JsonProperty("priceRelativeToS&P5004Week")
    private Double priceRelativeToSP5004Week;
    @JsonProperty("priceRelativeToS&P50052Week")
    private Double priceRelativeToSP50052Week;
    @JsonProperty("priceRelativeToS&P500Ytd")
    private Double priceRelativeToSP500Ytd;
    @JsonProperty("psAnnual")
    private Double psAnnual;
    @JsonProperty("psTTM")
    private Double psTTM;
    @JsonProperty("ptbvAnnual")
    private Double ptbvAnnual;
    @JsonProperty("ptbvQuarterly")
    private Double ptbvQuarterly;
    @JsonProperty("quickRatioAnnual")
    private Double quickRatioAnnual;
    @JsonProperty("quickRatioQuarterly")
    private Double quickRatioQuarterly;
    @JsonProperty("receivablesTurnoverAnnual")
    private Double receivablesTurnoverAnnual;
    @JsonProperty("receivablesTurnoverTTM")
    private Double receivablesTurnoverTTM;
    @JsonProperty("revenueEmployeeAnnual")
    private Double revenueEmployeeAnnual;
    @JsonProperty("revenueEmployeeTTM")
    private Double revenueEmployeeTTM;
    @JsonProperty("revenueGrowth3Y")
    private Double revenueGrowth3Y;
    @JsonProperty("revenueGrowth5Y")
    private Double revenueGrowth5Y;
    @JsonProperty("revenueGrowthQuarterlyYoy")
    private Double revenueGrowthQuarterlyYoy;
    @JsonProperty("revenueGrowthTTMYoy")
    private Double revenueGrowthTTMYoy;
    @JsonProperty("revenuePerShareAnnual")
    private Double revenuePerShareAnnual;
    @JsonProperty("revenuePerShareTTM")
    private Double revenuePerShareTTM;
    @JsonProperty("revenueShareGrowth5Y")
    private Double revenueShareGrowth5Y;
    @JsonProperty("roa5Y")
    private Double roa5Y;
    @JsonProperty("roaRfy")
    private Double roaRfy;
    @JsonProperty("roaTTM")
    private Double roaTTM;
    @JsonProperty("roe5Y")
    private Double roe5Y;
    @JsonProperty("roeRfy")
    private Double roeRfy;
    @JsonProperty("roeTTM")
    private Double roeTTM;
    @JsonProperty("roi5Y")
    private Double roi5Y;
    @JsonProperty("roiAnnual")
    private Double roiAnnual;
    @JsonProperty("roiTTM")
    private Double roiTTM;
    @JsonProperty("tangibleBookValuePerShareAnnual")
    private Double tangibleBookValuePerShareAnnual;
    @JsonProperty("tangibleBookValuePerShareQuarterly")
    private Double tangibleBookValuePerShareQuarterly;
    @JsonProperty("tbvCagr5Y")
    private Double tbvCagr5Y;
    @JsonProperty("totalDebt/totalEquityAnnual")
    private Double totalDebtTotalEquityAnnual;
    @JsonProperty("totalDebt/totalEquityQuarterly")
    private Double totalDebtTotalEquityQuarterly;
    @JsonProperty("yearToDatePriceReturnDaily")
    private Double yearToDatePriceReturnDaily;

    public Stock toEntity() {
        Stock stock = new Stock();
        stock.setSymbol(this.symbol);
        stock.setFiftyTwoWeekPriceReturnDaily(this.fiftyTwoWeekPriceReturnDaily);
        stock.setFiveDayPriceReturnDaily(this.fiveDayPriceReturnDaily);
        stock.setBeta(this.beta);
        stock.setCurrentRatioQuarterly(this.currentRatioQuarterly);
        stock.setDividendYieldIndicatedAnnual(this.dividendYieldIndicatedAnnual);
        stock.setEpsGrowthTTMYoy(this.epsGrowthTTMYoy);
        stock.setMarketCapitalization(this.marketCapitalization);
        stock.setPbQuarterly(this.pbQuarterly);
        stock.setPeTTM(this.peTTM);
        stock.setRevenueGrowthTTMYoy(this.revenueGrowthTTMYoy);
        stock.setRoaTTM(this.roaTTM);
        stock.setRoeTTM(this.roeTTM);
        stock.setTotalDebtTotalEquityQuarterly(this.totalDebtTotalEquityQuarterly);
        return stock;
    }
}
