package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Repository.*;
import com.PEA.webAsset.Share.DeviceService.DeviceService;
import com.PEA.webAsset.Share.ExcelService.ExcelHelper;
import com.PEA.webAsset.Share.ExcelService.ExcelService;
import com.PEA.webAsset.Share.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/dev")
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private CommitmentRepository commitmentRepository;
    @Autowired
    private DeviceTypeRepository dtRepository;
    @Autowired
    CostCenterRepository costCenterRepository;
    @Autowired
    ExcelService excelService;
    @Autowired
    DeviceService deviceService;

    @GetMapping("/getAllDevice")
    public ResponseEntity<Map<String, Object>> getEmpSor(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "3") int size) {
        try {
            List<tbDevice> device = new ArrayList<tbDevice>();
            Pageable paging = PageRequest.of(page, size);

            Page<tbDevice> pageTuts = deviceRepository.findAll(paging);
            device = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            response.put("data1", device);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByCCCode")
    public Collection<tbDevice> getByCCCode() {
        return deviceRepository.findByCCCode();
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        String message = "";
        if (ExcelHelper.hasExcelFormat(files)) {

            try {
                List<tbDevice> deviceList = excelService.saveDevice(files);
                deviceRepository.saveAll(deviceList);

                message = "Uploaded the file successfully: " + files.getOriginalFilename() + "\n";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + files.getOriginalFilename() + "! \n\t" + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PostMapping("/posT")
    public ResponseEntity<ResponseMessage> posT(@RequestParam("dev_serialNo") String dev_serialNo,
                                                @RequestParam("dev_note") String dev_note,
                                                @RequestParam("dev_description") String dev_description,
                                                @RequestParam("dev_peaNo") String dev_peaNo,
                                                @RequestParam("tbCostCenter") String tbCostCenter
    ) {


        String message;
        try {
            deviceService.postDevice(dev_serialNo, dev_note, dev_description, dev_peaNo, tbCostCenter);
            message = "POST OK \n";
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "POST NOT OK : " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }
    }




}