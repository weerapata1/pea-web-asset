package com.PEA.webAsset.Share.DeviceService;

import com.PEA.webAsset.Entity.tbDevice;
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

import java.io.Console;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

        LocalDate dateNow = LocalDate.now();
        String dateNowFormat = dateNow.format(dateFormat);
        LocalDate dateReceived = LocalDate.parse(dateNowFormat, dateFormat);
        int index = 0;
        DataFormatter formatter = new DataFormatter();
        try {
            List<tbDevice> deviceList = new ArrayList<tbDevice>();

            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (index++; index < worksheet.getPhysicalNumberOfRows(); index++) {
                tbDevice device = new tbDevice();
                XSSFRow row = worksheet.getRow(index);
                Double receivedPrice;
                Cell c10 = row.getCell(10);
                if (c10 == null || c10.getCellType() == CellType.BLANK) {
                    receivedPrice = (double) 0;
                } else {
                    receivedPrice = (double) row.getCell(10).getNumericCellValue();
                }

                if (index > 0) {
                    if (receivedPrice >= 1) {

                        String peaNo;
                        String description;
                        String serialNo;
                        String recievedDate;
                        Double leftPrice;
                        String ccId;
                        String userId;
                        // Long id = (long) row.getCell(0).getNumericCellValue();
                        Cell c2 = row.getCell(2);
                        if (c2 == null || c2.getCellType() == CellType.BLANK) {
                            peaNo = "";
                            System.out.println("peaNo is BLANK at " + index);
                        } else {
                            peaNo = formatter.formatCellValue(row.getCell(2));
                        }
                        Cell c3 = row.getCell(3);
                        if (c3 == null || c3.getCellType() == CellType.BLANK) {
                            userId = "";
                            System.out.println("userId is BLANK at " + index);
                        } else {
                            userId = formatter.formatCellValue(row.getCell(3));
                        }
                        Cell c4 = row.getCell(4);
                        if (c4 == null || c4.getCellType() == CellType.BLANK) {
                            description = "";
                            System.out.println("description is BLANK at " + index);
                        } else {
                            description = formatter.formatCellValue(row.getCell(4));
                        }
                        Cell c5 = row.getCell(5);
                        if (c5 == null || c5.getCellType() == CellType.BLANK) {
                            serialNo = "";
                            System.out.println("serialNo is BLANK at " + index);
                        } else {
                            //serialNo = (String) row.getCell(5).getStringCellValue();
                            serialNo = formatter.formatCellValue(row.getCell(5));
                        }
                        Cell c9 = row.getCell(9);
                        if (c9 == null || c9.getCellType() == CellType.BLANK) {
                            recievedDate = "";
                            System.out.println("recievedDate is BLANK at " + index);
                        } else {
                            recievedDate = formatter.formatCellValue(row.getCell(9));
                        }
                        Cell c11 = row.getCell(11);
                        if (c11 == null || c11.getCellType() == CellType.BLANK) {
                            leftPrice = (double) 1;
                            System.out.println("leftPrice is BLANK at " + index);
                        } else {
                            leftPrice = (double) row.getCell(11).getNumericCellValue();
                        }
                        Cell c12 = row.getCell(12);
                        if (c12 == null || c11.getCellType() == CellType.BLANK) {
                            ccId = "";
                            System.out.println("ccId is BLANK at " + index);
                        } else {
                            ccId = formatter.formatCellValue(row.getCell(12));
                        }
                        // String peaNo = row.getCell(2).getStringCellValue();
                        // String description = row.getCell(4).getStringCellValue();
                        // String serialNo = row.getCell(5).getStringCellValue();
                        // String recievedDate = row.getCell(9).getStringCellValue();
                        // Double leftPrice = (double) row.getCell(11).getNumericCellValue();
                        // String ccId = row.getCell(12).getStringCellValue();

                        // System.out.println("id >" + id);
                        System.out.println("serialNo >" + serialNo);
                        System.out.println("peaNo >" + peaNo);
                        System.out.println("description >" + description);
                        System.out.println("ccId >" + ccId);

                        // device.setId(id);
                        device.setDevPeaNo(peaNo);
                        device.setDevDescription(description);
                        device.setDevSerialNo(serialNo);
                        device.setDevReceivedDate(recievedDate);
                        device.setDevReceivedPrice(receivedPrice);
                        device.setDevLeftPrice(leftPrice);
                        device.setTbCostCenterTest(costCenterRepository.findByCcLongCode(ccId));
                        device.setTbEmployee(employeeRepository.findByEmpId(userId));

                        deviceList.add(device);
                    }
                }
            }
            workbook.close();
            return deviceList;
        } catch (IOException e) {
            throw new RuntimeException("Line: " + index + "fail to store excel data: " + e.getMessage());
        }
    }

}
