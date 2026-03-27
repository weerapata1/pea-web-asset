package com.PEA.webAsset.dto;

import java.math.BigDecimal;

public class Cost60ByMonthDTO {
    private String yearMonth;
    private java.math.BigDecimal valuePerMonth;
    private java.math.BigDecimal recordsPerMonth;

    public Cost60ByMonthDTO(String yearMonth, java.math.BigDecimal valuePerMonth, java.math.BigDecimal recordsPerMonth) {
        this.yearMonth = yearMonth;
        this.valuePerMonth = valuePerMonth;
        this.recordsPerMonth = recordsPerMonth;
    }

    public String getYearMonth() { return yearMonth; }
    public java.math.BigDecimal getValuePerMonth() { return valuePerMonth; }
    public java.math.BigDecimal getRecordsPerMonth() { return recordsPerMonth; }
}