package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbCostUse60;
import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Interface.Cost60Interface;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.CostUse60Repository;
import com.PEA.webAsset.Service.EmployeeService;
import com.PEA.webAsset.Share.ResponseMessage;
import com.PEA.webAsset.dto.Cost60ByMonthDTO;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cost")
public class CostUse60Controller {
    @Autowired
    CostUse60Repository costUse60Repository;

    // @Autowired
    // public CostUse60Controller() {

    // }

    @GetMapping("/cost60ByMonth")
    public ResponseEntity<ResponseMessage> cost60ByMonth() {
        try {
            Pageable paging = Pageable.unpaged();
            Page<Cost60Interface.Cost60ByMonth> pageResult = costUse60Repository.cost60GroupByMonth(paging);
            // List<Cost60ByMonthDTO> rows = costUse60Repository.cost60GroupByMonth();

            List<Cost60ByMonthDTO> rows = pageResult.getContent().stream()
                    .map(p -> new Cost60ByMonthDTO(
                            p.getYearMonth(),
                            p.getValuePerMonth(),
                            p.getRecordsPerMonth()))
                    .collect(java.util.stream.Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageResult.getNumber());
            response.put("totalItems", pageResult.getTotalElements());
            response.put("totalPages", pageResult.getTotalPages());
            response.put("data", rows);

            return ResponseEntity.ok(new ResponseMessage(true, "Fetched " + rows.size() + " records.", response));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(false, "Error fetching data", e));
        }
    }

    @GetMapping("/cost60ByUser")
    public ResponseEntity<ResponseMessage> cost60ByUser() {
        try {
            Pageable paging = Pageable.unpaged();
            Page<Cost60Interface.Cost60ByUser> pageResult = costUse60Repository.cost60GroupByUser(paging);
            // List<Cost60ByMonthDTO> rows = costUse60Repository.cost60GroupByMonth();

            List<Cost60ByMonthDTO> rows = pageResult.getContent().stream()
                    .map(p -> new Cost60ByMonthDTO(
                            p.getUsername(),
                            p.getValuePerUsername(),
                            p. getRecordsPerUsername()))
                    .collect(java.util.stream.Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageResult.getNumber());
            response.put("totalItems", pageResult.getTotalElements());
            response.put("totalPages", pageResult.getTotalPages());
            response.put("data", rows);

            return ResponseEntity.ok(new ResponseMessage(true, "Fetched " + rows.size() + " records.", response));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(false, "Error fetching data", e));
        }
    }

    @GetMapping("/getAllCost60")
    public Collection<tbCostUse60> getAllCost60() {
        return costUse60Repository.findAll().stream().collect(Collectors.toList());
    }
}
