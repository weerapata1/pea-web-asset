package com.PEA.webAsset.dto;

public class CountDeviceByDepDTO {

    private String deviceId;

    private String devPeaNo;

    private String devDescription;

    private String devReceivedDate;

    private String empName;

    private String empRank;

    private String ccShortName;

    private String ccLongCode;

    private String divisionCode;

    private java.math.BigDecimal divisionCount;

    private java.math.BigDecimal departmentCount;

    public CountDeviceByDepDTO(String deviceId, String devPeaNo, String devDescription, String devReceivedDate, String empName, String empRank,
            String ccShortName, String ccLongCode, String divisionCode, java.math.BigDecimal divisionCount,
            java.math.BigDecimal departmentCount) {
        this.deviceId = deviceId;
        this.devPeaNo = devPeaNo;
        this.devDescription = devDescription;
        this.devReceivedDate = devReceivedDate;
        this.empName = empName;
        this.empRank = empRank;
        this.ccShortName = ccShortName;
        this.ccLongCode = ccLongCode;
        this.divisionCode = divisionCode;
        this.divisionCount = divisionCount;
        this.departmentCount = departmentCount;
    }

    public String getDeviceId() { return deviceId; }
    public String getDevPeaNo() { return devPeaNo; }
    public String getDevDescription() { return devDescription; }
    public String getDevReceivedDate() { return devReceivedDate; }
    public String getEmpName() { return empName; }
    public String getEmpRank() { return empRank; }
    public String getCcShortName() { return ccShortName; }
    public String getCcLongCode() { return ccLongCode; }
    public String getDivisionCode() { return divisionCode; }
    public java.math.BigDecimal getDivisionCount() { return divisionCount; }
    public java.math.BigDecimal getDepartmentCount() { return departmentCount; }
}
