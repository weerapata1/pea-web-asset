package com.PEA.webAsset.Interface;

import java.math.BigDecimal;
import java.sql.Date;

public class TempDeviceInterface {

    public interface TempDeviceSummary {
        String getDevConcatPriceDate();

        String getDevDescription();

        String getDevReceivedDate();

        BigDecimal getDevReceivedPrice();
    }

}

