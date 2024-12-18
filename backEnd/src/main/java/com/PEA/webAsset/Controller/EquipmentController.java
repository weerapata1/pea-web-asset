package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbEquipment;
import com.PEA.webAsset.Repository.DeviceRepository;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
import com.PEA.webAsset.Repository.EquipmentLocationRepository;
import com.PEA.webAsset.Repository.EquipmentRepository;
import com.PEA.webAsset.Share.DateService.DateService;
import com.PEA.webAsset.Service.EquipmentService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.module.ResolutionException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/equip")
public class EquipmentController {
    @Autowired
    private final EquipmentRepository equipmentRepository;

    @Autowired
    private final DeviceRepository deviceRepository;

    @Autowired
    private final DeviceTypeRepository deviceTypeRepository;

    @Autowired
    private final EquipmentLocationRepository equipmentLocationRepository;

    @Autowired
    private EquipmentService equipmentService;

    public  EquipmentController(EquipmentRepository equipmentRepository, DeviceRepository deviceRepository, DeviceTypeRepository deviceTypeRepository, EquipmentLocationRepository equipmentLocationRepository){
        this.equipmentRepository = equipmentRepository;
        this.deviceRepository = deviceRepository;
        this.deviceTypeRepository = deviceTypeRepository;
        this.equipmentLocationRepository = equipmentLocationRepository;
    }

    @GetMapping("/getAllEquip")
    public ResponseEntity getAllEquip(){
        Collection<tbEquipment> equipments =  equipmentRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(equipments);
    }

    @GetMapping("/getQuantity/{dType}")//            find Equipment Quantity By DeviceType and Detail
    public ResponseEntity getQuantityOfEquipment(@PathVariable("dType")String dType){
        // เช็ดประเภทอุปกรณ์ที่คงเหลือใน stock พร้องจ่าย โดยตึงค่าเป็นประเภท และสามารถดูได้ว่ามีอะไรอยู่บ้าง S/N ไหน
        try{
            Object qtyEquipment = equipmentService.getQtyEquipment(dType);
            return ResponseEntity.status(HttpStatus.OK).body(qtyEquipment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error "+ e.getMessage());
        }

    }

    @PostMapping("/addEquipment") // เพิ่มอุปกรณ์
    public ResponseEntity addEquipment(@RequestParam("file") MultipartFile file ,@RequestParam("dType")Long dType ,@RequestParam("locId")Long locId ) throws Exception{
        try{
            System.out.println(file);
            equipmentService.addEquipmentFormExcel(file ,dType ,locId);
            return ResponseEntity.status(HttpStatus.OK).body("up load finish");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import file: " + e.getMessage());
        }
    }

    @PutMapping("/requisition") // เบิกอุปกรณ์
    public ResponseEntity createRequisition(@RequestParam("serialNo")String serialNo ,@RequestParam("usedFor")String usedFor){
        try{tbEquipment newCreateRequisition = equipmentRepository.findEquipmentBySerialNumber(serialNo).orElseThrow(()-> new ResolutionException("Not Found id"));
            System.out.println(newCreateRequisition);

            newCreateRequisition.setIsActivated(false);
            newCreateRequisition.setInstalledFor(deviceRepository.findDeviceByDevPeaNo(usedFor));
            newCreateRequisition.setUsedDate(DateService.localDateNow());

            return ResponseEntity.status(HttpStatus.OK).body(equipmentRepository.save(newCreateRequisition));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
        }

    }


}
