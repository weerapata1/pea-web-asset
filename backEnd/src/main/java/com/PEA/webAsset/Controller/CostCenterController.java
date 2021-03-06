package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbCostCenterTest;
import com.PEA.webAsset.Repository.CostCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/cc")
public class CostCenterController {
    @Autowired
    CostCenterRepository costCenterRepository;

    @GetMapping("/getAllCC")
    public Collection<tbCostCenterTest> getAllCC() {
        return costCenterRepository.findAll().stream().collect(Collectors.toList());
    }

//    @GetMapping("/getByCC")
//    public ResponseEntity<>
}
