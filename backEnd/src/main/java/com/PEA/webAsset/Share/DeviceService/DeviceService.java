package com.PEA.webAsset.Share.DeviceService;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DeviceService {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy");

    @Autowired
    CostCenterRepository costCenterRepository;
    @Autowired
    DeviceRepository deviceRepository;

    public void postDevice(String dev_serialNo, String dev_note, String dev_description
            , String dev_peaNo, String tbCostCenter
//                           ,String dateTimeNow ,String dateNow
    ) {
        String dateTimeTemp = now.format(dateTimeFormat);
        String dateTemp = now.format(dateFormat);
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeTemp, dateTimeFormat);
        LocalDate date = LocalDate.parse(dateTemp, dateFormat);

        try {
            tbDevice newDevice = new tbDevice();

            newDevice.setDev_peaNo(dev_peaNo);
            newDevice.setDev_serialNo(dev_serialNo);
            newDevice.setDev_note(dev_note);
            newDevice.setDev_description(dev_description);
            newDevice.setDev_update(dateTime);
            newDevice.setDev_received(date);

            newDevice.setTbCostCenter(costCenterRepository.findByCcLongCode(tbCostCenter));

            deviceRepository.save(newDevice);
        } catch (Exception e) {
            throw new RuntimeException("POST Fail : " + e.getMessage());
        }

    }

}
