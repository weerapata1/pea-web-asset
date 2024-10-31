package com.PEA.webAsset.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import  jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Getter @Setter
@Entity(name = "tb_repair")
@Table(name = "tb_repair")
@NoArgsConstructor
@EqualsAndHashCode
public class tbRepair {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "repair_seq")
    @SequenceGenerator(name = "repair_seq", sequenceName = "repair_seq")
    @Column(name = "id" ,unique = true ,nullable = false)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
    private LocalDate sendDate; //ส่งเรื่องซ่อม

    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
    private LocalDate admitDate; //วันที่รับดำเนินการ

    @Column(name = "sendPhoneNum ")
    private String sendPhoneNum ;

    @Column(name = "treatment")
    private String treatment; // วิธีการซ่อม

    private String causesOfDamage; //อาการเบื้องต้นที่ user กรอกมา

    @Column(name="treatCompleteDate")
    private LocalDate treatCompleteDate; // วันที่ซ่อมเสร็จ

//    private String empSend; // คนนำเครื่องมาส่ง
    private String adminReceive; // เจ้าหน้าที่รับเครื่อง
    private String returnEmp; // หน้างานมารับเครื่องคืน
    private String returnDate; // วันที่หน้างานมารับเครื่องคืน

    private String damageDetail;    //อาการที่เสียที่ admin พิจารณา

    @ManyToOne(targetEntity = tbRepairStatus.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id",insertable = true,referencedColumnName="id")
    private tbRepairStatus repairStatus; //สถานะการส่งซ่อม

    @ManyToOne(targetEntity = tbDevice.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id",insertable=true, referencedColumnName = "id")
    private tbDevice device; //เครื่องที่ส่งซ่อม
//
//    @ManyToOne(targetEntity = tbEmployee.class,fetch = FetchType.EAGER)
//    @JoinColumn(name = "emp_id", insertable = true, referencedColumnName = "id")
//    private tbEmployee empSend; // คนนำเครื่องมาส่ง


//----------------------------------------------------------

    public tbRepair(LocalDate SendDate, LocalDate admitDate, LocalDate treatCompleteDate,
                    String sendPhoneNum, String treatment, String causesOfDamage, String adminReceive,
                    String returnEmp, String returnDate, String damageDetail,
                    tbRepairStatus repairStatus, tbDevice device
//            ,tbEmployee empSend
    ){
        this.sendDate = SendDate;
        this.admitDate = admitDate;
        this.treatCompleteDate = treatCompleteDate;
        this.sendPhoneNum = sendPhoneNum;
        this.treatment = treatment;
        this.causesOfDamage = causesOfDamage;
        this.adminReceive = adminReceive;
        this.returnEmp = returnEmp;
        this.returnDate = returnDate;
        this.damageDetail = damageDetail;
        this.repairStatus = repairStatus;
        this.device = device;
//        this.empSend = empSend;
    }

}