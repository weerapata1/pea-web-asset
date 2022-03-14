package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbCostCenter;
import com.PEA.webAsset.Repository.CostCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class CostCenterController {
    @Autowired
    CostCenterRepository costCenterRepository;

    @GetMapping("/getAllCC")
    public Collection<tbCostCenter> getAllCC(){
        return costCenterRepository.findAll().stream().collect(Collectors.toList());
    }

}
