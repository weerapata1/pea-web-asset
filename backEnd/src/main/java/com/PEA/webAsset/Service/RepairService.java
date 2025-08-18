package com.PEA.webAsset.Service;
import com.PEA.webAsset.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;


@Service
public class RepairService {
    @Autowired
    private static RepairRepository repairRepository;

    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public static boolean CategoryOfItEquipment(String peaNo){
        String peaNoLower = peaNo.toLowerCase();
        System.out.println("peaNoLower : " + peaNoLower);
        return (peaNoLower.length() >= 1  && peaNoLower.substring(0, 2).equals("53") ||
                peaNoLower.length() >= 1 && peaNoLower.substring(0, 2).equals("zc"))
                ?  true :  false;
    }

    public static String GeneratePrefixNumber(){
        Long temp = repairRepository.findSequentOfRepair() + 1L;
        String year = String.valueOf(Year.now());
        return "RP-" + year + "-" + chkPreFixNum(temp) + temp;
    }
    private static String chkPreFixNum(Long temp){
        String stringTemp = "";
        if(temp <=9)
            stringTemp = "0000";
        else if (temp >=10 && temp <100)
            stringTemp = "000";
        else if (temp >=100 && temp <1000)
            stringTemp = "00";
        else
            stringTemp = "0";
        return stringTemp;
    }

    //    @GetMapping("/sequent")
//    public String getSeq(){
//        Long seqTemp = repairRepository.findSequentOfRepair() + 1;
//        System.out.println(seqTemp);
//        return String.valueOf(seqTemp);
//    }
}
