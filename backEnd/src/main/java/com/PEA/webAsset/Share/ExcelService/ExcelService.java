package com.PEA.webAsset.Share.ExcelService;

import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.DeviceRepository;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;


@Service
public class ExcelService {

    @Autowired DeviceTypeRepository deviceTypeRepository;
    @Autowired DeviceRepository deviceRepository;
    @Autowired CostCenterRepository costCenterRepository;

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy");


}
