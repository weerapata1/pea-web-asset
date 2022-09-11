package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbCostCenterTest;
import com.PEA.webAsset.Repository.CostCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@RestController
// @CrossOrigin(origins ="*")
@CrossOrigin("*")
@RequestMapping("/cc")
public class CostCenterController {
    @Autowired
    CostCenterRepository costCenterRepository;

    @GetMapping("/getAllCC")
    public Collection<tbCostCenterTest> getAllCC() {
        return costCenterRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/getAllCCOnlyUse")
    public ResponseEntity<Map<String, Object>> getAllCCOnlyUse() {
        try {
            List<tbCostCenterTest> costCenter = new ArrayList<tbCostCenterTest>();
            Pageable paging = Pageable.unpaged();
      
            Page<tbCostCenterTest> pageTuts = costCenterRepository.onlyUse(paging);
            costCenter = pageTuts.getContent();
      
            Map<String, Object> response = new HashMap<>();
            // response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            response.put("costCenter", costCenter);
            // response.put("itemsPerPage", size);
            return new ResponseEntity<>(response, HttpStatus.OK);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
//    @GetMapping("/getByCC")
//    public ResponseEntity<>
}
