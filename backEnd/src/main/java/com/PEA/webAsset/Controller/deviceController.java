package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping
public class deviceController {
    @Autowired
    DeviceRepository deviceRepository;

    @GetMapping("/device")
    public Collection<tbDevice> getAllDevices() {
        System.out.println(">> " + deviceRepository.findAll());
        return deviceRepository.findAll();
    }


    @GetMapping("/tests")
    public Collection<tbDevice> getEmpTestx(){
        return deviceRepository.findAllDevicesNative();
    }

}
