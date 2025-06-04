package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Entity.TempDevice;
import com.PEA.webAsset.Exeption.InvalidDataException;
import com.PEA.webAsset.Interface.SyncResultDTO;
import com.PEA.webAsset.Interface.TempDeviceInterface;
// import com.PEA.webAsset.Repository.CommitmentRepository;
import com.PEA.webAsset.Repository.ContractRepository;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.DeviceRepository;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
import com.PEA.webAsset.Repository.TempDeviceRepository;
import com.PEA.webAsset.Share.DeviceService.DeviceService;
import com.PEA.webAsset.Share.ExcelService.ExcelHelper;
import com.PEA.webAsset.Share.ExcelService.ExcelService;
import com.PEA.webAsset.Share.ResponseMessage;
import com.PEA.webAsset.Service.TempDeviceService;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// @CrossOrigin("*")
@RestController
@RequestMapping("/api/dev")
public class TempDeviceController {
  @Autowired
  private DeviceRepository deviceRepository;

  // @Autowired
  // private CommitmentRepository commitmentRepository;
  @Autowired
  private ContractRepository commitmentRepository;

  @Autowired
  private DeviceTypeRepository dtRepository;

  @Autowired
  CostCenterRepository costCenterRepository;

  @Autowired
  ExcelService excelService;

  @Autowired
  DeviceService deviceService;

  @Autowired
  TempDeviceRepository tempDeviceRepository;

  @Autowired
  TempDeviceService tempDeviceService;

  public TempDeviceController(DeviceRepository deviceRepository, ContractRepository commitmentRepository,
      CostCenterRepository costCenterRepository, ExcelService excelService,
      TempDeviceRepository tempDeviceRepository, TempDeviceService tempDeviceService) {
    this.deviceRepository = deviceRepository;
    this.costCenterRepository = costCenterRepository;
    this.commitmentRepository = commitmentRepository;
    this.excelService = excelService;
    this.tempDeviceRepository = tempDeviceRepository;
    this.tempDeviceService = tempDeviceService;
  }

  @PostMapping("/temp_upload")
  public ResponseEntity<ResponseMessage> tempUpload(@RequestBody List<TempDevice> tempDevices) {
    String message;
    if (tempDevices == null || tempDevices.isEmpty()) {
      message = "The uploaded data is empty!";
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT)
          .body(new ResponseMessage(false, message, null));
    }
    try {
      tempDeviceRepository.bulkInsertDevices(tempDevices);
      message = "Uploaded the data successfully. Records: " + tempDevices.size();
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseMessage(true, message, null));
    } catch (Exception e) {
      message = "Could not upload the data. Error: " + e.getMessage();
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseMessage(false, message, null));
    }
  }

  @PostMapping("/temp_concat")
  public ResponseEntity<ResponseMessage> tempConcat() {
    int rowsAffected = tempDeviceRepository.updateConcatPriceDate();
    String message = "Updated concat " + rowsAffected + " rows.";
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new ResponseMessage(true, message, null));
  }

  @PostMapping("/update_temp_device_type")
  public ResponseEntity<ResponseMessage> updateTempDeviceType() {
    int rowsAffected = tempDeviceRepository.updateTempDeviceType();
    String message = "Updated device type " + rowsAffected + " rows.";
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new ResponseMessage(true, message, null));
  }

  @GetMapping("/check_no_match")
  public ResponseEntity<ResponseMessage> checkNoMatch() {
    try {
      Pageable paging = Pageable.unpaged();
      Page<TempDeviceInterface.TempDeviceSummary> pageResult = tempDeviceRepository.checkNoMatch(paging);

      Map<String, Object> response = new HashMap<>();
      response.put("items", pageResult.getContent());
      response.put("totalItems", pageResult.getTotalElements());
      response.put("totalPages", pageResult.getTotalPages());
      response.put("currentPage", pageResult.getNumber());

      String message = "Fetched " + pageResult.getNumberOfElements() + " records.";
      return ResponseEntity.ok(new ResponseMessage(true, message, response));

    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseMessage(false, "Error fetching data", null));
    }
  }

  @PostMapping("/insert_update_master")
  public ResponseEntity<ResponseMessage> insertUpdateMaster() {
    // deviceService.insertUpdateFromTemp();
    SyncResultDTO insertedRows = tempDeviceService.insertUpdateFromTemp();
    // int rowsAffected = tempDeviceService.insertUpdateFromTemp();
    // String message = "insertUpdateFromTemp " + insertedRows + " records.";
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new ResponseMessage(true, "insertUpdateFromTemp complete", insertedRows));
  }

}
