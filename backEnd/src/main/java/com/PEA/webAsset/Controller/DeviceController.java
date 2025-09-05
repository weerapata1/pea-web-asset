package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Entity.TempDevice;
import com.PEA.webAsset.Exeption.InvalidDataException;
import com.PEA.webAsset.Interface.DeviceInterface;
// import com.PEA.webAsset.Repository.CommitmentRepository;
import com.PEA.webAsset.Repository.ContractRepository;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.DeviceRepository;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
import com.PEA.webAsset.Share.DeviceService.DeviceService;
import com.PEA.webAsset.Share.ExcelService.ExcelHelper;
import com.PEA.webAsset.Share.ExcelService.ExcelService;
import com.PEA.webAsset.dto.Cost60ByUserDTO;
import com.PEA.webAsset.dto.CountDeviceByDepDTO;
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
public class DeviceController {
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

  public DeviceController(DeviceRepository deviceRepository, ContractRepository commitmentRepository,
      CostCenterRepository costCenterRepository, ExcelService excelService) {
    this.deviceRepository = deviceRepository;
    this.costCenterRepository = costCenterRepository;
    this.commitmentRepository = commitmentRepository;
    this.excelService = excelService;
  }

  @GetMapping("/getAll")
  public Collection<tbDevice> getAll() {
    return deviceRepository.findAll().stream().collect(Collectors.toList());
  }

