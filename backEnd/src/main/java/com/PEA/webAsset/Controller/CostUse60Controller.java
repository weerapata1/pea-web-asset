package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbCostUse60;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.CostUse60Repository;
import com.PEA.webAsset.Service.EmployeeService;
import com.PEA.webAsset.Share.ResponseMessage;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cost")
public class CostUse60Controller {
    @Autowired
    CostUse60Repository costUse60Repository;

    // @Autowired
    // public CostUse60Controller() {

    // }

    @GetMapping("/getAllCost60")
    public Collection<tbCostUse60> getAllCost60() {
        return costUse60Repository.findAll().stream().collect(Collectors.toList());
    }
}
