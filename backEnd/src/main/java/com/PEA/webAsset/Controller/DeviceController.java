package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbDevice;
// import com.PEA.webAsset.Repository.CommitmentRepository;
import com.PEA.webAsset.Repository.ContractRepository;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.DeviceRepository;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;


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

  // @GetMapping("/getAllByPattern2")
  // public ResponseEntity<Map<String, Object>> Pattern2(
  // @RequestParam(defaultValue = "0") int page,
  // @RequestParam(defaultValue = "30") int size,
  // @RequestParam("region") String region,
  // @RequestParam("setAssetType") String setAssetType
  // ) {
  // System.out.println("setAssetType " + setAssetType);

  // try {
  // List<tbDevice> device = new ArrayList<tbDevice>();
  // Pageable paging = PageRequest.of(page, size);
  // Page<tbDevice> pageTuts = null;findDeviceByCcId53
  // if (setAssetType.equals("53")) {
  // System.out.println("Pattern2-53");
  // pageTuts =
  // (region.length() > 0)
  // ? deviceRepository.findDeviceByCcId53(region, paging)
  // : null;
  // } else if (setAssetType.equals("153")) {
  // System.out.println("Pattern2-153");
  // pageTuts =
  // (region.length() > 0)
  // ? deviceRepository.findDeviceByCcId153(region, paging)
  // : null;
  // } else if (setAssetType.equals("all")) {
  // System.out.println("Pattern2-all");
  // pageTuts =
  // (region.length() > 0)
  // ? deviceRepository.findDeviceByCcId(region, paging)
  // : null;
  // } else if (setAssetType.equals("1all")) {
  // System.out.println("Pattern2-1all");
  // pageTuts =
  // (region.length() > 0)
  // ? deviceRepository.findDeviceByCcId1all(region, paging)
  // : null;
  // }
  // device = pageTuts.getContent();
  // System.out.println(pageTuts);

  // System.out.println(device);

  // Map<String, Object> response = new HashMap<>();
  // response.put("currentPage", pageTuts.getNumber());
  // response.put("totalItems", pageTuts.getTotalElements());
  // response.put("totalPages", pageTuts.getTotalPages());
  // response.put("data1", device);
  // response.put("itemsPerPage", size);
  // return new ResponseEntity<>(response, HttpStatus.OK);
  // } catch (Exception e) {
  // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  // }
  // }

  @GetMapping("/searchNoWordUnpage")
  public ResponseEntity<Map<String, Object>> Patternunpage(
      @RequestParam("region") String region,
      @RequestParam("setAssetType") String setAssetType) {
    // System.out.println("setAssetType=" + setAssetType + " region=" + region);

    try {
      List<tbDevice> device = new ArrayList<tbDevice>();
      Pageable paging = Pageable.unpaged();
      Page<tbDevice> pageTuts = null;
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
      // System.out.println(pageTuts);

      // System.out.println(device);

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
      List<tbDevice> device = new ArrayList<tbDevice>();
      Pageable paging = Pageable.unpaged();
      Page<tbDevice> pageTuts = null;

      if (region.equals("E3") || region.equals("E3010")) {
        if (setAssetType.equals("53")) {
          System.out.println("searchWithWord-53");
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
          System.out.println("searchWithWord-53");
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
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // @GetMapping("/getAllByPattern")
  // public ResponseEntity<Map<String, Object>> getAllByPattern(
  // @RequestParam(defaultValue = "0") int page,
  // @RequestParam(defaultValue = "30") int size,
  // @RequestParam("test1") String test1[]
  // ) {
  // String peaNo = test1[0];
  // String empId = test1[1];
  // String empName = test1[2];
  // String ccLong = test1[3];

  // try {
  // List<tbDevice> device = new ArrayList<tbDevice>();
  // Pageable paging = PageRequest.of(page, size);

  // Page<tbDevice> XX = deviceRepository.findDeviceByPeaNoOrEmpIdOrEmpNameAndCC(
  // peaNo,
  // empId,
  // empName,
  // ccLong,
  // paging
  // );
  // Page<tbDevice> YY = deviceRepository.findDeviceByEmpIdOrEmpNameAndCC(
  // empId,
  // empName,
  // ccLong,
  // paging
  // );
  // Page<tbDevice> pageTuts = (peaNo.length() > 0) ? XX : YY;

  // System.out.println("paging : " + paging);

  // device = pageTuts.getContent();
  // Map<String, Object> response = new HashMap<>();
  // response.put("currentPage", pageTuts.getNumber());
  // response.put("totalItems", pageTuts.getTotalElements());
  // response.put("totalPages", pageTuts.getTotalPages());
  // response.put("data1", device);
  // return new ResponseEntity<>(response, HttpStatus.OK);
  // } catch (Exception e) {
  // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  // }
  // }

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> importExcelFile(
      @RequestParam("file") MultipartFile files)
      throws IOException {
    String message = "";
    if (ExcelHelper.hasExcelFormat(files)) {
      try {
        List<tbDevice> deviceList = deviceService.saveDevice(files);
        deviceRepository.saveAll(deviceList);

        message = "Uploaded the file successfully: " +
            files.getOriginalFilename() +
            "\n";
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " +
            files.getOriginalFilename() +
            e.getMessage();
        return ResponseEntity
            .status(HttpStatus.EXPECTATION_FAILED)
            .body(new ResponseMessage(message));
      }
    }
    message = "Please upload an excel file!";
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ResponseMessage(message));
  }

  @PostMapping("/posT")
  public ResponseEntity<ResponseMessage> posT(
      @RequestParam("dev_serialNo") String dev_serialNo,
      @RequestParam("dev_note") String dev_note,
      @RequestParam("dev_description") String dev_description,
      @RequestParam("dev_peaNo") String dev_peaNo,
      @RequestParam("tbCostCenter") String tbCostCenter) {
    String message;
    try {
      deviceService.postDevice(
          dev_serialNo,
          dev_note,
          dev_description,
          dev_peaNo,
          tbCostCenter);
      message = "POST OK \n";
      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "POST NOT OK : " + e.getMessage();
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new ResponseMessage(message));
    }
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
          .body(new ResponseMessage(message));
    } catch (ResourceNotFoundException e) {
      message = "Not found ";
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ResponseMessage(message));
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
        .body(new ResponseMessage(message));
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

  @GetMapping("/redirectgoogle")
  public Map<String, String> redirectGoogle() {
    Map<String, String> response = new HashMap<>();
    response.put("redirectUrl", "https://www.google.com");
    return response;
  }

  @GetMapping("/test")
  public String testEndpoint() {
    return "CORS is working!";
  }

  @GetMapping("/getDevice53unpageByccId")
  public ResponseEntity<Map<String, Object>> getDevice53unpageByccId(
      @RequestParam("region") String region,
      @RequestParam("dt_id") String dt_id) {
    try {
      List<tbDevice> device = new ArrayList<tbDevice>();
      Pageable paging = Pageable.unpaged();

      Page<tbDevice> pageTuts = deviceRepository.getDevice53unpageByccId(
          region, dt_id, paging);
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
      @RequestParam("dt_id") String dt_id) {
    try {
      List<tbDevice> device = new ArrayList<tbDevice>();
      Pageable paging = Pageable.unpaged();

      Page<tbDevice> pageTuts = deviceRepository.getDevice53unpageByccIdOnly7Year(
          region, dt_id, paging);
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

  private static final Logger LOGGER = Logger.getLogger(DeviceController.class.getName());

  @PostMapping("/test2")
  public String getDescription(@RequestBody String json) {
    return (json);
  }

  @RequestMapping(path = "/something", method = RequestMethod.PUT)
  public @ResponseBody String helloWorld() {
    return "Hello World";
  }

  String requestData2 = "{\r\n" + //
      "    \"templateProjectPath\": \"sample/ams/506027-fixform.dito\",\r\n" + //
      "    \"templateName\": \"output\",\r\n" + //
      "    \"pdfVersion\": \"1.7\",\r\n" + //
      "    \"data\": {\r\n" + //
      "        \"cost_center_name\": \"กฟส.กทล.\",\r\n" + //
      "        \"date\": \"19 มิ.ย. 2567\",\r\n" + //
      "        \"type_other\": \"\",\r\n" + //
      "        \"brand\": \"HP\",\r\n" + //
      "        \"model\": \"ProDesk 600 G5\",\r\n" + //
      "        \"contract\": \"บ.75/2563\",\r\n" + //
      "        \"serial\": \"4CE03526C6\",\r\n" + //
      "        \"pea_no\": \"5330404643\",\r\n" + //
      "        \"problem\": \"ฮาร์ดิสชำรุด\",\r\n" + //
      "        \"emp_name\": \"นายอนุสรณ์ อมรรัตนศักดิ์\",\r\n" + //
      "        \"emp_role\": \"พบค.7\",\r\n" + //
      "        \"emp_id\": \"499857\",\r\n" + //
      "        \"tel\": \"(22)14890\",\r\n" + //
      "        \"inspector_name\": \"นายภาณุวิชญ์ ธานีวัฒน์\",\r\n" + //
      "        \"inspector_role\": \"นรค.7\",\r\n" + //
      "        \"inspector_date\": \"19 มิ.ย. 2567\",\r\n" + //
      "        \"dep_head_name\": \"นายสุเธียรพงศ์ ธนาอภิสิทธิ์โสภณ\",\r\n" + //
      "        \"dep_head_role\": \"หผ.คข.กดส.ฉ.2\",\r\n" + //
      "        \"dep_head_date\": \"19 มิ.ย. 2567\"\r\n" + //
      "    }\r\n" + //
      "}";

  @CrossOrigin(origins = "http://localhost:8000")
  @PostMapping("/redirectPdfProducer")
  public ResponseEntity<byte[]> redirectPdfProducer(@RequestBody String requestData) {
    String targetUrl = "http://172.30.211.224:42/api/pdf-producer";
    System.err.println("Received Request Data: " + requestData);

    // Initialize RestTemplate and headers
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_PDF));

    // Here you can modify the requestData if needed before forwarding
    // Example: you can parse requestData into a JSON object and modify fields
    try {
    // Log the prepared request data
    System.err.println("Sending Data to External API: " + requestData);

    // Create HttpEntity with the modified requestData and headers
    HttpEntity<String> entity = new HttpEntity<>(requestData2, headers);

    // Send the request to the external API
    ResponseEntity<byte[]> response = restTemplate.exchange(targetUrl,
    HttpMethod.POST, entity, byte[].class);

    // Log the response status and headers
    System.err.println("Response Status: " + response.getStatusCode());
    System.err.println("Response Headers: " + response.getHeaders());

    // Set the response headers for the client
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);

    // Return the response as a PDF
    return
    ResponseEntity.status(response.getStatusCode()).headers(responseHeaders).body(response.getBody());
    } catch (Exception e) {
    // Log the exception for debugging
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
}
