package com.PEA.webAsset.DTO;

import lombok.Data;

@Data
public class RepairRequestsDTO {
    private String sendPhoneNum ;
    private String defectDetail;
    private String peaNo;
    private String adminReceive;
    private String empSend;
}