  @GetMapping("/getAllDevice")
  public ResponseEntity<Map<String, Object>> getEmpSor(
  // @RequestParam(defaultValue = "0") int page,
  // @RequestParam(defaultValue = "30") int size
  ) {
    try {
      List<tbDevice> device = new ArrayList<tbDevice>();
      Pageable paging = Pageable.unpaged();

      Page<tbDevice> pageTuts = deviceRepository.findAll(paging);
      device = pageTuts.getContent();

      Map<String, Object> response = new HashMap<>();
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());
      response.put("data1", device);
      // response.put("itemsPerPage", size);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/getDeviceByPeaNo")
  public tbDevice getDeviceByPeaNo(@RequestParam("PeaNo") String PeaNo) {
    return deviceRepository.findAllByDevPeaNo(PeaNo);
  }

  @GetMapping("/getDeviceByPeaNo/{peaNo}")
  public tbDevice getDeviceByPeaNoWithPathVariable(@PathVariable(name = "peaNo") String peaNo) {
    return deviceRepository.findAllByDevPeaNo(peaNo);
  }

  @GetMapping("/getAll53")
  public ResponseEntity<Object> getAll53(@RequestParam("ccLong") String ccLong) {
    List<tbDevice> deviceTemp = new ArrayList<tbDevice>();
    System.out.println("ccLong : " + ccLong);
    try {
      deviceTemp = deviceRepository.findDeviceForExcel53(ccLong);

      return new ResponseEntity<>(deviceTemp, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // System.out.println("deviceTemp "+deviceTemp);
  }

  @GetMapping("/getAllDevice53")
  public ResponseEntity<Map<String, Object>> Device53(
  // @RequestParam(defaultValue = "0") int page,
  // @RequestParam(defaultValue = "30") int size
  ) {
    try {
      List<tbDevice> device = new ArrayList<tbDevice>();
      Pageable paging = Pageable.unpaged();

      Page<tbDevice> pageTuts = deviceRepository.findAll53(paging);
      device = pageTuts.getContent();

      Map<String, Object> response = new HashMap<>();
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());
      response.put("data1", device);
      // response.put("itemsPerPage", size);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/getAllDevice53unpage")
  public ResponseEntity<Map<String, Object>> Device53unpage() {
    try {
      List<tbDevice> device = new ArrayList<tbDevice>();
      Pageable paging = Pageable.unpaged();

      Page<tbDevice> pageTuts = deviceRepository.findAll53(paging);
      device = pageTuts.getContent();

      Map<String, Object> response = new HashMap<>();
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());
      response.put("data1", device);
      // response.put("itemsPerPage", size);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/searchNoWordUnpage")
  public ResponseEntity<Map<String, Object>> Patternunpage(
      @RequestParam("region") String region,
      @RequestParam("setAssetType") String setAssetType) {
    // System.out.println("setAssetType=" + setAssetType + " region=" + region);

    try {
      List<Object[]> device = new ArrayList<Object[]>();
      Pageable paging = Pageable.unpaged();
      Page<Object[]> pageTuts = null;
      if (region.equals("E3") || region.equals("E3010")) {
        System.out.println("setAssetType=" + setAssetType + " region=" + region);
        if (setAssetType.equals("53")) {
          System.out.println("searchNoWord-53-E3");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcId53zc(region, paging)
              : null;
        } else if (setAssetType.equals("153")) {
          System.out.println("searchNoWord-153-E3");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcId153zc(region, paging)
              : null;
        } else if (setAssetType.equals("all")) {
          System.out.println("searchNoWord-all-E3");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcIdzc(region, paging)
              : null;
        } else if (setAssetType.equals("1all")) {
          System.out.println("searchNoWord-1all-E3");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcId1allzc(region, paging)
              : null;
        }
      } else {
        System.out.println("setAssetType=" + setAssetType + " region=" + region);
        if (setAssetType.equals("53")) {
          System.out.println("findDeviceByCcId53");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcId53(region, paging)
              : null;
        } else if (setAssetType.equals("153")) {
          System.out.println("findDeviceByCcId153");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcId153(region, paging)
              : null;
        } else if (setAssetType.equals("all")) {
          System.out.println("findDeviceByCcId");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcId(region, paging)
              : null;
        } else if (setAssetType.equals("1all")) {
          System.out.println("findDeviceByCcId1all");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcId1all(region, paging)
              : null;
        }
      }
      device = pageTuts.getContent();

      Map<String, Object> response = new HashMap<>();
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());
      response.put("dataExcel", device);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/searchWithWord")
  public ResponseEntity<Map<String, Object>> Pattern1(
      // @RequestParam(defaultValue = "0") int page,
      // @RequestParam(defaultValue = "30") int size,
      @RequestParam("region") String region,
      @RequestParam("textSearch") String textSearch,
      @RequestParam("setAssetType") String setAssetType) {
    try {
      List<Object[]> device = new ArrayList<Object[]>();
      Pageable paging = Pageable.unpaged();
      Page<Object[]> pageTuts = null;

      if (region.equals("E3") || region.equals("E3010")) {
        if (setAssetType.equals("53")) {
          System.out.println("searchWithWord-53zc");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcIdAndTextSearch53zc(
                  region,
                  textSearch,
                  paging)
              : null;
        } else if (setAssetType.equals("153")) {
          System.out.println("searchWithWord-153");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcIdAndTextSearch153zc(
                  region,
                  textSearch,
                  paging)
              : null;
        } else if (setAssetType.equals("all")) {
          System.out.println("searchWithWord-all");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcIdAndTextSearchzc(
                  region,
                  textSearch,
                  paging)
              : null;
        } else if (setAssetType.equals("1all")) {
          System.out.println("searchWithWord-all");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcIdAndTextSearch1allzc(
                  region,
                  textSearch,
                  paging)
              : null;
        }
      } else {
        if (setAssetType.equals("53")) {
          System.out.println("searchWithWord-53-53");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcIdAndTextSearch53(
                  region,
                  textSearch,
                  paging)
              : null;
        } else if (setAssetType.equals("153")) {
          System.out.println("searchWithWord-153");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcIdAndTextSearch153(
                  region,
                  textSearch,
                  paging)
              : null;
        } else if (setAssetType.equals("all")) {
          System.out.println("searchWithWord-all");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcIdAndTextSearch(
                  region,
                  textSearch,
                  paging)
              : null;
        } else if (setAssetType.equals("1all")) {
          System.out.println("searchWithWord-all");
          pageTuts = (region.length() > 0)
              ? deviceRepository.findDeviceByCcIdAndTextSearch1all(
                  region,
                  textSearch,
                  paging)
              : null;
        }
      }
      device = pageTuts.getContent();
      System.out.println(pageTuts);

      System.out.println(device);

      Map<String, Object> response = new HashMap<>();
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());
      response.put("data1", device);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      Map<String, Object> response = new HashMap<>();
      response.put("Error", e.getMessage());
      System.out.println(e.getMessage());
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> importExcelFile(
      @RequestParam("file") MultipartFile file) throws IOException {

    String message;

    if (!ExcelHelper.hasExcelFormat(file)) {
      message = "Please upload a valid Excel file!";
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new ResponseMessage(false, message, null));
    }

    try {
      System.err.println("try to saveDevice");
      List<tbDevice> deviceList = deviceService.saveDevice(file);

      if (deviceList.isEmpty()) {
        message = "The uploaded file contains no valid data!";
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(new ResponseMessage(false, message, null));
      }

      deviceRepository.saveAll(deviceList);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseMessage(true, message, null));
    } catch (InvalidDataException e) {
      message = "The file contains invalid data: " + e.getMessage();
      return ResponseEntity
          .status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(new ResponseMessage(false, message, null));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseMessage(false, message, null));
    }
  }

  @PostMapping("/test-upload")
  public ResponseEntity<String> testUpload(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok("File received: " + file.getOriginalFilename());
  }

  @PutMapping("/updateDevice")
  public ResponseEntity<ResponseMessage> updateDevice(
      @RequestParam("id") long id)
      throws Exception {
    String message = "";
    try {
      tbDevice device = deviceRepository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("NotFound"));

      device.setDevPeaNo("5555555");

      final tbDevice updateDevice = deviceRepository.save(device);
      message = "update is OK";
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseMessage(true, message, null));
    } catch (ResourceNotFoundException e) {
      message = "Not found ";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ResponseMessage(false, message, null));
    }
  }

  @SneakyThrows
  @PostMapping("/upload2")
  public ResponseEntity<ResponseMessage> importExcelFile2(
      @RequestParam("file") MultipartFile files)
      throws IOException {
    String message = "";
    if (ExcelHelper.hasExcelFormat(files)) {
      deviceService.chkCellType(files);
    }
    message = "Please upload an excel file!";
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ResponseMessage(false, message, null));
  }

