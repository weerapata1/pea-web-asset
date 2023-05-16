package com.PEA.webAsset.Mockup;

import com.PEA.webAsset.Entity.tbDevice;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.DeviceRepository;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
import com.PEA.webAsset.Repository.EmployeeRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class My27Device {
    private static final Logger logger = LoggerFactory.getLogger(My27Device.class);
    @Autowired
    public void run(ApplicationArguments arguments){}

    @Bean
    public ApplicationRunner MyDevice(DeviceRepository deviceRepository, EmployeeRepository employeeRepository, CostCenterRepository costCenterRepository, DeviceTypeRepository deviceTypeRepository){
        return (args -> {
            deviceRepository.save(new tbDevice("510317428-0","อุปกรณ์ประหยัดพลังงานเครื่องปรับอากาศ รก.รท.ฉ.2",
                    "-","2559.11.29",3950D,1795.65D,costCenterRepository.findByCcLongCode("E301023000"),
                    employeeRepository.findByEmpId("263535"),null,deviceTypeRepository.findByDtId(9L)));

            deviceRepository.save(new tbDevice("510317429-0","อุปกรณ์ประหยัดพลังงานเครื่องปรับอากาศ อก.รท.ฉ.2",
                    "-","2559.11.29",3950D,1795.65D,costCenterRepository.findByCcLongCode("E301023000"),
                    employeeRepository.findByEmpId("315748"),null,deviceTypeRepository.findByDtId(9L)));

            deviceRepository.save(new tbDevice("532120633-0","CPU \"ACER\" สต.035/2555 รุ่น VERITON M4610G",
                    "4ST089PR3120523319","2559.11.29",29800D,1795.65D,costCenterRepository.findByCcLongCode("E301023000"),
                    employeeRepository.findByEmpId("456900"),null,deviceTypeRepository.findByDtId(1L)));

            deviceRepository.save(new tbDevice("532120633-1","CPU \"ACER\" สต.035/2555 รุ่น VERITON M4610G",
                    "S0R02521402E192400","2559.11.29",4300D,1795.65D,costCenterRepository.findByCcLongCode("E301023000"),
                    employeeRepository.findByEmpId("456900"),null,deviceTypeRepository.findByDtId(3L)));

            deviceRepository.save(new tbDevice("532175968-0","คอมพิวเตอร์ NOTEBOOK สต.บ.2/2560",
                    "78H7YLRNXB6C2800H","2560.3.6",22850D,1D,costCenterRepository.findByCcLongCode("E301023010"),
                    employeeRepository.findByEmpId("508944"),null,deviceTypeRepository.findByDtId(1L)));

            deviceRepository.save(new tbDevice("532181469-0","คอมพิวเตอร์ ยี่ห้อ HP รุ่น ProDesk 60 บ.8/2560",
                    "78H7YLRNXB6C2800H","2560.3.6",22850D,1D,costCenterRepository.findByCcLongCode("E301023010"),
                    employeeRepository.findByEmpId("508944"),null,deviceTypeRepository.findByDtId(1L)));

            deviceRepository.save(new tbDevice("532181469-1","Monitor ยี่ห้อ HP รุ่น ProDesk 60 บ.8/2560",
                    "6CM7060RZK","2560.4.10",6940D,1D,costCenterRepository.findByCcLongCode("E301023010"),
                    employeeRepository.findByEmpId("508944"),null,deviceTypeRepository.findByDtId(3L)));

            deviceRepository.save(new tbDevice("532214690-0","Notebook บ.50/2563  ลว. 15 เม.ย. 63",
                    "L5NXCV00R761194","2560.4.10",6940D,6856.45D,costCenterRepository.findByCcLongCode("E301023020"),
                    employeeRepository.findByEmpId("505975"),null,deviceTypeRepository.findByDtId(1L)));

            deviceRepository.save(new tbDevice("532230611-0","NOTEBOOK ACER รุ่น Travelmate P214-53 บ.2/2564",
                    "NXVPNST00G11009485","2560.4.10",6940D,6856.45D,costCenterRepository.findByCcLongCode("E301023020"),
                    employeeRepository.findByEmpId("505975"),null,deviceTypeRepository.findByDtId(1L)));

            deviceRepository.save(new tbDevice("532157230-0","NoteBook บ.1/2558 (สุดาวรัตน์)",
                    "5CG4460H62","2558.3.6",26790D,1D,costCenterRepository.findByCcLongCode("E301023040"),
                    employeeRepository.findByEmpId("334205"),null,deviceTypeRepository.findByDtId(1L)));

            deviceRepository.save(new tbDevice("532206252-0","CPU Dell รุ่น  Optiplex 3060 MT บ.17/2562",
                    "80LDNX2","2558.3.6",26790D,1D,costCenterRepository.findByCcLongCode("E301023040"),
                    employeeRepository.findByEmpId("506027"),null,deviceTypeRepository.findByDtId(1L)));
;

            deviceRepository.save(new tbDevice("532206252-1","MONITOR \"DELL\" รุ่นOptiplex3060MTสัญญา บ.17/2562",
                    "YYJR6FCC0095VDADB","2558.3.6",26790D,1D,costCenterRepository.findByCcLongCode("E301023040"),
                    employeeRepository.findByEmpId("506027"),null,deviceTypeRepository.findByDtId(3L)));
        });
    }
}
