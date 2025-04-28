package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbCostCenter;
import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.EmployeeRepository;
import com.PEA.webAsset.Service.EmployeeService;
import com.PEA.webAsset.Share.ResponseMessage;
import com.PEA.webAsset.Share.ExcelService.ExcelHelper;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/emp")
// @CrossOrigin(origins = "*")

public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;
    private final CostCenterRepository costCenterRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CostCenterRepository costCenterRepository) {
        this.employeeService = employeeService;
        this.costCenterRepository = costCenterRepository;
    }

    @GetMapping("/getEmpAll")
    public Collection<tbEmployee> getEmpAll() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/getEmpAll2")
    public ResponseEntity<Map<String, Object>> getEmpAll2() {
        try {
            List<Object[]> device = new ArrayList<Object[]>();
            Pageable paging = Pageable.unpaged();

            Page<Object[]> pageTuts = employeeRepository.getEmpAll2(paging);
            device = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            response.put("data1", device);
            // response.put("itemsPerPage", size);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/getEmpId")
    // public tbEmployee getEmpId(@RequestParam("empId") String empId) {
    // return employeeRepository.findByEmpId(empId);
    // }

    // @GetMapping("/getEmployeeIdTest")
    // public HttpEntity<Optional<tbEmployee>> getEmployeeIdTest(@RequestBody
    // tbEmployee emp){
    // Optional<tbEmployee> find =
    // employeeRepository.findEmpByEmpId(emp.getEmpId());

    // return new ResponseEntity<>(find,HttpStatus.OK);
    // }

    @GetMapping("/getEmployeeId")
    public ResponseEntity<Object> getEmployeeId(@RequestParam("empId") String empId) {
        String message = "data";
        try {
            tbEmployee employee = employeeRepository.findEmpByEmpId(empId)
                    .orElseThrow(() -> new ResourceNotFoundException("NotFound"));
            return new ResponseEntity<>(employee, HttpStatus.OK);
            // return new ResponseEntity.status(HttpStatus.OK).body(new
            // ResponseMessage(message));
        } catch (ResourceNotFoundException e) {
            System.out.println("notFound");
            return new ResponseEntity<>("notFound", HttpStatus.OK);

        }
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

    @GetMapping("/getEmpRule")
    public ResponseEntity getEmpRuleByEmpId() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findEmpRule(1L));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail : " + e.getMessage());
        }

    }

    @GetMapping("/getEmpByccLongCode")
    public ResponseEntity<Map<String, Object>> Patternunpage(@RequestParam("region") String region) {
        System.out.println(region);
        try {
            List<Object[]> employee = new ArrayList<Object[]>();
            Pageable paging = Pageable.unpaged();
            Page<Object[]> pageTuts = employeeRepository.findEmployeeByCcId(region, paging);

            employee = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put("totalItems", pageTuts.getTotalElements());

            response.put("dataEmployee", employee);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateRule")
    public ResponseEntity<tbEmployee> updateRule() {
        try {
            employeeRepository.updateEmpRule();
            System.out.println("update user rule without admin");

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // throw new RuntimeException(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadEmployees(@RequestParam("file") MultipartFile file) {
        String message;
        System.out.println("process /emp/upload");
        // Validate file type (e.g., Excel)
        if (!ExcelHelper.hasExcelFormat(file)) {
            message = "Please upload a valid Excel file!";
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(message));
        }
        try {
            // Process and save the uploaded file
            List<tbEmployee> employees = employeeService.saveEmployeesFromFile(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessage(message + " (" + employees.size() + " records processed)"));
        } catch (RuntimeException e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(message));
        }
    }

    @GetMapping("/test-cost-center")
    public ResponseEntity<?> testCostCenter(@RequestParam("code") String ccLongCode) {
        Optional<tbCostCenter> costCenter = costCenterRepository.findByCcLongCode(ccLongCode);

        if (costCenter.isPresent()) {
            return ResponseEntity.ok("Cost Center Found: " + costCenter.get().getCcLongCode());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cost center not found for code: " + ccLongCode);
        }
    }

    @GetMapping("/getInspectorList")
    public ResponseEntity<Map<String, Object>> getInspectorList() {
        try {
            List<Object[]> inspectorList = new ArrayList<Object[]>();
            Pageable paging = Pageable.unpaged();

            Page<Object[]> pageTuts = employeeRepository.getInspectorList(paging);
            inspectorList = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            response.put("data", inspectorList);
            // response.put("itemsPerPage", size);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
