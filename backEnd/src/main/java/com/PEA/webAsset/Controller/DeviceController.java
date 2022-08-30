package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Repository.CommitmentRepository;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.DeviceRepository;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
import com.PEA.webAsset.Share.DeviceService.DeviceService;
import com.PEA.webAsset.Share.ExcelService.ExcelHelper;
import com.PEA.webAsset.Share.ExcelService.ExcelService;
import com.PEA.webAsset.Share.ResponseMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("*")
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

    @GetMapping("/getAll")
    public Collection<tbDevice> getAll() {
        return deviceRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/getAll53")
    public ResponseEntity<Map<String,Object>> getAll53(@RequestParam("ccLong")String ccLong){
        List<tbDevice> deviceTemp = new ArrayList<tbDevice>();
        try {
            deviceTemp = deviceRepository.findDeviceForExcel53(ccLong);
            Map<String , Object> response = new HashMap<>();
            response.put("getAll53",deviceTemp);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

//        System.out.println("deviceTemp "+deviceTemp);

    }

    @GetMapping("/getAllDevice")
    public ResponseEntity<Map<String, Object>> getEmpSor(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
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
            response.put("itemsPerPage", size);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllDevice53")
    public ResponseEntity<Map<String, Object>> Device53(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        try {
            List<tbDevice> device = new ArrayList<tbDevice>();
            Pageable paging = PageRequest.of(page, size);

            Page<tbDevice> pageTuts = deviceRepository.findAll53(paging);
            device = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            response.put("data1", device);
            response.put("itemsPerPage", size);
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

    @GetMapping("/getAllByPattern2")
    public ResponseEntity<Map<String, Object>> Pattern2(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size,
            @RequestParam("region") String region,
            @RequestParam("setAssetType") String setAssetType) {

        System.out.println("setAssetType " + setAssetType);

        try {
            List<tbDevice> device = new ArrayList<tbDevice>();
            Pageable paging = PageRequest.of(page, size);
            Page<tbDevice> pageTuts = null;
            if (setAssetType.equals("53")) {
                System.out.println("Pattern2-53");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcId53(region, paging)
                        : null;
            } else if (setAssetType.equals("153")) {
                System.out.println("Pattern2-153");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcId153(region, paging)
                        : null;
            } else if (setAssetType.equals("all")) {
                System.out.println("Pattern2-all");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcId(region, paging)
                        : null;
            } else if (setAssetType.equals("1all")) {
                System.out.println("Pattern2-1all");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcId1all(region, paging)
                        : null;
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

    @GetMapping("/getAllByPattern2unpage")
    public ResponseEntity<Map<String, Object>> Patternunpage(
            @RequestParam("region") String region,
            @RequestParam("setAssetType") String setAssetType) {

        System.out.println("setAssetType " + setAssetType);

        try {
            List<tbDevice> device = new ArrayList<tbDevice>();
            Pageable paging = Pageable.unpaged();
            Page<tbDevice> pageTuts = null;
            if (setAssetType.equals("53")) {
                System.out.println("Pattern2-53");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcId53(region, paging)
                        : null;
            } else if (setAssetType.equals("153")) {
                System.out.println("Pattern2-153");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcId153(region, paging)
                        : null;
            } else if (setAssetType.equals("all")) {
                System.out.println("Pattern2-all");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcId(region, paging)
                        : null;
            } else if (setAssetType.equals("1all")) {
                System.out.println("Pattern2-1all");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcId1all(region, paging)
                        : null;
            }
            device = pageTuts.getContent();
            System.out.println(pageTuts);

            System.out.println(device);

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

    @GetMapping("/getAllByPattern1")
    public ResponseEntity<Map<String, Object>> Pattern1(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size,
            @RequestParam("region") String region,
            @RequestParam("textSearch") String textSearch,
            @RequestParam("setAssetType") String setAssetType) {
        try {
            List<tbDevice> device = new ArrayList<tbDevice>();
            Pageable paging = PageRequest.of(page, size);
            Page<tbDevice> pageTuts = null;
            if (setAssetType.equals("53")) {
                System.out.println("Pattern2-53");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcIdAndTextSearch53(region, textSearch, paging)
                        : null;
            }else if (setAssetType.equals("153")) {
                System.out.println("Pattern2-153");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcIdAndTextSearch153(region, textSearch, paging)
                        : null;
            }else if (setAssetType.equals("all")) {
                System.out.println("Pattern2-all");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcIdAndTextSearch(region, textSearch, paging)
                        : null;
            } else if (setAssetType.equals("1all")) {
                System.out.println("Pattern2-all");
                pageTuts = (region.length() > 0)
                        ? deviceRepository.findDeviceByCcIdAndTextSearch1all(region, textSearch, paging)
                        : null;
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

    @GetMapping("/getAllByPattern")
    public ResponseEntity<Map<String, Object>> getAllByPattern(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size,
            @RequestParam("test1") String test1[]) {

        String peaNo = test1[0];
        String empId = test1[1];
        String empName = test1[2];
        String ccLong = test1[3];

        try {
            List<tbDevice> device = new ArrayList<tbDevice>();
            Pageable paging = PageRequest.of(page, size);

            Page<tbDevice> XX = deviceRepository.findDeviceByPeaNoOrEmpIdOrEmpNameAndCC(peaNo, empId, empName, ccLong,
                    paging);
            Page<tbDevice> YY = deviceRepository.findDeviceByEmpIdOrEmpNameAndCC(empId, empName, ccLong, paging);
            Page<tbDevice> pageTuts = (peaNo.length() > 0) ? XX : YY;

            System.out.println("paging : " + paging);

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

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> importExcelFile(@RequestParam("file") MultipartFile files)
            throws IOException {
        String message = "";
        if (ExcelHelper.hasExcelFormat(files)) {

            try {
                List<tbDevice> deviceList = deviceService.saveDevice(files);
                deviceRepository.saveAll(deviceList);

                message = "Uploaded the file successfully: " + files.getOriginalFilename() + "\n";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + files.getOriginalFilename() + e.getMessage();
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
            @RequestParam("tbCostCenter") String tbCostCenter) {
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

    @PutMapping("/updateDevice")
    public ResponseEntity<ResponseMessage> updateDevice(@RequestParam("id") long id) throws Exception {

        String message = "";
        try {
            tbDevice device = deviceRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("NotFound"));

            device.setDevPeaNo("5555555");

            final tbDevice updateDevice = deviceRepository.save(device);
            message = "update is OK";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (ResourceNotFoundException e) {
            message = "Not found ";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
        }
    }

    @SneakyThrows
    @PostMapping("/upload2")
    public ResponseEntity<ResponseMessage> importExcelFile2(@RequestParam("file") MultipartFile files)
            throws IOException {
        String message = "";
        if (ExcelHelper.hasExcelFormat(files)) {
            deviceService.chkCellType(files);
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
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
            //     System.out.println("Pattern2-153");
            //     pageTuts = (region.length() > 0)
            //             ? deviceRepository.findDeviceByCcIdAndTextSearch153(region, textSearch, paging)
            //             : null;
            // }else if (setAssetType.equals("all")) {
            //     System.out.println("Pattern2-all");
            //     pageTuts = (region.length() > 0)
            //             ? deviceRepository.findDeviceByCcIdAndTextSearch(region, textSearch, paging)
            //             : null;
            // } else if (setAssetType.equals("1all")) {
            //     System.out.println("Pattern2-all");
            //     pageTuts = (region.length() > 0)
            //             ? deviceRepository.findDeviceByCcIdAndTextSearch1all(region, textSearch, paging)
            //             : null;
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
            //     System.out.println("Pattern2-153");
            //     pageTuts = (region.length() > 0)
            //             ? deviceRepository.findDeviceByCcIdAndTextSearch153(region, textSearch, paging)
            //             : null;
            // }else if (setAssetType.equals("all")) {
            //     System.out.println("Pattern2-all");
            //     pageTuts = (region.length() > 0)
            //             ? deviceRepository.findDeviceByCcIdAndTextSearch(region, textSearch, paging)
            //             : null;
            // } else if (setAssetType.equals("1all")) {
            //     System.out.println("Pattern2-all");
            //     pageTuts = (region.length() > 0)
            //             ? deviceRepository.findDeviceByCcIdAndTextSearch1all(region, textSearch, paging)
            //             : null;
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
}