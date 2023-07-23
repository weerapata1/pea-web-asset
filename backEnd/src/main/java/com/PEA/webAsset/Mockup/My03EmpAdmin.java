 package com.PEA.webAsset.Mockup;

 import com.PEA.webAsset.Entity.tbEmpAdmin;
 import com.PEA.webAsset.Repository.EmpAdminRepository;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.ApplicationArguments;
 import org.springframework.boot.ApplicationRunner;
 import org.springframework.context.annotation.Bean;
 import org.springframework.stereotype.Component;

 @Component
 public class My03EmpAdmin {
     private static final Logger logger = LoggerFactory.getLogger(My03EmpAdmin.class);
     @Autowired
     public void run(ApplicationArguments arguments){}

     @Bean
     public ApplicationRunner MyEmpAdmin(EmpAdminRepository empAdminRepository){
         return (args -> {
                 empAdminRepository.save(new tbEmpAdmin("นางศิริลักษณ์ เจริญถวิล","334205","หผ.ปค.","admin1","admin"));
                 empAdminRepository.save(new tbEmpAdmin("นายนรเชษฐ์ มงคล","498345","ชผ.ปค.","admin2","admin"));
                 empAdminRepository.save(new tbEmpAdmin("นายภาณุวิชญ์ ธานีวัฒน์","506027","นรค.6","admin3","admin"));
                 empAdminRepository.save(new tbEmpAdmin("นายวีรภัทร ทวีศักดิ์","512099","นรค.4","admin4","admin"));
         });
     }

 }
