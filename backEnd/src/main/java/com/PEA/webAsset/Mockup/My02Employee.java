package com.PEA.webAsset.Mockup;

import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class My02Employee implements ApplicationRunner {
    @Autowired
    public void run(ApplicationArguments args){}

    @Bean
    public ApplicationRunner MyEmployee(EmployeeRepository employeeRepository, CostCenterRepository costCenterRepository){
        return (args ->{
            employeeRepository.save(new tbEmployee("263535","นายเฉลิมพร รัชชุศิริ","อก."," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000")));
            employeeRepository.save(new tbEmployee("315748","นางสุภาวดี เตียวสุวัฒน์","รก."," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000")));
            employeeRepository.save(new tbEmployee("434932","นายชิษณุพงศ์ สุทธิพงษ์ประชา","รก."," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000")));
            employeeRepository.save(new tbEmployee("456900","นายปยา นนทศิลา","นรค.9"," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000")));
            employeeRepository.save(new tbEmployee("453091","น.ส.อัญชลีพร รักษาสัตย์","นบท.8"," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000")));
            employeeRepository.save(new tbEmployee("242458","นางรัตนา พยุงวงษ์","ผชน.8"," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000")));
            employeeRepository.save(new tbEmployee("500047","นางจิราพร กันตะบุตร","พบค.6"," กรท. ฝบพ.(ฉ2) กฟฉ.2",costCenterRepository.findByCcLongCode("E301023000")));

            employeeRepository.save(new tbEmployee("493538","นางศรินรัตน์ ศุภกมลเสนีย์","หผ."," ผสบ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023010")));
            employeeRepository.save(new tbEmployee("508944","น.ส.จริยาวดี พรหมมาลี","นรค.5"," ผสบ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023010")));
            employeeRepository.save(new tbEmployee("498701","นายกฤติเดช เกษอาสา","พคค.6"," ผสบ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023010")));

            employeeRepository.save(new tbEmployee("334205","นางศิริลักษณ์ เจริญถวิล","หผ."," ผปค. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023040")));
            employeeRepository.save(new tbEmployee("498345","นายนรเชษฐ์ มงคล","ชผ."," ผปค. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023040")));
            employeeRepository.save(new tbEmployee("506027","นายภาณุวิชญ์ ธานีวัฒน์","นรค.6"," ผปค. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023040")));
            employeeRepository.save(new tbEmployee("512099","นายวีรภัทร ทวีศักดิ์","นรค.4"," ผปค. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023040")));

            employeeRepository.save(new tbEmployee("475124","นายฉัฐเมศร์ ศุภกมลเสนีย์","หผ."," ผสจ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023020")));
            employeeRepository.save(new tbEmployee("500695","นายรัตนโชติ สามิลา","นรค.6"," ผสจ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023020")));
            employeeRepository.save(new tbEmployee("506519","นายทนัญชัย สุขบุญส่ง","นรค.6"," ผสจ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023020")));
            employeeRepository.save(new tbEmployee("505975","นายวริศวงษ์ วสุรัตน์ธวัชกุล","พคค.5"," ผสจ. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023020")));

            employeeRepository.save(new tbEmployee("495128","นายสุเธียรพงศ์ ธนาอภิสิทธิ์โสภณ","หผ."," ผปข. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023030")));
            employeeRepository.save(new tbEmployee("504562","นายศุภโชค ประไวย์","วศก.6"," ผปข. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023030")));
            employeeRepository.save(new tbEmployee("510831","นายธนทัต บูระพันธ์","นรค.4"," ผปข. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023030")));
            employeeRepository.save(new tbEmployee("496674","น.ส.ธนาภา สอนสวาท","พคค.6"," ผปข. กรท. ฝบพ.(ฉ2)",costCenterRepository.findByCcLongCode("E301023030")));
        });
    }
}
