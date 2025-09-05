package com.PEA.webAsset.dto;

import java.math.BigDecimal;

public class Cost60ByUserDTO {
    private String username;
    private java.math.BigDecimal valuePerUser;
    private java.math.BigDecimal recordsPerUser;

    public Cost60ByUserDTO(String username, java.math.BigDecimal valuePerUser, java.math.BigDecimal recordsPerUser) {
        this.username = username;
        this.valuePerUser = valuePerUser;
        this.recordsPerUser = recordsPerUser;
    }

    public String getUsername() { return username; }
    public java.math.BigDecimal getValuePerUser() { return valuePerUser; }
    public java.math.BigDecimal getRecordsPerUser() { return recordsPerUser; }
}