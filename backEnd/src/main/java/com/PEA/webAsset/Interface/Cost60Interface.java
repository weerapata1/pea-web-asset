package com.PEA.webAsset.Interface;

public class Cost60Interface {
    public interface Cost60ByMonth {
        // String year_and_month();
        // java.math.BigDecimal value_per_month();
        // java.math.BigDecimal records_per_month();
        String getYearMonth();
        java.math.BigDecimal getValuePerMonth();
        java.math.BigDecimal getRecordsPerMonth();
    }

    public interface Cost60ByUser {
        // String year_and_month();
        // java.math.BigDecimal value_per_month();
        // java.math.BigDecimal records_per_month();
        String getUsername();
        java.math.BigDecimal getValuePerUsername();
        java.math.BigDecimal getRecordsPerUsername();
    }
}