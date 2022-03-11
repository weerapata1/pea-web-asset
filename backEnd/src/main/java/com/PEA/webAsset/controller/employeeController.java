package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/getEmpAll")
    public List<tbEmployee> getEmpAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/getByEmpId/{id}")
    public tbEmployee getEmpByEmpId(@PathVariable("id") String id) {
        return employeeRepository.findEmployeeByEmpId(id);
    }

}

