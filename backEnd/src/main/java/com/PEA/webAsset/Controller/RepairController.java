package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbRepair;
import com.PEA.webAsset.DTO.RepairFindWithRepairCodeOrDevPeaNoDTO;
import com.PEA.webAsset.DTO.RepairRequestsDTO;
import com.PEA.webAsset.DTO.RepairUpdateRequestDTO;
import com.PEA.webAsset.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import static com.PEA.webAsset.Service.RepairService.GeneratePrefixAndRepairCode;

@RestController
@RequestMapping("/repair" )
@CrossOrigin(origins = "*" )
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
    @Autowired
    private final ActiveStatusRepository activeStatusRepository;

    public RepairController(RepairRepository repairRepository, EmployeeRepository employeeRepository,
                            RepairStatusRepository repairStatusRepository, CostCenterRepository costCenterRepository,
                            ActiveStatusRepository activeStatusRepository, DeviceRepository deviceRepository, ActiveStatusRepository activeStatusRepository1
    ) {
        this.repairRepository = repairRepository;
        this.employeeRepository = employeeRepository;
        this.repairStatusRepository = repairStatusRepository;
        this.costCenterRepository = costCenterRepository;
        this.deviceRepository = deviceRepository;

        this.activeStatusRepository = activeStatusRepository;
    }

    @GetMapping("/getAllRepair" )
    public Collection<tbRepair> getAllCC() {
        return repairRepository.findAll();
    }

    @GetMapping("/getRepairByRepairCode" )
    public ResponseEntity<Collection<tbRepair>> getRepairById(@RequestBody RepairFindWithRepairCodeOrDevPeaNoDTO findRepairDTO) {
        Collection<tbRepair> repairs = repairRepository.findRepairByRepairCodeContainingOrDevice_DevPeaNoContaining(findRepairDTO.getRepairCode(), findRepairDTO.getDevPeaNo());
        return new ResponseEntity<>(repairs, HttpStatus.OK);
    }

    @GetMapping("/getByRepairCodeOrPeaNO" )
    public ResponseEntity<Collection<tbRepair>> getRepairByRepairCode(@RequestBody RepairFindWithRepairCodeOrDevPeaNoDTO findRepairDTO) {
        Collection<tbRepair> findRepair = repairRepository.findByRepairCodeOrPeaNoAndActStatus(findRepairDTO.getTextSearch());
        System.out.println("findRepair = " + findRepair);
        return new ResponseEntity<>(findRepair, HttpStatus.OK);
    }

    //    if find Device is found add value else return not found >> add detail  >> save
    @PostMapping("/createRepair" )
    public ResponseEntity<tbRepair> createRepair(@RequestBody RepairRequestsDTO repair) {

        tbRepair newRepair = new tbRepair();

        System.out.println("repair ::>>" + repair);

        newRepair.setDefectDetail(repair.getDefectDetail()); // << set basic Defect
        newRepair.setEmpSend(repair.getEmpSend()); // << set phone number of sender
        newRepair.setSendPhoneNum(repair.getSendPhoneNum()); // << set Phone Number
        newRepair.setRepairCode(GeneratePrefixAndRepairCode()); // << set repairCode
        newRepair.setRepairStatus(repairStatusRepository.findRepairStatusById(1L)); // << set status InProgress
        newRepair.setAdmitDate(LocalDateTime.now()); // << set Date Admit
        newRepair.setLastModifyDate(LocalDateTime.now()); // << set Date Last Modify
        newRepair.setAdminReceive(repair.getAdminReceive()); // << in final findDctAdminByPositionRole *****
        newRepair.setDevice(deviceRepository.findAllByDevPeaNo(repair.getPeaNo())); // findDeviceByPeaNo and set device
        newRepair.setIsActive(activeStatusRepository.findActiveStatusByActiveId(1L));


        repairRepository.save(newRepair);
        System.out.println("repair : >> " + newRepair);

        return ResponseEntity.ok(newRepair);
    }

    @PutMapping("/updateRepair" )
    public ResponseEntity<Optional<tbRepair>> updateRepair(@RequestBody RepairUpdateRequestDTO updateDTO) {
//        find repairCode or PeaNo. for update status and detail if found ,update status and detail ,else return not found
        Optional<tbRepair> repairTemp = Optional.ofNullable(repairRepository.findByRepairCode(updateDTO.getTextSearch()));

        if (repairTemp.isPresent()) {

            tbRepair updateRepair = repairRepository.findByRepairCode(updateDTO.getTextSearch());
            updateRepair.setLastModifyDate(LocalDateTime.now()); // << set Date Last Modify

            switch (updateDTO.getRepairStatus()) {
                case "2": // Ready4Delivery
                    updateRepair.setAdminDefectReview(updateDTO.getAdminDefectReview());
                    updateRepair.setFixMethod(updateDTO.getFixMethod());
                    updateRepair.setCostOfRepair(updateDTO.getCostOfRepair());
                    updateRepair.setRepairStatus((repairStatusRepository.findRepairStatusById(2L)));
                    updateRepair.setIsActive(activeStatusRepository.findActiveStatusByActiveId(1L));
                    break;
                case "3": // The device has been returned.
                    updateRepair.setRepairStatus((repairStatusRepository.findRepairStatusById(3L)));
                    updateRepair.setIsActive(activeStatusRepository.findActiveStatusByActiveId(2L));
                    break;

            }
            final tbRepair repair = repairRepository.save(updateRepair);
            return ResponseEntity.ok(Optional.of(repair));
        } else {
            System.out.println("not found" );
            return ResponseEntity.ok(repairTemp);
        }
    }




//    @PutMapping("updateRepairDone/{RepairId}")
//    public ResponseEntity updateRepairDone(@PathVariable(name = "RepairId")String RepairId, @RequestParam String returnEmp){
//
//        try{
//            Optional<tbRepair>repairTemp = Optional.ofNullable(repairRepository.findRepairByRepairNoId(RepairId));
//            if(repairTemp.isPresent()){
//                tbRepair updateRepair = repairRepository.findRepairByRepairNoId(RepairId);
//
//
//                updateRepair.setRepairStatus(repairStatusRepository.findStatusById(3L));
//                updateRepair.setReturnEmp(returnEmp);
//                updateRepair.setReturnDate(DateService.localDateNow());
//
//                final Optional<tbRepair> repair = Optional.of(repairRepository.save(updateRepair));
//
//                return ResponseEntity.status(HttpStatus.OK).body(repair);
//            }
//            else
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Repair Device Is Not Found " + RepairId);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//
//    }


}
