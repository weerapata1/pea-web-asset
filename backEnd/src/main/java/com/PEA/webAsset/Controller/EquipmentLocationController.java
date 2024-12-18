package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Repository.EquipmentLocationRepository;
import com.PEA.webAsset.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/EquipLoc")
public class EquipmentLocationController {
    @Autowired
    private final EquipmentLocationRepository equipmentLocationRepository;

    @Autowired
    private final EquipmentRepository equipmentRepository;


    public EquipmentLocationController(EquipmentLocationRepository equipmentLocationRepository, EquipmentRepository equipmentRepository) {
        this.equipmentLocationRepository = equipmentLocationRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @GetMapping("/getAllEquipLoc")
    public ResponseEntity getAllEquipLoc(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(equipmentLocationRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error : " + e.getMessage());
        }

    }
}
