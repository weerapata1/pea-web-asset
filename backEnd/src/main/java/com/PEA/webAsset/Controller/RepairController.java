package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbRepair;
import com.PEA.webAsset.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

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

    // @Autowired
    // private CauseRepository causeRepository;

    public RepairController(RepairRepository repairRepository, EmployeeRepository employeeRepository,
                            RepairStatusRepository repairStatusRepository, CostCenterRepository costCenterRepository,
                            DeviceRepository deviceRepository
                            ){
        this.repairRepository = repairRepository;
        this.employeeRepository = employeeRepository;
        this.repairStatusRepository = repairStatusRepository;
        this.costCenterRepository = costCenterRepository;
        this.deviceRepository = deviceRepository;

    }

    private LocalDate localDateNow() {
        LocalDate dateNow = LocalDate.now();
        return dateNow;
    }


    @GetMapping("/getAllRepair")
    public Collection<tbRepair> getAllCC() {
        return repairRepository.findAll();
    }

    @GetMapping("/getByLocation")
    public Collection<tbRepair> getByLocation(@RequestParam("location") String location) {
        return repairRepository.findDeviceRepairByLocation(location);
    }

    @PostMapping("/createRepairReport/{peaNo}")
    public ResponseEntity<tbRepair> createRepairReport(@RequestBody tbRepair repair, @PathVariable(name= "peaNo") String peaNo){
        try {
            tbRepair newRepair = new tbRepair();
            newRepair.setSendPhoneNum(repair.getSendPhoneNum());
            newRepair.setSendDate(localDateNow());
            newRepair.setCausesOfDamage(repair.getCausesOfDamage());
            newRepair.setRepairStatus(repairStatusRepository.findStatusById(1L));
            newRepair.setDevice(deviceRepository.findDeviceByDevPeaNo(peaNo));
//            newRepair.setEmpSend(employeeRepository.findById(1L));

//            System.out.println("5555+ : "+ repair.getEmpSend());
            tbRepair newRepairs = repairRepository.save(newRepair);
//            System.out.println("createRepairReport " + newRepair.getDevice());
            return new ResponseEntity<>(newRepairs ,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("can not createRepairReport : " + e.getMessage());
        }

    }



    @GetMapping("/getByStatusId")
    public Collection<tbRepair> getRepairByRepairStatus(@RequestParam("status") int status) {
//        tbRepair findRepairByRepairStatus = repairRepository.findDeviceRepairByRepairStatusId(status);

        return repairRepository.findDeviceRepairByRepairStatusId(status);
    }
//
//
//    @GetMapping("/getByLocAndSta")
//    public Collection<tbRepair> getByLocationAndState(@RequestParam("location") String location, @RequestParam("status") int status) {
//        return repairRepository.findDeviceRepairByLocationAndState(location, status);
//    }


//    @PostMapping("/repair")
//    public ResponseEntity<tbRepair> createRepair(@RequestParam("empSend") String empSend, @RequestParam("damage") String damage,
//                                                 @RequestParam("devicePeaNO") String devicePeaNO, @RequestParam("empPhoneNumb") String empPhoneNumb) {
//        tbRepair newRepair = new tbRepair();
//
//        LocalDate dateNow = LocalDate.now();
//
//        newRepair.setSendDate(dateNow);
//        newRepair.setDamageDetail(damage);
//        newRepair.setEmpSend(employeeRepository.findByEmpId(empSend));
//        newRepair.setRepairStatus(repairStatusRepository.findStatusById(1L));
//        newRepair.setDevice(deviceRepository.findDeviceByDevPeaNo(devicePeaNO));
//        newRepair.setEmpPhoneNumb(empPhoneNumb);
//
//        System.out.println("create repair : " + devicePeaNO + " by : " + empSend + " complete");
//
//        final tbRepair repair = repairRepository.save(newRepair);
//        return new ResponseEntity<>(repair, HttpStatus.CREATED);
//    }
//
////    @GetMapping("/findStatusById")
////    public List<tbRepairStatus> getStatus() {
////        return repairStatusRepository.findAll();
////    }
////
//    @PutMapping("/updateStatusSec/{repairId}") //
//    public ResponseEntity<tbRepair> updateStatusSec(@PathVariable("repairId") String repairId, @RequestParam String adUserName, @RequestParam String cause) {
//
//
//        tbRepair updateRepair = repairRepository.findRepairByRepairId(repairId);
//
//        LocalDate dateNow = LocalDate.now();
//
//        System.out.println("adminUser : " + adUserName + " causeId : " + cause);
//
//        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(2L));
//        updateRepair.setAdminReceive(empAdminRepository.findAllByAdminUserName(adUserName));
//        updateRepair.setCause(cause);
//        updateRepair.setAdmitDate(dateNow);
//
//        final tbRepair repair = repairRepository.save(updateRepair);
//        return new ResponseEntity<>(repair, HttpStatus.OK);
//    }
//
//
//    @PutMapping("/updateStatusThd/{repairId}") //
//    public ResponseEntity<tbRepair> updateStatusThd(@PathVariable("repairId") String repairId, @RequestParam("treat") String treat) {
//        tbRepair updateRepair = repairRepository.findRepairByRepairId(repairId);
//
//        LocalDate dateNow = LocalDate.now();
//
//        updateRepair.setTreatment(treat);
//        updateRepair.setTreatComplete(dateNow);
//        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(3L));
//        System.out.println("treat : " + treat);
//
//        final tbRepair repair = repairRepository.save(updateRepair);
//        return new ResponseEntity<>(repair, HttpStatus.OK);
//    }
//
//    @PutMapping("/updateStatusFur/{repairId}") //
//    public ResponseEntity<tbRepair> updateStatusFur(@PathVariable("repairId") String repairId, @RequestParam("returnEmp") String returnEmp) {
//
//        tbRepair updateRepair = repairRepository.findRepairByRepairId(repairId);
//        LocalDate dateNow = LocalDate.now();
//
//        tbEmployee findEmpID = employeeRepository.findByEmpId(returnEmp);
//        if (findEmpID == null) {
//            throw new ResourceNotFoundException(("this EmpID : " + returnEmp + " is not found"));
//        }
//
//        System.out.println("returnEmp : " + returnEmp);
//
//        updateRepair.setReturnEmp(findEmpID);
//        updateRepair.setReturnDate(dateNow);
//        updateRepair.setRepairStatus(repairStatusRepository.findStatusById(4L));
//
//        final tbRepair repair = repairRepository.save(updateRepair);
//        return new ResponseEntity<>(repair, HttpStatus.OK);
//    }

//    @GetMapping("/getHistByPeaNo")
//    public ResponseEntity<Collection<tbRepair>> getHistByPeaNo(@RequestParam("PeaNo") Long PeaNo) {
//        try {
//            Collection<tbRepair> getRepair = repairRepository.findByDeviceId(PeaNo).stream().collect(Collectors.toList());
//            return new ResponseEntity<>(getRepair, HttpStatus.OK);
//        } catch (ResourceNotFoundException e) {
//            return new ResponseEntity("this device is don't have a history of repaired", HttpStatus.NOT_FOUND);
//        }
//
//    }

}
