package com.PEA.webAsset.controller;

import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
//@CrossOrigin(origins = "*")
public class employeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/Emps")
    public List<tbEmployee> EmpTest(){
        return employeeRepository.findAll();
    }

    @GetMapping("/Emp")
    public String getEmpTest(){
        return "Test";
    }
}