  @GetMapping("/getExcelData2")
  public ResponseEntity<Map<String, Object>> excelData2(
      // @RequestParam(defaultValue = "0") int page,
      // @RequestParam(defaultValue = "30") int size,
      @RequestParam("region") String region,
      // @RequestParam("textSearch") String textSearch,
      @RequestParam("setAssetType") String setAssetType) {
    try {
      List<tbDevice> device = new ArrayList<tbDevice>();
      // Pageable paging = PageRequest.of(page, size);
      // Page<tbDevice> pageTuts = null;
      if (setAssetType.equals("53")) {
        System.out.println("Excel-53");
        device = (region.length() > 0)
            ? deviceRepository.findDeviceForExcel53(region)
            : null;
      }
      // else if (setAssetType.equals("153")) {
      // System.out.println("Pattern2-153");
      // pageTuts = (region.length() > 0)
      // ? deviceRepository.findDeviceByCcIdAndTextSearch153(region, textSearch,
      // paging)
      // : null;
      // }else if (setAssetType.equals("all")) {
      // System.out.println("Pattern2-all");
      // pageTuts = (region.length() > 0)
      // ? deviceRepository.findDeviceByCcIdAndTextSearch(region, textSearch, paging)
      // : null;
      // } else if (setAssetType.equals("1all")) {
      // System.out.println("Pattern2-all");
      // pageTuts = (region.length() > 0)
      // ? deviceRepository.findDeviceByCcIdAndTextSearch1all(region, textSearch,
      // paging)
      // : null;
      // }

      // device = pageTuts.getContent();
      // System.out.println(pageTuts);

      System.out.println(device);

      Map<String, Object> response = new HashMap<>();
      // response.put("currentPage", pageTuts.getNumber());
      // response.put("totalItems", pageTuts.getTotalElements());
      // response.put("totalPages", pageTuts.getTotalPages());
      response.put("dataExcel", device);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/getExcelData2search")
  public ResponseEntity<Map<String, Object>> excelData2search(
      // @RequestParam(defaultValue = "0") int page,
      // @RequestParam(defaultValue = "30") int size,
      @RequestParam("region") String region,
      @RequestParam("textSearch") String textSearch,
      @RequestParam("setAssetType") String setAssetType) {
    try {
      List<tbDevice> device = new ArrayList<tbDevice>();
      // Pageable paging = PageRequest.of(page, size);
      // Page<tbDevice> pageTuts = null;
      if (setAssetType.equals("53")) {
        System.out.println("Excel-53");
        device = (region.length() > 0)
            ? deviceRepository.findDeviceForExcel53search(region, textSearch)
            : null;
      }

      System.out.println(device);

      Map<String, Object> response = new HashMap<>();
      // response.put("currentPage", pageTuts.getNumber());
      // response.put("totalItems", pageTuts.getTotalElements());
      // response.put("totalPages", pageTuts.getTotalPages());
      response.put("dataExcel", device);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/getDevice53unpageByccId")
  public ResponseEntity<Map<String, Object>> Pattern2(

      @RequestParam("region") String region,
      @RequestParam("device_type_id") String device_type_id) {
    try {
      System.out.println("/getDevice53unpageByccId");
      List<Object[]> device = new ArrayList<Object[]>();
      Pageable paging = Pageable.unpaged();

      Page<Object[]> pageTuts = deviceRepository.getDevice53unpageByccId(
          region, device_type_id, paging);
      device = pageTuts.getContent();

      Map<String, Object> response = new HashMap<>();

      response.put("totalItems", pageTuts.getTotalElements());
      response.put("dataDevice", device);
      // response.put("itemsPerPage", size);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/getDevice53unpageByccIdOnly7Year")
  public ResponseEntity<Map<String, Object>> getDevice53unpageByccIdOnly7Year(
      @RequestParam("region") String region,
      @RequestParam("device_type_id") String device_type_id) {
    try {

      List<tbDevice> device = new ArrayList<tbDevice>();
      Pageable paging = Pageable.unpaged();

      Page<tbDevice> pageTuts = deviceRepository.getDevice53unpageByccIdOnly7Year(
          region, device_type_id, paging);
      device = pageTuts.getContent();
      System.out.println("Device : " + device);

      Map<String, Object> response = new HashMap<>();

      response.put("totalItems", pageTuts.getTotalElements());
      response.put("dataDevice", device);
      // response.put("itemsPerPage", size);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println("error on  getDevice53unpageByccIdOnly7Year message: " + e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private static final Logger LOGGER = Logger.getLogger(DeviceController.class.getName());

  @RequestMapping(path = "/something", method = RequestMethod.PUT)
  public @ResponseBody String helloWorld() {
    return "Hello World";
  }

  @CrossOrigin(origins = "http://localhost:8000")
  @PostMapping("/redirectPdfProducer")
  public ResponseEntity<byte[]> redirectPdfProducer(@RequestBody String requestData) {
    String targetUrl = "http://172.30.211.224:42/api/pdf-producer";
    System.err.println("Received Request Data: " + requestData);

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = null;

    try {
      rootNode = objectMapper.readTree(requestData);
    } catch (JsonMappingException e) {

      e.printStackTrace();
    } catch (JsonProcessingException e) {

      e.printStackTrace();
    }

    String costCenterName = rootNode.path("ccShortName").asText();
    String date = rootNode.path("date").asText();
    String brand = rootNode.path("brand").asText(); // Extracted from devDescription
    String model = rootNode.path("model").asText(); // Extracted from devDescription
    String serial = rootNode.path("devSerialNo").asText();
    String peaNo = rootNode.path("devPeaNo").asText();
    String problem = rootNode.path("problem").asText();
    String contract = rootNode.path("contract").asText();
    String empName = rootNode.path("empName").asText();
    String empRank = rootNode.path("empRank").asText();
    String empId = rootNode.path("empId").asText();
    String type_other = rootNode.path("type_other").asText();
    String tel = rootNode.path("tel").asText();
    String inspector_name = rootNode.path("inspector_name").asText();
    String inspector_role = rootNode.path("inspector_role").asText();
    String dep_head_name = rootNode.path("dep_head_name").asText();
    String dep_head_role = rootNode.path("dep_head_role").asText();
    String inspect_dep_name = rootNode.path("inspect_dep_name").asText();

    String requestData2 = "{\r\n" + //
        "    \"templateProjectPath\": \"sample/ams/506027-fixform-2025v2.dito\",\r\n" + //
        "    \"templateName\": \"output\",\r\n" + //
        "    \"pdfVersion\": \"1.7\",\r\n" + //
        "    \"data\": {\r\n" + //
        "        \"cost_center_name\": \"" + costCenterName + "\",\r\n" +
        "        \"date\": \"" + date + "\",\r\n" +
        "        \"type_other\": \"" + type_other + "\",\r\n" +
        "        \"brand\": \"" + brand + "\",\r\n" +
        "        \"model\": \"" + model + "\",\r\n" +
        "        \"contract\": \"" + contract + "\",\r\n" +
        "        \"serial\": \"" + serial + "\",\r\n" +
        "        \"pea_no\": \"" + peaNo + "\",\r\n" +
        "        \"problem\": \"" + problem + "\",\r\n" +
        "        \"emp_name\": \"" + empName + "\",\r\n" +
        "        \"emp_role\": \"" + empRank + "\",\r\n" +
        "        \"emp_id\": \"" + empId + "\",\r\n" +
        "        \"tel\": \"" + tel + "\",\r\n" +
        "        \"inspector_name\": \"" + inspector_name + "\",\r\n" +
        "        \"inspector_role\": \"" + inspector_role + "\",\r\n" +
        "        \"inspector_date\": \"" + date + "\",\r\n" +
        "        \"dep_head_name\": \"" + dep_head_name + "\",\r\n" +
        "        \"dep_head_role\": \"" + dep_head_role + "\",\r\n" +
        "        \"dep_head_date\": \"" + date + "\",\r\n" +
        "        \"inspect_dep_name\": \"" + inspect_dep_name + "\"\r\n" +
        "    }\r\n" + //
        "}";

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_PDF));
    headers.setContentDisposition(ContentDisposition.inline().filename("generated.pdf").build());

    try {

      System.err.println("Sending Data to External API: " + requestData2);

      HttpEntity<String> entity = new HttpEntity<>(requestData2, headers);

      ResponseEntity<byte[]> response = restTemplate.exchange(targetUrl,
          HttpMethod.POST, entity, byte[].class);

      System.err.println("Response Status: " + response.getStatusCode());
      System.err.println("Response Headers: " + response.getHeaders());

      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);

      // return
      // ResponseEntity.status(response.getStatusCode()).headers(responseHeaders).body(response.getBody());
      return new ResponseEntity<>(response.getBody(), responseHeaders, HttpStatus.OK);
    } catch (Exception e) {
      System.err.println("Error occurred while redirecting PDF producer request: "
          + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @ExceptionHandler(CustomException.class)
  public final ResponseEntity<String> handleCustomException(CustomException ex, WebRequest request) {
    LOGGER.log(Level.SEVERE, "Handling custom exception", ex);
    return ResponseEntity.status(500).body("Custom error: " + ex.getMessage());
  }

  public static class CustomException extends RuntimeException {
    public CustomException(String message, Throwable cause) {
      super(message, cause);
    }
  }

  @GetMapping("/countDeviceByDep")
  public ResponseEntity<ResponseMessage> countDeviceByDep() {
    try {
      List<Object[]> device = new ArrayList<Object[]>();
      Pageable paging = Pageable.unpaged();

      Page<DeviceInterface.countDeviceByDep> pageResult = deviceRepository.getDeviceDetailsWithCounts(paging);
      List<CountDeviceByDepDTO> rows = pageResult.getContent().stream()
          .map(p -> new CountDeviceByDepDTO(
              p.getDeviceId(),
              p.getDevPeaNo(),
              p.getDevDescription(),
              p.getDevReceivedDate(),
              p.getEmpName(),
              p.getEmpRank(),
              p.getCcShortName(),
              p.getCcLongCode(),
              p.getDivisionCode(),
              p.getDivisionCount(),
              p.getDepartmentCount()))
          .collect(java.util.stream.Collectors.toList());

      Map<String, Object> response = new HashMap<>();
      response.put("currentPage", pageResult.getNumber());
      response.put("totalItems", pageResult.getTotalElements());
      response.put("totalPages", pageResult.getTotalPages());
      response.put("data", rows);
      // response.put("itemsPerPage", size);
      return ResponseEntity.ok(new ResponseMessage(true, "Fetched " + rows.size() + " records.", response));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseMessage(false, "Error fetching data", e));
    }
  }

}
