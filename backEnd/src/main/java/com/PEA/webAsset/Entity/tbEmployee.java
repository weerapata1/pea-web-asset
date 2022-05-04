package com.PEA.webAsset.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

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
    private String empId;

    private String empName;

    // public tbEmployee(String empId, String empName){
    //     this.empId = empId;
    //     this.empName = empName;
    // }
}
