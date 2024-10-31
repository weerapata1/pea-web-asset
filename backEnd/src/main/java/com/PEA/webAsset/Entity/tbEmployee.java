package com.PEA.webAsset.Entity;

import lombok.*;

import  jakarta.persistence.*;
//import javax.validation.constraints.NotNull;



@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "tb_employees")
@Table(name = "tb_employees")
@ToString
public class tbEmployee {
    @Id
    @SequenceGenerator(name = "emp_seq", sequenceName = "emp_seq")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "emp_seq")
    private Long id;

//    @Column(name = "empId", unique = true, nullable = false)
    private String empId; // รหัสพนักงาน

    private String empName; // ชื่อพนักงาน

    private String empRole;  //ตำแหน่ง

    private String empDepFull; // ชื่อแผนก

    // private String CostCenter; // ชื่อศุนย์ต้นทุน ไม่ผูก

    @ManyToOne(targetEntity = tbCostCenter.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_id", insertable = true, nullable = true )
    private tbCostCenter CostCenter;

    @ManyToOne(targetEntity = tbEmpRule.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_rule_id", insertable = true, nullable = true )
    private tbEmpRule EmpRule;

    // private String empCcShortName; //

    public tbEmployee(String empId, String empName, String empRole, String empDepFull, tbCostCenter CostCenter , tbEmpRule EmpRule) {
        this.empId = empId;
        this.empName = empName;
        this.empDepFull = empDepFull;
        this.empRole = empRole;
        this.CostCenter = CostCenter;
        this.EmpRule = EmpRule;
    }


}
