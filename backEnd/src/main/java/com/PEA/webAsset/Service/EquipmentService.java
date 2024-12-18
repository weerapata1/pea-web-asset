package com.PEA.webAsset.Service;

import com.PEA.webAsset.Entity.tbEquipment;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
import com.PEA.webAsset.Repository.EquipmentLocationRepository;
import com.PEA.webAsset.Repository.EquipmentRepository;
import com.PEA.webAsset.Share.DateService.DateService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @Autowired
    private EquipmentLocationRepository equipmentLocationRepository;

    public List<tbEquipment> addEquipmentFormExcel(MultipartFile file, Long dType,Long locId) throws Exception {

        List<tbEquipment> equipmentList = new ArrayList<>();

        InputStream inputStream = file.getInputStream();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        for(int col = 1 ; col <= sheet.getLastRowNum(); col++){
            Row row = sheet.getRow(col);

            tbEquipment newEquipment = new tbEquipment();

            newEquipment.setIsActivated(true);
            newEquipment.setReceiveDate(DateService.localDateNow());
            newEquipment.setEquipmentDescription((row.getCell(6).getStringCellValue()));
            newEquipment.setSerialNumber((row.getCell(7).getStringCellValue()));
            newEquipment.setDeviceType(deviceTypeRepository.findByDtId(dType));
            newEquipment.setEquipmentLocation(equipmentLocationRepository.findLocById(locId));

            equipmentList.add(newEquipment);
        }
        System.out.println(equipmentList);

        equipmentRepository.saveAll(equipmentList);
        return equipmentList;
    }

    public Object getQtyEquipment(String dType){
        Collection<tbEquipment> EquipmentDetail = equipmentRepository.findEquipmentQuantityAndAvailabilityByDeviceType(dType);
        Map<String ,Object> map1 = new HashMap<>();
        map1.put("Detail",EquipmentDetail);


        Map<String, Object> map2 = new  HashMap<>();
        map2.put("Availability",EquipmentDetail.size());

        Map<String ,Object> merge = new HashMap<>(map1);
        merge.putAll(map2);

        return merge;
    }

    public Optional<tbEquipment> requisitionEquipment(){

        return null;
    }
}
