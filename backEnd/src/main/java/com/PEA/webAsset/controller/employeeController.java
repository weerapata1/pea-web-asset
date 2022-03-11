package com.PEA.webAsset.controller;

import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
//@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/Emps")
    public List<tbEmployee> EmpTest(){
        return employeeRepository.findAll();
    }

    @GetMapping("/Emp")
    public String getEmpTest(){
        //return "Test";
        //return "{\"success\":1}";
        return "{\"total\": 200}";
    }

    @RequestMapping(value = "/getString", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getString() {
        return Collections.singletonMap("response", "Hello World");
    }

}
