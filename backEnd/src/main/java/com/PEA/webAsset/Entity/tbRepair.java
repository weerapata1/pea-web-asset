package com.PEA.webAsset.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name = "repair_Id")
    private Long repair_Id;

    private LocalDateTime recivedIn; //รับเรื่องซ่อม

    private String damageDetail;    //อาการที่เสีย

    private LocalDateTime returnDate; //วันส่งคืน

    // อก.รท
    @Column(name = "manager_status")
    private Boolean managerApproveStatus;
    @Column(name = "manager_status_detail")
    private String  managerDetail;
    @Column(name = "manager_date")
    private LocalDateTime managerApproveDate;
    @Column(name = "manager_sig")
    private String managerSig;

    // หัวหน้า ผปค.
    @Column(name = "op_lead_status")
    private Boolean operationLeadApproveStatus;
    @Column(name = "op_lead_detail")
    private String operationLeadDetail;
    @Column(name = "op_lead_date")
    private LocalDateTime operationLeadApproveDate;
    @Column(name = "op_lead_sig")
    private String operationLeadSig;

    // พนักงงานคนรับเรื่อง
    @ManyToOne(targetEntity = tbEmployee.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "empId",insertable = true)
    @JsonBackReference
    private tbEmployee employee;

    @ManyToOne(targetEntity = tbRepairStatus.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id",insertable = true,referencedColumnName="id")
    private tbRepairStatus tbRepairStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id",referencedColumnName = "id")
    private tbDevice device;
}