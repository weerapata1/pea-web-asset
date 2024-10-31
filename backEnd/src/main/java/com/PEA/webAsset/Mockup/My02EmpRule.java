// package com.PEA.webAsset.Mockup;
//
//
// import com.PEA.webAsset.Entity.tbEmpRule;
// import com.PEA.webAsset.Repository.EmpRuleRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.stereotype.Component;
//
// @Component
// public class My02EmpRule {
//     @Autowired
//     public void run(ApplicationArguments args){}
//
//     @Bean
//     public ApplicationRunner MyEmpAdmin(EmpRuleRepository empRuleRepository){
//
//         return (args -> {
//                 empRuleRepository.save(new tbEmpRule("Admin"));
//                 empRuleRepository.save(new tbEmpRule("User"));
//
//         });
//     }
//
// }
