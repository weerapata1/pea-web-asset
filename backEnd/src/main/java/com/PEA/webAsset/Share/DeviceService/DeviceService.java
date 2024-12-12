package com.PEA.webAsset.Share.DeviceService;

import com.PEA.webAsset.Entity.tbCostCenter;
import com.PEA.webAsset.Entity.tbDevice;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.DataFormatter;

@Service
public class DeviceService {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy");

    @Autowired
    CostCenterRepository costCenterRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public void postDevice(String dev_serialNo, String dev_note, String dev_description, String dev_peaNo,
            String tbCostCenterTest
    // ,String dateTimeNow ,String dateNow
    ) {
        String dateTimeTemp = now.format(dateTimeFormat);
        String dateTemp = now.format(dateFormat);
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeTemp, dateTimeFormat);
        LocalDate date = LocalDate.parse(dateTemp, dateFormat);

        try {
            tbDevice newDevice = new tbDevice();

            // newDevice.setDevPeaNo(dev_peaNo);
            // newDevice.setDevSerialNo(dev_serialNo);
            // newDevice.setDevNote(dev_note);
            // newDevice.setDevDescription(dev_description);
            // newDevice.setDevUpdate(dateTime);

            // newDevice.setTbCostCenter(costCenterRepository.findByCcLongCode(tbCostCenter));

            deviceRepository.save(newDevice);
        } catch (Exception e) {
            throw new RuntimeException("POST Fail : " + e.getMessage());
        }
    }

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

    public List<tbDevice> saveDevice(MultipartFile file) throws IOException {

        int index = 0;
        DataFormatter formatter = new DataFormatter();
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            List<tbDevice> deviceList = new ArrayList<>();
            XSSFSheet worksheet = workbook.getSheetAt(0);

            // Start from the second row (index 1) to skip the header
            for (index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
                XSSFRow row = worksheet.getRow(index);

                // Check if the row is null or if Column A (Cell(0)) is empty
                if (row == null || row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                    break; // Stop processing if Column A is empty
                }
                tbDevice device = new tbDevice();

                Double receivedPrice;
                Cell c10 = row.getCell(10);
                receivedPrice = (c10 == null || c10.getCellType() == CellType.BLANK)
                        ? 0.0
                        : row.getCell(10).getNumericCellValue();
                // if (index > 0) {
                // if (receivedPrice >= 1) {

                // String peaNo;
                // String description;
                // String serialNo;
                // String recievedDate;
                // Double leftPrice;
                // String ccLongCode;
                // String userId;
                // // Long id = (long) row.getCell(0).getNumericCellValue();
                // Cell c2 = row.getCell(2);
                // if (c2 == null || c2.getCellType() == CellType.BLANK) {
                // peaNo = "";
                // System.out.println("peaNo is BLANK at " + index);
                // } else {
                // peaNo = formatter.formatCellValue(row.getCell(2));
                // }
                // Cell c3 = row.getCell(3);
                // if (c3 == null || c3.getCellType() == CellType.BLANK) {
                // userId = "";
                // System.out.println("userId is BLANK at " + index);
                // } else {
                // userId = formatter.formatCellValue(row.getCell(3));
                // }
                // Cell c4 = row.getCell(4);
                // if (c4 == null || c4.getCellType() == CellType.BLANK) {
                // description = "";
                // System.out.println("description is BLANK at " + index);
                // } else {
                // description = formatter.formatCellValue(row.getCell(4));
                // }
                // Cell c5 = row.getCell(5);
                // if (c5 == null || c5.getCellType() == CellType.BLANK) {
                // serialNo = "";
                // System.out.println("serialNo is BLANK at " + index);
                // } else {
                // //serialNo = (String) row.getCell(5).getStringCellValue();
                // serialNo = formatter.formatCellValue(row.getCell(5));
                // }
                // Cell c9 = row.getCell(9);
                // if (c9 == null || c9.getCellType() == CellType.BLANK) {
                // recievedDate = "";
                // System.out.println("recievedDate is BLANK at " + index);
                // } else {
                // recievedDate = formatter.formatCellValue(row.getCell(9));
                // }
                // Cell c11 = row.getCell(11);
                // if (c11 == null || c11.getCellType() == CellType.BLANK) {
                // leftPrice = (double) 1;
                // System.out.println("leftPrice is BLANK at " + index);
                // } else {
                // leftPrice = (double) row.getCell(11).getNumericCellValue();
                // }
                // Cell c12 = row.getCell(12);
                // if (c12 == null || c12.getCellType() == CellType.BLANK) {
                // ccLongCode = "";
                // System.out.println("ccLongCode is BLANK at " + index);
                // } else {
                // ccLongCode = formatter.formatCellValue(row.getCell(12));
                // }

                // // System.out.println("id >" + id);
                // System.out.println("serialNo >" + serialNo);
                // System.out.println("peaNo >" + peaNo);
                // System.out.println("description >" + description);
                // System.out.println("ccLongCode >" + ccLongCode);

                // // device.setId(id);
                // device.setDevPeaNo(peaNo);
                // device.setDevDescription(description);
                // device.setDevSerialNo(serialNo);
                // device.setDevReceivedDate(recievedDate);
                // device.setDevReceivedPrice(receivedPrice);
                // device.setDevLeftPrice(leftPrice);
                // device.setTbCostCenter(costCenterRepository.findByCcLongCode(ccLongCode));
                // device.setTbEmployee(employeeRepository.findByEmpId(userId));
                // // device.setTbCostCenter(ccLongCode);
                // // device.setTbEmployee(userId);
                // deviceList.add(device);
                // }
                // }
                if (index > 0 && receivedPrice >= 1) {
                    // Extract and validate fields
                    String peaNo = extractCellValue(formatter, row.getCell(2), "peaNo", index);
                    String userId = extractCellValue(formatter, row.getCell(3), "userId", index);
                    String description = extractCellValue(formatter, row.getCell(4), "description", index);
                    String serialNo = extractCellValue(formatter, row.getCell(5), "serialNo", index);
                    String receivedDate = extractCellValue(formatter, row.getCell(9), "receivedDate", index);
                    Double leftPrice = extractCellNumericValue(row.getCell(11), "leftPrice", index);
                    String ccLongCode = extractCellValue(formatter, row.getCell(12), "ccLongCode", index);

                    Optional<tbCostCenter> optionalCostCenter = costCenterRepository.findByCcLongCode(ccLongCode);
                    tbCostCenter costCenter = optionalCostCenter
                    .orElseThrow(() -> new RuntimeException("Cost center not found for code: " + ccLongCode));
            
                    // Set fields to the device
                    device.setDevPeaNo(peaNo);
                    device.setDevDescription(description);
                    device.setDevSerialNo(serialNo);
                    device.setDevReceivedDate(receivedDate);
                    device.setDevReceivedPrice(receivedPrice);
                    device.setDevLeftPrice(leftPrice);
                    // device.setTbCostCenter(costCenterRepository.findByCcLongCode(ccLongCode));
                    device.setTbCostCenter(costCenter);
                    device.setTbEmployee(employeeRepository.findByEmpId(userId));

                    deviceList.add(device);
                }
            }
            // Validate devices
            validateDevices(deviceList);
            return deviceList;
        } catch (IOException e) {
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
        }
    }

    private String extractCellValue(DataFormatter formatter, Cell cell, String fieldName, int index) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            System.out.println(fieldName + " is BLANK at row " + index);
            return "";
        }
        return formatter.formatCellValue(cell);
    }

    private Double extractCellNumericValue(Cell cell, String fieldName, int index) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            System.out.println(fieldName + " is BLANK at row " + index);
            return 0.0;
        }
        return cell.getNumericCellValue();
    }
}
