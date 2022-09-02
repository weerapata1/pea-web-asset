package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Repository.EmployeeRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "*")

public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/getEmp")
    public List<tbEmployee> getEmpAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/getEmpId")
    public tbEmployee getEmpId(@RequestParam("id") String id) {
        return employeeRepository.findByEmpId(id);
    }

    @GetMapping("/getEmpSor")
    public ResponseEntity<Map<String, Object>> getEmpSor(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "3") int size) {
        try {
            List<tbEmployee> employee = new ArrayList<tbEmployee>();
            Pageable paging = PageRequest.of(page, size);

            Page<tbEmployee> pageTuts = employeeRepository.findAll(paging);
            employee = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            response.put("data", employee);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmpByccLongCode")
    public ResponseEntity<Map<String, Object>> Patternunpage(@RequestParam("region") String region
    ) {
        try {
            List<tbEmployee> employee = new ArrayList<tbEmployee>();
            Pageable paging = Pageable.unpaged();
            Page<tbEmployee> pageTuts = employeeRepository.findEmployeeByCcId(region, paging);

            employee = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put("totalItems", pageTuts.getTotalElements());

            response.put("dataEmployee", employee);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
