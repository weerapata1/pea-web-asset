package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbCostCenterTest;
import com.PEA.webAsset.Entity.tbRepair;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.EmployeeRepository;
import com.PEA.webAsset.Repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/repair")
@CrossOrigin(origins = "*")
public class RepairController {
    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CostCenterRepository costCenterRepository;

    @GetMapping("/getAllRepair")
    public Collection<tbRepair> getAllCC() {
        return repairRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/getByLocation")
    public Collection<tbRepair> getByLocation(@RequestParam("location")String location){
        return repairRepository.findByLocation(location);
    }

}
