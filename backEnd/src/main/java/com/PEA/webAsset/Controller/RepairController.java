package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Entity.tbRepair;
import com.PEA.webAsset.Entity.tbRepairStatus;
import com.PEA.webAsset.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
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
        return repairRepository.findAll();
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
                                                 @RequestParam("devicePeaNO") String devicePeaNO, @RequestParam("empPhoneNumb") String empPhoneNumb) {
        tbRepair newRepair = new tbRepair();

        LocalDateTime dateTimeNow = LocalDateTime.now();

        newRepair.setSendDate(dateTimeNow);
        newRepair.setDamageDetail(damage);
        newRepair.setEmpSend(employeeRepository.findByEmpId(empSend));
        newRepair.setRepairStatus(repairStatusRepository.findStatusById(1L));
        newRepair.setDevice(deviceRepository.findDeviceByDevPeaNo(devicePeaNO));
        newRepair.setEmpPhoneNumb(empPhoneNumb);

        System.out.println("create repair : " + devicePeaNO + " by : " + empSend + " complete");

        final tbRepair repair = repairRepository.save(newRepair);
        return new ResponseEntity<>(repair, HttpStatus.CREATED);
    }

//    @GetMapping("/findStatusById")
//    public List<tbRepairStatus> getStatus() {
//        return repairStatusRepository.findAll();
//    }
//
    @PutMapping("/updateStatusSec/{repairId}") //
    public ResponseEntity<tbRepair> updateStatusSec(@PathVariable("repairId") String repairId, @RequestParam String adUserName, @RequestParam String cause) {


        tbRepair updateRepair = repairRepository.findRepairByRepairId(repairId);

        LocalDateTime dateTimeNow = LocalDateTime.now();

        System.out.println("adminUser : " + adUserName + " causeId : " + cause);

        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(2L));
        updateRepair.setAdminReceive(empAdminRepository.findAllByAdminUserName(adUserName));
        updateRepair.setCause(cause);
        updateRepair.setAdmitDate(dateTimeNow);

        final tbRepair repair = repairRepository.save(updateRepair);
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }


    @PutMapping("/updateStatusThd/{repairId}") //
    public ResponseEntity<tbRepair> updateStatusThd(@PathVariable("repairId") String repairId, @RequestParam("treat") String treat) {
        tbRepair updateRepair = repairRepository.findRepairByRepairId(repairId);

        LocalDateTime dateTimeNow = LocalDateTime.now();

        updateRepair.setTreatment(treat);
        updateRepair.setTreatComplete(dateTimeNow);
        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(3L));
        System.out.println("treat : " + treat);

        final tbRepair repair = repairRepository.save(updateRepair);
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }

    @PutMapping("/updateStatusFur/{repairId}") //
    public ResponseEntity<tbRepair> updateStatusFur(@PathVariable("repairId") String repairId, @RequestParam("returnEmp") String returnEmp) {

        tbRepair updateRepair = repairRepository.findRepairByRepairId(repairId);
        LocalDateTime dateTimeNow = LocalDateTime.now();

        tbEmployee findEmpID = employeeRepository.findByEmpId(returnEmp);
        if (findEmpID == null) {
            throw new ResourceNotFoundException(("this EmpID : " + returnEmp + " is not found"));
        }

        System.out.println("returnEmp : " + returnEmp);

        updateRepair.setReturnEmp(findEmpID);
        updateRepair.setReturnDate(dateTimeNow);
        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(4L));

        final tbRepair repair = repairRepository.save(updateRepair);
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }

    @GetMapping("/getHistByPeaNo")
    public ResponseEntity<Collection<tbRepair>> getHistByPeaNo(@RequestParam("PeaNo") Long PeaNo) {
        try {
            Collection<tbRepair> getRepair = repairRepository.findByDeviceId(PeaNo).stream().collect(Collectors.toList());
            return new ResponseEntity<>(getRepair, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity("this device is don't have a history of repaired", HttpStatus.NOT_FOUND);
        }

    }



}
