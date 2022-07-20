package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Getter @Setter
@Entity(name = "tb_repair")
@Table(name = "tb_repair")
@NoArgsConstructor
@EqualsAndHashCode
public class tbRepair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rep_seq")
    @SequenceGenerator(name = "rep_seq", sequenceName = "rep_seq")
    @Column(name = "repairId")
    private Long repairId;

    private LocalDateTime SendDate; //ส่งเรื่องซ่อม
    private String damageDetail;    //อาการที่เสีย
    @ManyToOne(targetEntity = tbEmployee.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "empSend",insertable = true,referencedColumnName = "empId")
//    @JsonBackReference
    private tbEmployee empSend; // คนนำเครื่องมาส่ง

    private LocalDateTime admitDate; //รับเรื่องซ่อม
    private String examineDamage; // อาการเบื้องต้น
    @ManyToOne(targetEntity = tbEmpAdmin.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "adminReceive",insertable = true,referencedColumnName = "id")
    private tbEmpAdmin adminReceive; //คนรับเครื่องเข้าระบบ

    private String treatment; // วิธีการซ่อม
    private LocalDateTime treatComplete; // วันที่ซ่อมเสร็จ

    private String returnEmp; //คนมารับเครื่อง
    private LocalDateTime returnDate; //วันส่งคืน
    // อก.รท
//    @Column(name = "manager_status")
//    private Boolean managerApproveStatus;
//    @Column(name = "manager_status_detail")
//    private String  managerDetail;
//    @Column(name = "manager_date")
//    private LocalDateTime managerApproveDate;
//    @Column(name = "manager_sig")
//    private String managerSig;

    // หัวหน้า ผปค.
//    @Column(name = "op_lead_status")
//    private Boolean operationLeadApproveStatus;
//    @Column(name = "op_lead_detail")
//    private String operationLeadDetail;
//    @Column(name = "op_lead_date")
//    private LocalDateTime operationLeadApproveDate;
//    @Column(name = "op_lead_sig")
//    private String operationLeadSig;



    @ManyToOne(targetEntity = tbRepairStatus.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id",insertable = true,referencedColumnName="id")
    private tbRepairStatus repairStatus; //สถานะการส่งซ่อม

    @ManyToOne(targetEntity = tbDevice.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id",insertable=true, referencedColumnName = "id")
    private tbDevice device; //เครื่องที่ส่งซ่อม
}