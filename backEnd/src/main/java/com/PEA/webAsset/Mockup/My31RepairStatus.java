 package com.PEA.webAsset.Mockup;

 import com.PEA.webAsset.Entity.tbRepairStatus;
 import com.PEA.webAsset.Repository.RepairRepository;
 import com.PEA.webAsset.Repository.RepairStatusRepository;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.boot.ApplicationArguments;
 import org.springframework.boot.ApplicationRunner;
 import org.springframework.context.annotation.Bean;
 import org.springframework.stereotype.Component;

 import java.util.stream.Stream;

 @Component
 public class My31RepairStatus implements ApplicationRunner {
     private static final Logger logger = LoggerFactory.getLogger(My31RepairStatus.class);

     @Override
     public void run(ApplicationArguments args) throws Exception {
     }

     @Bean
     public ApplicationRunner MyRepair(RepairRepository repairRepository, RepairStatusRepository repairStatusRepository
                                       ){
         return (args -> {
             Stream.of("รับเครื่อง","รับเรื่องแล้วกำลังดำเนินการ","เสร็จแล้ว","ส่งคืนเครื่องแล้ว").forEach(name ->{
                 tbRepairStatus newStatus = new tbRepairStatus();
                 newStatus.setStatusName(name);
                 repairStatusRepository.save(newStatus);
             });
         });
     }
 }
