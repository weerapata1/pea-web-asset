package com.PEA.webAsset.Interface;

public class DeviceInterface {

    public interface countDeviceByDep {

        String getDeviceId();

        String getDevPeaNo();

        String getDevDescription();

        String getDevReceivedDate();

        String getEmpName();

        String getEmpRank();

        String getCcShortName();

        String getCcLongCode();

        String getDivisionCode();

        java.math.BigDecimal getDivisionCount();

        java.math.BigDecimal getDepartmentCount();
    }
}
