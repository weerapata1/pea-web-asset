package com.PEA.webAsset.Entity;

import lombok.*;

import  jakarta.persistence.*;
//import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "tb_employee")
@Table(name = "tb_employee")
@ToString
public class tbEmployee {
    // @Id
    // @SequenceGenerator(name = "emp_seq", sequenceName = "emp_seq")
    // @GeneratedValue(strategy = GenerationType.AUTO,generator = "emp_seq")
    // @Column(name = "employee_id", unique = true, nullable = false)
    // private Long employeeId;
    
    @Id
    @Column(name = "emp_id", unique = true, nullable = false)
    private String empId; // รหัสพนักงาน

    private String empName; // ชื่อพนักงาน

    private String empRank;  //ตำแหน่ง

    private String empDepFull; // ชื่อแผนก

    // private String CostCenter; // ชื่อศุนย์ต้นทุน ไม่ผูก

    @ManyToOne(targetEntity = tbCostCenter.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_long_code", referencedColumnName = "cc_long_code", nullable = true)
    private tbCostCenter costCenter;

    @Transient
    private String costCenterCode;

    @ManyToOne(targetEntity = tbEmpRole.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_role_id", insertable = true, nullable = true )
    private tbEmpRole empRole;

    // private String empCcShortName; //


    public tbEmployee(String empId, String empName, tbEmpRole empRole, String empDepFull, tbCostCenter costCenter , String empRank) {
        this.empId = empId;
        this.empName = empName;
        this.empDepFull = empDepFull;
        this.empRole = empRole;
        this.costCenter = costCenter;
        this.empRank = empRank;
    }

}
