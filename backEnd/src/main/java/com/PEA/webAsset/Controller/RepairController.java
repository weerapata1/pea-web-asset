package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbRepair;
import com.PEA.webAsset.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/repair")
@CrossOrigin(origins = "*")
public class RepairController {
    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RepairStatusRepository repairStatusRepository;
    @Autowired
    private CostCenterRepository costCenterRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private EmpAdminRepository empAdminRepository;



    @GetMapping("/getAllRepair")
    public Collection<tbRepair> getAllCC() {
        return repairRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/getByLocation")
    public Collection<tbRepair> getByLocation(@RequestParam("location") String location) {
        return repairRepository.findDeviceRepairByLocation(location);
    }

    @GetMapping("/getByStatusId")
    public Collection<tbRepair> getByStatusId(@RequestParam("status") int status) {
        return repairRepository.findDeviceRepairByStatusId(status);
    }

    @GetMapping("/getByLocAndSta")
    public Collection<tbRepair> getByLocationAndState(@RequestParam("location") String location, @RequestParam("status") int status) {
        return repairRepository.findDeviceRepairByLocationAndState(location, status);
    }

    @PostMapping("/repair")
    public ResponseEntity<tbRepair> createRepair(@RequestParam("empSend") String empSend, @RequestParam("damage") String damage,
                                                 @RequestParam("device") Long device) {
        tbRepair newRepair = new tbRepair();

        LocalDateTime dateTimeNow = LocalDateTime.now();

        newRepair.setSendDate(dateTimeNow);
        newRepair.setDamageDetail(damage);
        newRepair.setEmpSend(employeeRepository.findByEmpId(empSend));
        newRepair.setRepairStatus(repairStatusRepository.findStatusById(1L));
        newRepair.setDevice(deviceRepository.findDeviceById(device));

        final tbRepair repair = repairRepository.save(newRepair);
        return new ResponseEntity<>(repair, HttpStatus.CREATED);
    }

    @PutMapping("/updateStatusSec")
    public ResponseEntity<tbRepair> updateStatusSec(@RequestParam("id") Long id, @RequestParam("adName") String adName
            , @RequestParam("examine") String examine) {

        tbRepair updateRepair = repairRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This device is not found"));

        LocalDateTime dateTimeNow = LocalDateTime.now();

        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(2L));
        updateRepair.setAdminReceive(empAdminRepository.findAllByAdName(adName));
        updateRepair.setExamineDamage(examine);
        updateRepair.setAdmitDate(dateTimeNow);

        final tbRepair repair = repairRepository.save(updateRepair);
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }

    @PutMapping("/updateStatusThd")
    public ResponseEntity<tbRepair> updateStatusThd(@RequestParam("id")Long id,@RequestParam("treat")String treat) {
        tbRepair updateRepair = repairRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This device is not found"));

        LocalDateTime dateTimeNow = LocalDateTime.now();

        updateRepair.setTreatment(treat);
        updateRepair.setTreatComplete(dateTimeNow);
        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(3L));

        final tbRepair repair = repairRepository.save(updateRepair);
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }

    @PutMapping("/updateStatusFur")
    public ResponseEntity<tbRepair> updateStatusFur(@RequestParam("id")Long id,@RequestParam("emp") String emp) {
        tbRepair updateRepair = repairRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This device is not found"));

        LocalDateTime dateTimeNow = LocalDateTime.now();

        updateRepair.setReturnEmp(emp);
        updateRepair.setReturnDate(dateTimeNow);
        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(4L));

        final tbRepair repair = repairRepository.save(updateRepair);
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }

}
