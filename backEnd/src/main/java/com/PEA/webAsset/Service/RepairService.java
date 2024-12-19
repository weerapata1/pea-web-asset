package com.PEA.webAsset.Service;

import org.springframework.stereotype.Service;

@Service
public class RepairService {

    public static boolean CategoryOfItEquipment(String peaNo){
        String peaNoLower = peaNo.toLowerCase();
        System.out.println("peaNoLower : " + peaNoLower);
        return (peaNoLower.length() >= 1  && peaNoLower.substring(0, 2).equals("53") ||
                peaNoLower.length() >= 1 && peaNoLower.substring(0, 2).equals("zc"))
                ?  true :  false;
    }
}
