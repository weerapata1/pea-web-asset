package com.PEA.webAsset.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TempDeviceInterface {

    public interface TempDeviceSummary {
        String getDevConcatPriceDate();

        String getDevDescription();

        String getDevReceivedDate();

        BigDecimal getDevReceivedPrice();
    }

}

