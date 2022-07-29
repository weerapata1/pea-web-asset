package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "tb_employees")
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "tb_employees")
@ToString
public class tbEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq",sequenceName = "emp_seq")
    @Column(name = "empId", unique = true,nullable = false)
    @NotNull
    private String empId; // รหัสพนักงาน

    private String empName; // ชื่อพนักงาน


    private String empRole;  //ตำแหน่ง

    private String empDepFull; // ชื่อแผนก

    private String empCcId;  //

    private String empCcShortName; //

}
