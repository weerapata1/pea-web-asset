package com.PEA.webAsset.Share.DeviceService;

import com.PEA.webAsset.Entity.tbCostCenter;
import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Exeption.InvalidDataException;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.DeviceRepository;
import com.PEA.webAsset.Repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.DataFormatter;
import com.PEA.webAsset.Share.ExcelService.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class DeviceService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy");

    private final CostCenterRepository costCenterRepository;
    private final DeviceRepository deviceRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DeviceService(
            CostCenterRepository costCenterRepository,
            DeviceRepository deviceRepository,
            EmployeeRepository employeeRepository) {
        this.costCenterRepository = costCenterRepository;
        this.deviceRepository = deviceRepository;
        this.employeeRepository = employeeRepository;
    }

    // public void postDevice(String dev_serialNo, String dev_note, String
    // dev_description, String dev_peaNo,
    // String tbCostCenterTest
    // // ,String dateTimeNow ,String dateNow
    // ) {
    // String dateTimeTemp = now.format(dateTimeFormat);
    // String dateTemp = now.format(dateFormat);
    // LocalDateTime dateTime = LocalDateTime.parse(dateTimeTemp, dateTimeFormat);
    // LocalDate date = LocalDate.parse(dateTemp, dateFormat);

    // try {
    // tbDevice newDevice = new tbDevice();
    // deviceRepository.save(newDevice);
    // } catch (Exception e) {
    // throw new RuntimeException("POST Fail : " + e.getMessage());
    // }
    // }

    public void chkCellType(MultipartFile file) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rows = sheet.rowIterator();
        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();

            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()) {
                XSSFCell cell = (XSSFCell) cells.next();

                CellType type = cell.getCellType();
                if (type == CellType.STRING) {
                    System.out.printf("[%d, %d] = STRING; Value = %s%n",
                            cell.getRowIndex(), cell.getColumnIndex(),
                            cell.getRichStringCellValue().toString());
                } else if (type == CellType.NUMERIC) {
                    System.out.printf("[%d, %d] = NUMERIC; Value = %f%n",
                            cell.getRowIndex(), cell.getColumnIndex(),
                            cell.getNumericCellValue());
                } else if (type == CellType.BOOLEAN) {
                    System.out.printf("[%d, %d] = BOOLEAN; Value = %b%n",
                            cell.getRowIndex(), cell.getColumnIndex(),
                            cell.getBooleanCellValue());
                } else if (type == CellType.BLANK) {
                    System.out.printf("[%d, %d] = BLANK CELL%n",
                            cell.getRowIndex(), cell.getColumnIndex());
                }
            }
        }
    }

    @Transactional
    public List<tbDevice> saveDevice(MultipartFile file) throws IOException {
        int index = 0;
        DataFormatter formatter = new DataFormatter();

        List<tbDevice> deviceList = new ArrayList<>();
        List<Object[]> deviceBatch = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            XSSFSheet worksheet = workbook.getSheetAt(0);
            XSSFRow row1 = worksheet.getRow(1);
            int lastCellNum = row1.getLastCellNum();
            System.out.println("row1 lastCellNum" + lastCellNum);
            // Start from the second row (index 1) to skip the header

            for (index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {

                XSSFRow row = worksheet.getRow(index);
                System.out.println("Row " + index + " has " + row.getLastCellNum() + " cells.");

                if (row == null || row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                    break; // Stop processing if Column A is empty
                }

                Double receivedPrice;
                Cell c10 = row.getCell(lastCellNum - 3);
                receivedPrice = (c10 == null || c10.getCellType() == CellType.BLANK)
                        ? 0.0
                        : row.getCell(lastCellNum - 3).getNumericCellValue();

                System.out.println("Row " + index + " receivedPrice " + receivedPrice);
                if (index > 0 && receivedPrice >= 1) {

                    String peaNo = "";
                    String userId = "";
                    String description = "";
                    String serialNo = "";
                    String receivedDate = "";
                    Double leftPrice = 0.0;
                    String ccLongCode = "";

                    peaNo = ExcelHelper.extractCellValue(formatter, row.getCell(lastCellNum - 11), "peaNo", index);
                    System.out.println("Row " + index + " peaNo " + peaNo);

                    userId = ExcelHelper.extractCellValue(formatter,
                            row.getCell(lastCellNum - 10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "userId", index);
                    System.out.println("Row " + index + " userId " + userId);

                    description = ExcelHelper.extractCellValue(formatter, row.getCell(lastCellNum - 9),
                            "description", index);
                    System.out.println("Row " + index + " description " + description);

                    serialNo = ExcelHelper.extractCellValue(formatter, row.getCell(lastCellNum - 8), "serialNo", index);
                    System.out.println("Row " + index + " serialNo " + serialNo);

                    receivedDate = ExcelHelper.extractCellValue(formatter, row.getCell(lastCellNum - 4),
                            "receivedDate", index);
                    System.out.println("Row " + index + " receivedDate " + receivedDate);

                    Double valueDD = ExcelHelper.extractCellNumericValue(row.getCell(lastCellNum - 2), "leftPrice",
                            index);
                    leftPrice = valueDD;
                    System.out.println("Row " + index + " leftPrice " + leftPrice);

                    ccLongCode = ExcelHelper.extractCellValue(formatter, row.getCell(lastCellNum - 1), "ccLongCode",
                            index);
                    System.out.println("Row " + index + " ccLongCode " + ccLongCode);    

                    tbEmployee employee = null;
                    if (userId != null && !userId.trim().isEmpty()) {
                        Optional<tbEmployee> optionalEmployee = employeeRepository.findEmpByEmpId(userId);
                        employee = optionalEmployee.orElse(null); // Allow employee to be null if not found
                    }

                    tbCostCenter costCenter = null;
                    if (ccLongCode != null && !ccLongCode.trim().isEmpty()) {
                        Optional<tbCostCenter> optionalCostCenter = costCenterRepository.findByCcLongCode(ccLongCode);
                        costCenter = optionalCostCenter.orElse(null); // Allow employee to be null if not found
                    }

                    deviceBatch.add(new Object[] {
                            peaNo,
                            description,
                            serialNo,
                            receivedDate,
                            receivedPrice,
                            leftPrice,
                            costCenter != null ? costCenter.getCcLongCode() : null, // Use Cost Center ID
                            employee != null ? employee.getEmpId() : null, // Use Employee ID if available, else
                            ccLongCode, // null
                    });
                }
                // System.out.println("deviceBatch " + deviceBatch);
                // for (Object[] objArray : deviceBatch) {
                //     logger.info("Device Entry: {}", Arrays.toString(objArray));
                // }
            }
            validateDevices(deviceList);

            deviceRepository.bulkInsertDevices(deviceBatch);

            return deviceList;
        } catch (

        IOException e) {
            throw new RuntimeException("Line: " + index + " failed to store excel data: " + e.getMessage());
        }
    }

    private void validateDevices(List<tbDevice> devices) {
        for (tbDevice device : devices) {
            // Mandatory field validations
            if (device.getDevPeaNo() == null || device.getDevPeaNo().isEmpty()) {
                throw new InvalidDataException("Device PEA No is missing or invalid.");
            }

            if (device.getDevDescription() != null && device.getDevDescription().length() > 500) {
                throw new InvalidDataException("Device description exceeds the maximum length.");
            }

            if (device.getDevSerialNo() != null && device.getDevSerialNo().length() > 255) {
                throw new InvalidDataException("Device serial number exceeds the maximum length.");
            }

            if (device.getDevReceivedDate() == null || device.getDevReceivedDate().isEmpty()) {
                throw new InvalidDataException("Device received date is missing or invalid.");
            }

            if (device.getDevReceivedPrice() == null || device.getDevReceivedPrice() <= 0) {
                throw new InvalidDataException("Device received price must be greater than 0.");
            }

            // Nullable field validations (Optional or logical checks)
            if (device.getDevLeftPrice() != null && device.getDevLeftPrice() < 0) {
                throw new InvalidDataException("Device left price cannot be negative.");
            }

            if (device.getTbCostCenter() != null && device.getTbCostCenter().getCcLongCode() == null) {
                throw new InvalidDataException("Cost center is invalid (missing required details).");
            }

            if (device.getTbEmployee() != null && device.getTbEmployee().getEmpId() == null) {
                throw new InvalidDataException("Employee is invalid (missing required details).");
            }

            if (device.getCcLongCodeString() != null && device.getCcLongCodeString().isEmpty()) {
                throw new InvalidDataException("CcLongCodeString is missing (but it nullable).");
            }
        }
    }

    private static final int BATCH_SIZE = 1000;

    public List<tbDevice> saveDevicesInBatch(List<tbDevice> devices) {
        List<tbDevice> savedDevices = new ArrayList<>();
        for (int i = 0; i < devices.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, devices.size());
            List<tbDevice> batch = devices.subList(i, end);
            savedDevices.addAll(deviceRepository.saveAll(batch));
            deviceRepository.flush(); // Flush after every batch
        }
        return savedDevices;
    }

    // @Transactional
    // public void bulkInsertDevices(List<tbDevice> deviceList) {
    // List<Object[]> deviceBatch = deviceList.stream()
    // .map(device -> new Object[] {
    // device.getDevPeaNo(),
    // device.getDevDescription(),
    // device.getDevSerialNo(),
    // device.getDevReceivedDate(),
    // device.getDevReceivedPrice(),
    // device.getDevLeftPrice(),
    // device.getTbCostCenter().getTbCostCenterId(),
    // device.getTbEmployee().getTbEmployeeId()
    // })
    // .collect(Collectors.toList());
    // deviceRepository.bulkInsertDevices(deviceBatch);
    // }

}
