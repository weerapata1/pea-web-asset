package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbEmpAdmin;
import com.PEA.webAsset.Repository.EmpAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empAdmin")
public class EmpAdminController {
    @Autowired
    private EmpAdminRepository empAdminRepository;

    @GetMapping("/getAllAd")
    public Collection<tbEmpAdmin> getAllAd(){
        return empAdminRepository.findAll().stream().collect(Collectors.toList());
    }
}
