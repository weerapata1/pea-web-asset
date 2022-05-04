package com.PEA.webAsset.Share.ExcelService;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.DeviceRepository;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExcelService {
<<<<<<< Updated upstream
    @Autowired
    DeviceTypeRepository deviceTypeRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    CostCenterRepository costCenterRepository;

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy");


//    public void saveExcel(MultipartFile files) throws IOException {
//        try {
//            List<Tutorial> deviceList = new ArrayList<Tutorial>();
//
//            XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
//            XSSFSheet worksheet = workbook.getSheetAt(0);
//
//            for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
//                if (index > 0) {
//                    Tutorial product = new Tutorial();
//
//                    XSSFRow row = worksheet.getRow(index);
//                    Long id = (long) row.getCell(0).getNumericCellValue();
//                    String title = row.getCell(1).getStringCellValue();
//                    String description = row.getCell(2).getStringCellValue();
//                    Boolean published = row.getCell(3).getBooleanCellValue();
//                    Long dtId = (long) row.getCell(4).getNumericCellValue();
//
//                    product.setId(id);
//                    product.setTitle(title);
//                    product.setDescription(description);
//                    product.setPublished(published);
//                    product.setTbDeviceType(deviceTypeRepository.findByDtId(dtId));
//
//                    tutorialRepository.save(product);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("fail to store excel data: " + e.getMessage());
//        }
//    }

=======
    @Autowired DeviceTypeRepository deviceTypeRepository;
    @Autowired DeviceRepository deviceRepository;
    @Autowired CostCenterRepository costCenterRepository;

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy");

>>>>>>> Stashed changes
    public List<tbDevice> saveDevice(MultipartFile file) throws IOException {

        LocalDate dateNow = LocalDate.now();
        String dateNowFormat = dateNow.format(dateFormat);
        LocalDate dateReceived = LocalDate.parse(dateNowFormat, dateFormat);

        try {
            List<tbDevice> deviceList = new ArrayList<tbDevice>();

            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
                if (index > 0) {
                    tbDevice device = new tbDevice();

                    XSSFRow row = worksheet.getRow(index);
                    Long id = (long) row.getCell(0).getNumericCellValue();
                    String serialNo = row.getCell(1).getStringCellValue();
                    String peaNo = row.getCell(2).getStringCellValue();
                    String description = row.getCell(3).getStringCellValue();
                    String ccId = row.getCell(4).getStringCellValue();

//                    device.setId(id);
                    device.setDevPeaNo(serialNo);
                    device.setDevSerialNo(peaNo);
                    device.setDevDescription(description);
                    device.setDevReceived(dateReceived);
                    device.setTbCostCenter(costCenterRepository.findByCcLongCode(ccId));

                    deviceList.add(device);
                }
            }
            workbook.close();
            return deviceList;
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
