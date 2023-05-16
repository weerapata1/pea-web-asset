package com.PEA.webAsset.Mockup;

import com.PEA.webAsset.Entity.tbDeviceType;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class My21DeviceType {
    @Autowired
    public void run(ApplicationArguments args){}

    @Bean
    public ApplicationRunner MyDeviceType(DeviceTypeRepository deviceTypeRepository){
        return (args -> {
            Stream.of("Computer or labtop","Tablet","Monitor","Printer","UPS","อุปกรณ์สื่อสาร","อุปกรณ์ประกอบหรืออุปกรณ์อื่นๆ",
            "Software","Other").forEach(typeName ->{
                tbDeviceType tempDeviceType = new tbDeviceType();
                tempDeviceType.setDeviceTypeName(typeName);
                deviceTypeRepository.save(tempDeviceType);
            });
        });
    }
}
