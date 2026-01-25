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

    @GetMapping("/getAllRepair")
    public Collection<tbRepair> getAllCC() {
        return repairRepository.findAll();
    }

    @GetMapping("/getRepairAct")
    public Collection<tbRepair> getAllRepairIsActive() {
        return repairRepository.findAllByIsActive(1L);
    }


    @GetMapping("/getRepairByRepairCode")
    public ResponseEntity<Collection<tbRepair>> getRepairById(@RequestBody RepairFindWithRepairCodeOrDevPeaNoDTO findRepairDTO) {
        Collection<tbRepair> repairs = repairRepository.findRepairByRepairCodeContainingOrDevice_DevPeaNoContaining(findRepairDTO.getRepairCode(), findRepairDTO.getDevPeaNo());
        return new ResponseEntity<>(repairs, HttpStatus.OK);
    }

    @GetMapping("/getByRepairCodeOrPeaNO")
    public ResponseEntity<Collection<tbRepair>> getRepairByRepairCode(@RequestParam String textSearch) {
        Collection<tbRepair> findRepair = repairRepository.findByRepairCodeOrPeaNoAndActStatus(textSearch);
//        System.out.println("findRepair = " + findRepair);
        return new ResponseEntity<>(findRepair, HttpStatus.OK);
    }

    //    if find Device is found add value else return not found >> add detail  >> save
    @PostMapping("/createRepair")
    public ResponseEntity<tbRepair> createRepair(@RequestBody RepairRequestsDTO repair) {

        tbRepair newRepair = new tbRepair();

//        System.out.println("repair ::>>" + repair);

        newRepair.setDefectDetail(repair.getDefectDetail()); // << set basic Defect
        newRepair.setEmpSend(repair.getEmpSend()); // << set phone number of sender
        newRepair.setSendPhoneNum(repair.getSendPhoneNum()); // << set Phone Number
        newRepair.setRepairCode(GeneratePrefixAndRepairCode()); // << set repairCode
        newRepair.setRepairStatus(repairStatusRepository.findRepairStatusById(1L)); // << set status new Repair
        newRepair.setCreatedDate(LocalDateTime.now()); // << set Date Admit
        newRepair.setLastModifyDate(LocalDateTime.now()); // << set Date Last Modify
//        newRepair.setAssignedTo(employeeRepository.findByEmpId(repair.getAssignedTo())); // << in final findDctAdminByPositionRole *****
        newRepair.setDevice(deviceRepository.findAllByDevPeaNo(repair.getPeaNo())); // findDeviceByPeaNo and set device
        newRepair.setIsActive(activeStatusRepository.findActiveStatusByActiveId(1L)); // set isActive

        repairRepository.save(newRepair);
//        System.out.println("repair : >> " + newRepair);

        return ResponseEntity.ok(newRepair);
    }

    //    searchRepair with repair status
    @GetMapping("/getRepairByStatus")
    public ResponseEntity<Collection<tbRepair>> getRepairByStatus(@RequestParam(name = "status") String status) {
        Collection<tbRepair> searchRepair = repairRepository.findByRepairStatus(status);


        return new ResponseEntity<>(searchRepair, HttpStatus.OK);
    }

    @PutMapping("/updateRepair")
    public ResponseEntity<Optional<tbRepair>> updateRepair(@RequestBody RepairUpdateRequestDTO updateDTO) {
//        find repairCode or PeaNo. for update status and detail if found ,update status and detail ,else return not found
        Optional<tbRepair> repairTemp = Optional.ofNullable(repairRepository.findByRepairCode(updateDTO.getTextSearch()));

        if (repairTemp.isPresent()) {

            tbRepair updateRepair = repairRepository.findByRepairCode(updateDTO.getTextSearch());

            switch (updateDTO.getRepairStatus()) {
                case "2": // in progress Repair
                    updateRepair.setAdminDefectReview(updateDTO.getAdminDefectReview()); // << set admin defect review
                    updateRepair.setRepairStatus((repairStatusRepository.findRepairStatusById(2L))); // << set status Ready for Delivery
                    updateRepair.setLastModifyDate(LocalDateTime.now()); // << set Date Last Modify
                    updateRepair.setAssignedTo(employeeRepository.findByEmpId(updateDTO.getAssignedTo())); // << set assignedTo name
                    System.out.println("status 2");
                    break;
                case "3": // ready for Delivery
                    updateRepair.setRepairStatus((repairStatusRepository.findRepairStatusById(3L)));// << set status device has been returned
                    updateRepair.setFixMethod(updateDTO.getFixMethod()); // << set method of repair
                    updateRepair.setLastModifyDate(LocalDateTime.now()); // << set Date Last Modify
                    updateRepair.setCostOfRepair(updateDTO.getCostOfRepair()); // << set cost of repair
                    System.out.println("status 3");
                    break;
                case "4": // returned Device
                    updateRepair.setRepairStatus((repairStatusRepository.findRepairStatusById(4L)));// << set status device has been returned
                    updateRepair.setConsigneeName(employeeRepository.findByEmpId(updateDTO.getConsigneeName())); // << set name of consignee
                    updateRepair.setLastModifyDate(LocalDateTime.now()); // << set Date Last Modify
                    System.out.println("Consignee Name: " + updateDTO.getConsigneeName());
                    System.out.println("status 4");
                    break;

                case "5":
                    updateRepair.setRepairStatus((repairStatusRepository.findRepairStatusById(5L)));// << set status done
                    updateRepair.setClosedDate(LocalDateTime.now()); // << set Date Close Repair
                    updateRepair.setIsActive(activeStatusRepository.findActiveStatusByActiveId(2L));// << hide form listRepair
                    System.out.println("status 5");
                    break;
            }
            final tbRepair repair = repairRepository.save(updateRepair);
            System.out.println("repair ::>> " + repair);
            return ResponseEntity.ok().build();
        } else {
            System.out.println("not found");
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
