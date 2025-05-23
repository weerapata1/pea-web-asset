package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Entity.TempDevice;
import com.PEA.webAsset.Exeption.InvalidDataException;
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

  public TempDeviceController(DeviceRepository deviceRepository, ContractRepository commitmentRepository,
      CostCenterRepository costCenterRepository, ExcelService excelService,
      TempDeviceRepository tempDeviceRepository) {
    this.deviceRepository = deviceRepository;
    this.costCenterRepository = costCenterRepository;
    this.commitmentRepository = commitmentRepository;
    this.excelService = excelService;
    this.tempDeviceRepository = tempDeviceRepository;
  }

  @PostMapping("/temp_upload")
  public ResponseEntity<ResponseMessage> tempUpload(@RequestBody List<TempDevice> tempDevices) {
    String message;
    if (tempDevices == null || tempDevices.isEmpty()) {
        message = "The uploaded data is empty!";
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ResponseMessage(false, message));
    }
    try {
        tempDeviceRepository.bulkInsertDevices(tempDevices);
        message = "Uploaded the data successfully. Records: " + tempDevices.size();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage(true, message));
    } catch (Exception e) {
        message = "Could not upload the data. Error: " + e.getMessage();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessage(false, message));
    }
  }
}
