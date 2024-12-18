package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Entity.tbRepair;
import com.PEA.webAsset.Repository.*;
import com.PEA.webAsset.Share.DateService.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static com.PEA.webAsset.Service.RepairService.CategoryOfItEquipment;

@RestController
@RequestMapping("/repair")
@CrossOrigin(origins = "*")
public class RepairController {
    @Autowired
    private final RepairRepository repairRepository;
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final RepairStatusRepository repairStatusRepository;
    @Autowired
    private final CostCenterRepository costCenterRepository;
    @Autowired
    private final DeviceRepository deviceRepository;

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

    @GetMapping("/getAllRepair")
    public Collection<tbRepair> getAllCC() {
        return repairRepository.findAll();
    }


    //get repair from cost center
    @GetMapping("/getRepairByCostCenter/{costCenter}")
    public ResponseEntity getRepairByCostCenter(@PathVariable(name = "costCenter")String costCenter){
        try {
            Optional<tbRepair> findRepairByCostCenter = Optional.ofNullable(repairRepository.findDeviceRepairByLocation(costCenter));
            if(findRepairByCostCenter.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(findRepairByCostCenter);
            }
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Repair Device Is Not Found From " + costCenter);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @GetMapping("/getRepair/{RepairId}")
    public ResponseEntity getRepairByRepairId(@PathVariable(name = "RepairId")String RepairId) {
        Optional<tbRepair> repairTemp = Optional.ofNullable(repairRepository.findRepairByRepairNoId(RepairId));
        if(repairTemp.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(repairTemp);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Repair Device Is Not Found " + RepairId);
    }

//    @GetMapping("/getByLocation")
//    public Collection<tbRepair> getByLocation(@RequestParam("location") String location) {
//        return repairRepository.findDeviceRepairByLocation(location);
//    }

    @PostMapping("/createRepairReport/{peaNo}")
    public ResponseEntity createRepairReport(@RequestBody tbRepair repair, @PathVariable(name= "peaNo") String peaNo){
        try {
            if(CategoryOfItEquipment(peaNo)){
                Optional<tbDevice> deviceTemp = Optional.ofNullable(this.deviceRepository.findAllByDevPeaNo(peaNo));
                if(deviceTemp.isPresent()){
                    tbRepair newRepair = new tbRepair();
                    newRepair.setSendPhoneNum(repair.getSendPhoneNum());
                    newRepair.setSendDate(DateService.localDateNow());
                    newRepair.setDamageDetail(repair.getDamageDetail());
                    newRepair.setRepairStatus(repairStatusRepository.findStatusById(1L));
                    newRepair.setDevice(deviceRepository.findAllByDevPeaNo(peaNo));

                    tbRepair newRepairs = repairRepository.save(newRepair);
                    return new ResponseEntity<>(newRepairs ,HttpStatus.CREATED);
                }
                else {
                    System.out.println("Can not Create Repair Device Is Not Found " + peaNo);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not Create Repair Device Is Not Found " + peaNo);
                }
            }else {
                System.out.println("Can not Create Repair PEA_Number Is Not Collect " + peaNo);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not Create Repair PEA_Number Is Not Collect " + peaNo);
            }

        } catch (Exception e) {
            throw new RuntimeException("can not createRepairReport : " + e.getMessage());
        }

    }



    @GetMapping("/getByStatusId")
    public Collection<tbRepair> getRepairByRepairStatus(@RequestParam("status") int status) {
//        tbRepair findRepairByRepairStatus = repairRepository.findDeviceRepairByRepairStatusId(status);

        return repairRepository.findDeviceRepairByRepairStatusId(status);
    }

    @PutMapping("updateRepairStatusInprogress/{RepairId}")
    public ResponseEntity updateRepairStatusInprogress(@PathVariable(name = "RepairId")String RepairId, @RequestParam String causesOfDamage){

        Optional<tbRepair>repairTemp = Optional.ofNullable(repairRepository.findRepairByRepairNoId(RepairId));
        if(repairTemp.isPresent()){
            tbRepair updateRepair = repairRepository.findRepairByRepairNoId(RepairId);

            updateRepair.setRepairStatus(repairStatusRepository.findStatusById(1L));
            updateRepair.setCausesOfDamage(causesOfDamage);
            updateRepair.setAdmitDate(DateService.localDateNow());

            final Optional<tbRepair> repair = Optional.of(repairRepository.save(updateRepair));

            return ResponseEntity.status(HttpStatus.OK).body(repair);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Repair Device Is Not Found " + RepairId);
    }
    
    @PutMapping("modifyRepairComplete/{RepairId}")
    public ResponseEntity modifyRepairComplete(@PathVariable(name = "RepairId")String RepairId, @RequestParam String treatmentSolution){

        Optional<tbRepair>repairTemp = Optional.ofNullable(repairRepository.findRepairByRepairNoId(RepairId));
        if(repairTemp.isPresent()){
            tbRepair updateRepair = repairRepository.findRepairByRepairNoId(RepairId);

            updateRepair.setRepairStatus(repairStatusRepository.findStatusById(2L));
            updateRepair.setTreatmentSolution(treatmentSolution);
            updateRepair.setTreatCompleteDate(DateService.localDateNow());

            final Optional<tbRepair> repair = Optional.of(repairRepository.save(updateRepair));

            return ResponseEntity.status(HttpStatus.OK).body(repair);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Repair Device Is Not Found " + RepairId);
    }

    @PutMapping("updateRepairDone/{RepairId}")
    public ResponseEntity updateRepairDone(@PathVariable(name = "RepairId")String RepairId, @RequestParam String returnEmp){

        try{
            Optional<tbRepair>repairTemp = Optional.ofNullable(repairRepository.findRepairByRepairNoId(RepairId));
            if(repairTemp.isPresent()){
                tbRepair updateRepair = repairRepository.findRepairByRepairNoId(RepairId);


                updateRepair.setRepairStatus(repairStatusRepository.findStatusById(3L));
                updateRepair.setReturnEmp(returnEmp);
                updateRepair.setReturnDate(DateService.localDateNow());

                final Optional<tbRepair> repair = Optional.of(repairRepository.save(updateRepair));

                return ResponseEntity.status(HttpStatus.OK).body(repair);
            }
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Repair Device Is Not Found " + RepairId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}
