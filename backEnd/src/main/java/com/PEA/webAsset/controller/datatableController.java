package com.PEA.webAsset.controller;

import com.PEA.webAsset.Entity.tbDatatable;
import com.PEA.webAsset.Entity.tbDatatable_format;
import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Repository.DatatableRepository;
import com.PEA.webAsset.Repository.EmployeeRepository;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.PEA.webAsset.Repository.DatatableFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class datatableController {
    @Autowired
    private DatatableRepository datatableRepository;
    @Autowired
    private DatatableFormatRepository datatableFormatRepository;

    @GetMapping("/Datatable")
    public List<tbDatatable> DatatableTest(){
        return datatableRepository.findAll();
    }

    @GetMapping("/DataFormat")
    public List<tbDatatable_format> DatatableFormat(){
        return datatableFormatRepository.findAll();
    }

    // @GetMapping("/DataFormatA")
    // public List<tbDatatable_format> DatatableFormat2(){

    //     // List<tbDatatable_format> dataSet = new ArrayList<tbDatatable_format>();
    //     // tbDatatable_format objt = new  tbDatatable_format();
    //     // objt = (tbDatatable_format) datatableFormatRepository.findAll();
    //     // dataSet.add(objt);

    //     return JsonDeserializer<>                 datatableFormatRepository.findAll();
        
    // }

    // @GetMapping("/DataFormatB")
    // public List<String> DatatableFormat3(){

    //     // List<tbDatatable_format> dataSet = new ArrayList<tbDatatable_format>();
    //     // tbDatatable_format objt = new  tbDatatable_format();
    //     // objt = (tbDatatable_format) datatableFormatRepository.findAll();
    //     // dataSet.add(objt);

    //     return datatableFormatRepository.findAll();
        
    // }

    // @GetMapping("/employees/{id}")
    // public ResponseEntity<tbDatatable_format> DatatableFormat3(@PathVariable(value = "id") Long employeeId){
    //     return employeeService.findById(employeeId);
    // }

    // @PostMapping("/cars")
    //    public Vehicle createCar(@Valid @RequestBody Vehicle vehicle, @Valid @RequestParam(required = false) LocalDate carOut){
    
    //        System.out.println(vehicle);
    //        return vehicleRepository.save(vehicle);
    //    }

}
