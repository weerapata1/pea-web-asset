package com.PEA.webAsset.Controller;


import com.PEA.webAsset.Entity.tbEmpAdmin;
import com.PEA.webAsset.Repository.EmpAdminRepository;
import com.PEA.webAsset.Share.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empAdmin")
public class EmpAdminController {
    @Autowired
    private EmpAdminRepository empAdminRepository;

    @GetMapping("/getAllAd")
    public Collection<tbEmpAdmin> getAllAd() {
        return empAdminRepository.findAll().stream().collect(Collectors.toList());
    }

    // @GetMapping("/login")
    // public ResponseEntity<ResponseMessage> loginService(@RequestParam("userName") String userName, @RequestParam("Password") String Password) {

    //     String message = "";
    //     tbEmpAdmin admin = empAdminRepository.findAdminPasswordByAdminUserName(userName);
    //     System.out.println("username : "+ userName + " password : "+ Password);
    //     if (new String(userName).equals(admin.getAdminUserName()) && new String(Password).equals(admin.getAdminPassword()) ){
    //         System.out.println("true");
    //         message = "true";
    //         return ResponseEntity
    //                 .status(HttpStatus.OK)
    //                 .body(new ResponseMessage(message));
    //     } else {
    //         System.out.println("fail");
    //         message = "fail";
    //         return ResponseEntity
    //                 .status(HttpStatus.BAD_REQUEST)
    //                 .body(new ResponseMessage(message));
    //     }
    // }
}
