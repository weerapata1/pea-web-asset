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
    @Column(name = "empId", unique = true, nullable = false)
    @NotNull
    private String empId; // รหัสพนักงาน

    private String empName; // ชื่อพนักงาน

    private String empRole;  //ตำแหน่ง

    private String empDepFull; // ชื่อแผนก

    // private String CostCenter; // ชื่อศุนย์ต้นทุน ไม่ผูก

    @ManyToOne(targetEntity = tbCostCenterTest.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_id", insertable = true, nullable = true)
    private tbCostCenterTest CostCenter;

    // private String empCcShortName; //

    public tbEmployee(String empId, String empName, String empRole, String empDepFull, tbCostCenterTest CostCenter) {
        this.empId = empId;
        this.empName = empName;
        this.empDepFull = empDepFull;
        this.empRole = empRole;
        this.CostCenter = CostCenter;
    }


}
