package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class DeviceController {
    @Autowired
    DeviceRepository deviceRepository;

    @GetMapping("/getAllDevi")
    public Collection<tbDevice> getAllDevi(){
        return deviceRepository.findAll();
    }

    @GetMapping("/getSearch")
    public Collection<tbDevice> getSearch(){
        return deviceRepository.findAll();
    }
}
