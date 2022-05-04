package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Entity.tbRepair;
import com.PEA.webAsset.Repository.EmployeeRepository;
import com.PEA.webAsset.Repository.RepairRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/repair")
public class RepairController {
    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/getAll")
    public ResponseEntity<tbRepair> getAllRepair(){
        return new ResponseEntity(repairRepository.findAll(),HttpStatus.OK);
    }

//    @GetMapping("/getRepairByEmp")
//    public Collection<tbRepair> getRepairByEmp(@RequestParam("empId") String empId){
//        return repairRepository.findAll().(empId);
//    }
}
