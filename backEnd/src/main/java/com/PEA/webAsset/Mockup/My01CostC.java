 package com.PEA.webAsset.Mockup;

 import com.PEA.webAsset.Entity.tbCostCenterTest;
 import com.PEA.webAsset.Repository.CostCenterRepository;
 import com.PEA.webAsset.Repository.EmployeeRepository;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.boot.ApplicationArguments;
 import org.springframework.boot.ApplicationRunner;
 import org.springframework.context.annotation.Bean;
 import org.springframework.stereotype.Component;

 @Component
 public class My01CostC implements ApplicationRunner {
     private static final Logger logger = LoggerFactory.getLogger(My01CostC.class);

     @Override
     public void run(ApplicationArguments args) throws Exception {
     }

     @Bean
     public ApplicationRunner MyCostC(CostCenterRepository costCenterRepository, EmployeeRepository employeeRepository) {

         return (args -> {
             costCenterRepository.save(new tbCostCenterTest("E301023000", "", "", "กบห.กรท.-บห.", "กลุ่มบริหาร กองระบบสารสนเทศ-บริหาร"));
             costCenterRepository.save(new tbCostCenterTest("E301023010", "", "", "ผสบ.กรท.-บห.", "แผนกสารสนเทศด้านบริการลูกค้า กรท.-บริหาร"));
             costCenterRepository.save(new tbCostCenterTest("E301023020", "", "", "ผสจ.กรท.-บห.", "แผนกสารสนเทศด้านการจัดการองค์กร กรท.-บริหาร"));
             costCenterRepository.save(new tbCostCenterTest("E301023030", "", "", "ผปข.กรท.-บห.", "แผนกปฏิบัติการเครือข่ายคอมพิวเตอร์ กรท.-บริหาร"));
             costCenterRepository.save(new tbCostCenterTest("E301023040", "", "", "ผปค.กรท.-บห.", "แผนกปฏิบัติการคอมพิวเตอร์ กรท.-บริหาร"));

         });
     }
 }
