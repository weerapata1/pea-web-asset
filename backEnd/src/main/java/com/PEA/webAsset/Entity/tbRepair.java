package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Column(name = "repairId" ,nullable = false ,unique = true)
    private Long repairId;

    private LocalDateTime SendDate; //ส่งเรื่องซ่อม
    @NotNull(message = ">> plz chk your damageDetail is Null <<")
    @Size(min = 5 ,max = 100 ,message = ">> plz chk your damageDetail is less 5 or more 100 char <<")
    private String damageDetail;    //อาการที่เสีย

    @NotNull(message = ">> plz chk your empSend is Null <<")
    @ManyToOne(targetEntity = tbEmployee.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "empSend",insertable = true,referencedColumnName = "empId")
    private tbEmployee empSend; // คนนำเครื่องมาส่ง

    private LocalDateTime admitDate; //รับเรื่องซ่อม

    @ManyToOne(targetEntity = tbCause.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "cause",insertable = true,referencedColumnName = "id")
    private tbCause cause; //อาการเบื้องต้น

    @ManyToOne(targetEntity = tbEmpAdmin.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "adminReceive",insertable = true,referencedColumnName = "id")
    private tbEmpAdmin adminReceive; //คนรับเครื่องเข้าระบบ

    @Size(min = 5, max = 100 ,message = ">> plz chk your treatment is less 5 or more 100 char <<")
    private String treatment; // วิธีการซ่อม
    private LocalDateTime treatComplete; // วันที่ซ่อมเสร็จ

    @ManyToOne(targetEntity = tbEmployee.class ,fetch = FetchType.EAGER)
    @JoinColumn(name = "empReturn",insertable = true ,referencedColumnName = "empId")
    private tbEmployee returnEmp; //คนมารับเครื่อง
    private LocalDateTime returnDate; //วันส่งคืน


    @ManyToOne(targetEntity = tbRepairStatus.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id",insertable = true,referencedColumnName="id")
    private tbRepairStatus repairStatus; //สถานะการส่งซ่อม

    @ManyToOne(targetEntity = tbDevice.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id",insertable=true, referencedColumnName = "id")
    private tbDevice device; //เครื่องที่ส่งซ่อม
}