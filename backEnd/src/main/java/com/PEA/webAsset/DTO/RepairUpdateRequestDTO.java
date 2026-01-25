package com.PEA.webAsset.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RepairUpdateRequestDTO {
    private String repairCode;
    private String adminDefectReview;
    private String repairStatus;
    private String fixMethod;
    private BigDecimal costOfRepair;
    private String textSearch;
    private String assignedTo;
    private String ConsigneeName;
}
