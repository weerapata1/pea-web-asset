package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDevice;
import lombok.Data;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "device", types = {tbDevice.class})
public interface DeadlineType {
    String getDev_serialNo();
    String getDev_peaNo();
    String getDev_note();
    String getDev_description();
    String getCc_short_name();
    String getCc_long_code();
    String getEmpName();
    String getEmpId();

}
