package com.PEA.webAsset.controller;

import java.util.List;

import com.PEA.webAsset.Entity.tbData;
import com.PEA.webAsset.Repository.DataRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class dataController {
    @Autowired
    private DataRepo DataRepo;

    @GetMapping("/data")
    public List<tbData> DatatableTest(){
        System.out.println(">>>"+DataRepo.findAll());
        return DataRepo.findAll();
    }
    
}
