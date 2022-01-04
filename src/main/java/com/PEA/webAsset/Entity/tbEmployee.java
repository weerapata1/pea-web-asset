//package com.PEA.webAsset.Entity;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity(name = "tb_employees")
//@Data
//@Getter
//@Setter
//@EqualsAndHashCode
//@NoArgsConstructor
////@AllArgsConstructor
//@Table(name = "tb_employees")
//@ToString
//public class tbEmployee {
//    @Id
//    @Column(name = "empId", unique = true,nullable = false)
//    @NotNull
//    private Long empId;
//
//    private String emp_name;
//
//    private String emp_office;
//
//    //   Join tbDevice.class------------------------------
//    @OneToMany(mappedBy = "tbEmployee")
//    private List<tbDevice> tbDevices = new ArrayList<tbDevice>();
//
//}
