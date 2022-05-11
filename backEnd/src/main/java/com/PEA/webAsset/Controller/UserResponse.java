package com.PEA.webAsset.Controller;

public class UserResponse {

    private Long id;
    private String empId;
    private String empName;

    public UserResponse(Long id, String empId, String empName){
        this.id = id;
        this.empId = empId;
        this.empName =  empName;
    }

}
