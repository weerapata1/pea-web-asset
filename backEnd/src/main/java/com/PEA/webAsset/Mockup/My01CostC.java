////  package com.PEA.webAsset.Mockup;
//
//
// import com.PEA.webAsset.Entity.*;
// import com.PEA.webAsset.Repository.*;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.stereotype.Component;
//
//
////  import java.util.stream.Stream;
//
////  @Component
////  public class My01CostC implements ApplicationRunner {
//
////      @Override
////      public void run(ApplicationArguments args) throws Exception {
////      }
//
//<<<<<<< Updated upstream
////      @Bean
////      public ApplicationRunner MyCostC(CostCenterRepository costCenterRepository, EmployeeRepository employeeRepository, EmpRuleRepository empRuleRepository,
////                                       DeviceTypeRepository deviceTypeRepository, DeviceRepository deviceRepository, RepairStatusRepository repairStatusRepository) {
//=======
//     @Bean
//     public ApplicationRunner MyCostC(CostCenterRepository costCenterRepository, EmployeeRepository employeeRepository, EmpRuleRepository empRuleRepository,
//                                      DeviceTypeRepository deviceTypeRepository, DeviceRepository deviceRepository, RepairStatusRepository repairStatusRepository,
//                                      EquipmentLocationRepository equipmentLocationRepository) {
//>>>>>>> Stashed changes
//
////          return (args -> {
////              costCenterRepository.save(new tbCostCenter("E301023000", null, null, "กบห.กรท.-บห.", "กลุ่มบริหาร กองระบบสารสนเทศ-บริหาร"));
////              costCenterRepository.save(new tbCostCenter("E301023010", null, null, "ผสบ.กรท.-บห.", "แผนกสารสนเทศด้านบริการลูกค้า กรท.-บริหาร"));
////              costCenterRepository.save(new tbCostCenter("E301023020", null, null, "ผสจ.กรท.-บห.", "แผนกสารสนเทศด้านการจัดการองค์กร กรท.-บริหาร"));
////              costCenterRepository.save(new tbCostCenter("E301023030", null, null, "ผปข.กรท.-บห.", "แผนกปฏิบัติการเครือข่ายคอมพิวเตอร์ กรท.-บริหาร"));
////              costCenterRepository.save(new tbCostCenter("E301023040", null, null, "ผปค.กรท.-บห.", "แผนกปฏิบัติการคอมพิวเตอร์ กรท.-บริหาร"));
//
//<<<<<<< Updated upstream
////              empRuleRepository.save(new tbEmpRule("Admin"));
////              empRuleRepository.save(new tbEmpRule("User"));
//=======
//             empRuleRepository.save(new tbEmpRole("Admin"));
//             empRuleRepository.save(new tbEmpRole("User"));
//
//             equipmentLocationRepository.save(new tbEquipmentLocation("ห้องเก็บของ อาคาร 4"));
//             equipmentLocationRepository.save(new tbEquipmentLocation("ห้องใต้บันได"));
//             equipmentLocationRepository.save(new tbEquipmentLocation("พื้นที่ ผคข."));
//
//             Stream.of("รับเครื่อง","รับเรื่องแล้วกำลังดำเนินการ","เสร็จแล้ว","ส่งคืนเครื่องแล้ว").forEach(name ->{
//                 tbRepairStatus newStatus = new tbRepairStatus();
//                 newStatus.setStatusName(name);
//                 repairStatusRepository.save(newStatus);
//             });
//>>>>>>> Stashed changes
//
////              employeeRepository.save(new tbEmployee("263535","นายเฉลิมพร รัชชุศิริ","อก."," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("315748","นางสุภาวดี เตียวสุวัฒน์","รก."," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("434932","นายชิษณุพงศ์ สุทธิพงษ์ประชา","รก."," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("456900","นายปยา นนทศิลา","นรค.9"," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("453091","น.ส.อัญชลีพร รักษาสัตย์","นบท.8"," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("242458","นางรัตนา พยุงวงษ์","ผชน.8"," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("500047","นางจิราพร กันตะบุตร","พบค.6"," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000"),empRuleRepository.findEmpRuleById(2L)));
//
////              employeeRepository.save(new tbEmployee("493538","นางศรินรัตน์ ศุภกมลเสนีย์","หผ."," ผสบ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023010"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("508944","น.ส.จริยาวดี พรหมมาลี","นรค.5"," ผสบ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023010"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("498701","นายกฤติเดช เกษอาสา","พคค.6"," ผสบ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023010"),empRuleRepository.findEmpRuleById(2L)));
//
////              employeeRepository.save(new tbEmployee("334205","นางศิริลักษณ์ เจริญถวิล","หผ."," ผปค. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023040"),empRuleRepository.findEmpRuleById(1L)));
////              employeeRepository.save(new tbEmployee("498345","นายนรเชษฐ์ มงคล","ชผ."," ผปค. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023040"),empRuleRepository.findEmpRuleById(1L)));
////              employeeRepository.save(new tbEmployee("506027","นายภาณุวิชญ์ ธานีวัฒน์","นรค.6"," ผปค. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023040"),empRuleRepository.findEmpRuleById(1L)));
////              employeeRepository.save(new tbEmployee("512099","นายวีรภัทร ทวีศักดิ์","นรค.4"," ผปค. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023040"),empRuleRepository.findEmpRuleById(1L)));
//
////              employeeRepository.save(new tbEmployee("475124","นายฉัฐเมศร์ ศุภกมลเสนีย์","หผ."," ผสจ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023020"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("500695","นายรัตนโชติ สามิลา","นรค.6"," ผสจ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023020"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("506519","นายทนัญชัย สุขบุญส่ง","นรค.6"," ผสจ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023020"),empRuleRepository.findEmpRuleById(2L)));
////              employeeRepository.save(new tbEmployee("505975","นายวริศวงษ์ วสุรัตน์ธวัชกุล","พคค.5"," ผสจ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023020"),empRuleRepository.findEmpRuleById(2L)));
//
////              employeeRepository.save(new tbEmployee("495128","นายสุเธียรพงศ์ ธนาอภิสิทธิ์โสภณ","หผ."," ผปข. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023030"),null));
////              employeeRepository.save(new tbEmployee("504562","นายศุภโชค ประไวย์","วศก.6"," ผปข. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023030"),null));
////              employeeRepository.save(new tbEmployee("510831","นายธนทัต บูระพันธ์","นรค.4"," ผปข. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023030"),null));
////              employeeRepository.save(new tbEmployee("496674","น.ส.ธนาภา สอนสวาท","พคค.6"," ผปข. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023030"),null));
//
//
//<<<<<<< Updated upstream
////              Stream.of("Computer or labtop","Tablet","Monitor","Printer","UPS","อุปกรณ์สื่อสาร","อุปกรณ์ประกอบหรืออุปกรณ์อื่นๆ",
////                      "Software","Other").forEach(typeName ->{
////                  tbDeviceType tempDeviceType = new tbDeviceType();
////                  tempDeviceType.setDeviceTypeName(typeName);
////                  deviceTypeRepository.save(tempDeviceType);
////              });
//=======
//             Stream.of("Computer","Monitor","Labtop","Tablet","Printer","UPS","อุปกรณ์สื่อสาร","SSD","Internet Switch",
//                     "Software","Labtop สำหรับประชุม","Other").forEach(typeName ->{
//                 tbDeviceType tempDeviceType = new tbDeviceType();
//                 tempDeviceType.setDeviceTypeName(typeName);
//                 deviceTypeRepository.save(tempDeviceType);
//             });
//>>>>>>> Stashed changes
//
////              deviceRepository.save(new tbDevice("510317428-0","อุปกรณ์ประหยัดพลังงานเครื่องปรับอากาศ รก.รท.ฉ.2",
////                      "-","2559.11.29",3950D,1795.65D,costCenterRepository.findByCcLongCode("E301023000"),
////                      employeeRepository.findByEmpId("263535"),null,deviceTypeRepository.findByDtId(9L)));
//
////              deviceRepository.save(new tbDevice("510317429-0","อุปกรณ์ประหยัดพลังงานเครื่องปรับอากาศ อก.รท.ฉ.2",
////                      "-","2559.11.29",3950D,1795.65D,costCenterRepository.findByCcLongCode("E301023000"),
////                      employeeRepository.findByEmpId("315748"),null,deviceTypeRepository.findByDtId(9L)));
//
////              deviceRepository.save(new tbDevice("532120633-0","CPU \"ACER\" สต.035/2555 รุ่น VERITON M4610G",
////                      "4ST089PR3120523319","2559.11.29",29800D,1795.65D,costCenterRepository.findByCcLongCode("E301023000"),
////                      employeeRepository.findByEmpId("456900"),null,deviceTypeRepository.findByDtId(1L)));
//
////              deviceRepository.save(new tbDevice("532120633-1","CPU \"ACER\" สต.035/2555 รุ่น VERITON M4610G",
////                      "S0R02521402E192400","2559.11.29",4300D,1795.65D,costCenterRepository.findByCcLongCode("E301023000"),
////                      employeeRepository.findByEmpId("456900"),null,deviceTypeRepository.findByDtId(3L)));
//
////              deviceRepository.save(new tbDevice("532175968-0","คอมพิวเตอร์ NOTEBOOK สต.บ.2/2560",
////                      "78H7YLRNXB6C2800H","2560.3.6",22850D,1D,costCenterRepository.findByCcLongCode("E301023010"),
////                      employeeRepository.findByEmpId("508944"),null,deviceTypeRepository.findByDtId(1L)));
//
////              deviceRepository.save(new tbDevice("532181469-0","คอมพิวเตอร์ ยี่ห้อ HP รุ่น ProDesk 60 บ.8/2560",
////                      "78H7YLRNXB6C2800H","2560.3.6",22850D,1D,costCenterRepository.findByCcLongCode("E301023010"),
////                      employeeRepository.findByEmpId("508944"),null,deviceTypeRepository.findByDtId(1L)));
//
////              deviceRepository.save(new tbDevice("532181469-1","Monitor ยี่ห้อ HP รุ่น ProDesk 60 บ.8/2560",
////                      "6CM7060RZK","2560.4.10",6940D,1D,costCenterRepository.findByCcLongCode("E301023010"),
////                      employeeRepository.findByEmpId("508944"),null,deviceTypeRepository.findByDtId(3L)));
//
////              deviceRepository.save(new tbDevice("532214690-0","Notebook บ.50/2563  ลว. 15 เม.ย. 63",
////                      "L5NXCV00R761194","2560.4.10",6940D,6856.45D,costCenterRepository.findByCcLongCode("E301023020"),
////                      employeeRepository.findByEmpId("505975"),null,deviceTypeRepository.findByDtId(1L)));
//
////              deviceRepository.save(new tbDevice("532230611-0","NOTEBOOK ACER รุ่น Travelmate P214-53 บ.2/2564",
////                      "NXVPNST00G11009485","2560.4.10",6940D,6856.45D,costCenterRepository.findByCcLongCode("E301023020"),
////                      employeeRepository.findByEmpId("505975"),null,deviceTypeRepository.findByDtId(1L)));
//
////              deviceRepository.save(new tbDevice("532157230-0","NoteBook บ.1/2558 (สุดาวรัตน์)",
////                      "5CG4460H62","2558.3.6",26790D,1D,costCenterRepository.findByCcLongCode("E301023040"),
////                      employeeRepository.findByEmpId("334205"),null,deviceTypeRepository.findByDtId(1L)));
//
////              deviceRepository.save(new tbDevice("532206252-0","CPU Dell รุ่น  Optiplex 3060 MT บ.17/2562",
////                      "80LDNX2","2558.3.6",26790D,1D,costCenterRepository.findByCcLongCode("E301023040"),
////                      employeeRepository.findByEmpId("506027"),null,deviceTypeRepository.findByDtId(1L)));
////              ;
//
////              deviceRepository.save(new tbDevice("532206252-1","MONITOR \"DELL\" รุ่นOptiplex3060MTสัญญา บ.17/2562",
////                      "YYJR6FCC0095VDADB","2558.3.6",26790D,1D,costCenterRepository.findByCcLongCode("E301023040"),
////                      employeeRepository.findByEmpId("506027"),null,deviceTypeRepository.findByDtId(3L)));
//
//<<<<<<< Updated upstream
////              Stream.of("รับเครื่อง","รับเรื่องแล้วกำลังดำเนินการ","เสร็จแล้ว","ส่งคืนเครื่องแล้ว").forEach(name ->{
////                  tbRepairStatus newStatus = new tbRepairStatus();
////                  newStatus.setStatusName(name);
////                  repairStatusRepository.save(newStatus);
////              });
////          });
//
////      }
////  }
//=======
//             Stream.of("รับเครื่อง","รับเรื่องแล้วกำลังดำเนินการ","เสร็จแล้ว","ส่งคืนเครื่องแล้ว").forEach(name ->{
//                 tbRepairStatus newStatus = new tbRepairStatus();
//                 newStatus.setStatusName(name);
//                 repairStatusRepository.save(newStatus);
//             });
//
//
//         });
//
//
//
//     }
// }
//>>>>>>> Stashed changes
