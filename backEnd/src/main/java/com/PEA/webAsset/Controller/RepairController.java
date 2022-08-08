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
    @Autowired
    private CauseRepository causeRepository;



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

    @PutMapping("/updateStatusSec/{repairId}") //
    public ResponseEntity<tbRepair> updateStatusSec(@PathVariable("repairId") Long repairId, @RequestParam String adminName,@RequestParam Long causeId) {


        tbRepair updateRepair = repairRepository.findById(repairId).orElseThrow(() -> new ResourceNotFoundException("This device is not found"));

        LocalDateTime dateTimeNow = LocalDateTime.now();

        System.out.println("adminName : " + adminName + " causeId : " + causeId);

        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(2L));
        updateRepair.setAdminReceive(empAdminRepository.findAllByAdName(adminName));
        updateRepair.setCause(causeRepository.findCauseById(causeId));
        updateRepair.setAdmitDate(dateTimeNow);

        final tbRepair repair = repairRepository.save(updateRepair);
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }

    @PutMapping("/updateStatusThd/{repairId}") //
    public ResponseEntity<tbRepair> updateStatusThd(@PathVariable("repairId")Long repairId,@RequestParam("treat")String treat) {
        tbRepair updateRepair = repairRepository.findById(repairId).orElseThrow(() -> new ResourceNotFoundException("This device is not found"));

        LocalDateTime dateTimeNow = LocalDateTime.now();

        updateRepair.setTreatment(treat);
        updateRepair.setTreatComplete(dateTimeNow);
        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(3L));
        System.out.println("treat : " + treat);

        final tbRepair repair = repairRepository.save(updateRepair);
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }

    @PutMapping("/updateStatusFur/{repairId}") //
    public ResponseEntity<tbRepair> updateStatusFur(@PathVariable("repairId")Long repairId,@RequestParam("returnEmp") String returnEmp) {
        tbRepair updateRepair = repairRepository.findById(repairId).orElseThrow(() -> new ResourceNotFoundException("This device is not found"));

        LocalDateTime dateTimeNow = LocalDateTime.now();

        System.out.println("returnEmp : " + returnEmp);
        updateRepair.setReturnEmp(returnEmp);
        updateRepair.setReturnDate(dateTimeNow);
        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(4L));

        final tbRepair repair = repairRepository.save(updateRepair);
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }

}
