package com.PEA.webAsset.Entity;

import com.PEA.webAsset.Share.Generator.CustomIdGenerator;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import  jakarta.persistence.*;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Data
@Getter @Setter
@Entity(name = "tb_repair")
@Table(name = "tb_repair")
@NoArgsConstructor
@EqualsAndHashCode
public class tbRepair {
//    @Id
//    @GenericGenerator( name = "user_id_seq", type = CustomIdGenerator.class, parameters = {
//            @org.hibernate.annotations.Parameter( name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "rp--" ),
//            @org.hibernate.annotations.Parameter( name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d" ) } )
//    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "user_id_seq" )
//
    @Id
    @SequenceGenerator(name = "repairId",sequenceName = "repairId")
    @GeneratedValue(strategy = GenerationType.AUTO ,generator = "repairId")
    private Long repairId;

    @Column(name = "repair_no_id")
    private String repairNoId;

    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
    private LocalDate sendDate; //ส่งเรื่องซ่อม

    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
    private LocalDate admitDate; //วันที่รับดำเนินการ

    @Column(name = "sendPhoneNum ")
    private String sendPhoneNum ;

    @Column(name = "treatment")
    private String treatmentSolution; // วิธีการซ่อม
    private String causesOfDamage; //อาการเบื้องต้นที่ user กรอกมา
    @Column(name="treatCompleteDate")
    private LocalDate treatCompleteDate; // วันที่ซ่อมเสร็จ

    private String damageDetail;    //อาการที่เสียที่ admin พิจารณา
    private String empSend; // คนนำเครื่องมาส่ง
    private String adminReceive; // เจ้าหน้าที่รับเครื่อง
    private String returnEmp; // หน้างานมารับเครื่องคืน
    private LocalDate returnDate; // วันที่หน้างานมารับเครื่องคืน



    @ManyToOne(targetEntity = tbRepairStatus.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id",insertable = true,referencedColumnName="id")
    private tbRepairStatus repairStatus; //สถานะการส่งซ่อม

    @ManyToOne(targetEntity = tbDevice.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id",insertable=true, referencedColumnName = "device_id")
    private tbDevice device; //เครื่องที่ส่งซ่อม
//
//    @ManyToOne(targetEntity = tbEmployee.class,fetch = FetchType.EAGER)
//    @JoinColumn(name = "emp_id", insertable = true, referencedColumnName = "id")
//    private tbEmployee empSend; // คนนำเครื่องมาส่ง


//----------------------------------------------------------

    public tbRepair(LocalDate SendDate, LocalDate admitDate, LocalDate treatCompleteDate,
                    String sendPhoneNum, String treatmentSolution, String causesOfDamage, String adminReceive,
                    String returnEmp, LocalDate returnDate, String damageDetail,
                    tbRepairStatus repairStatus, tbDevice device
//            ,tbEmployee empSend
    ){
        this.sendDate = SendDate;
        this.admitDate = admitDate;
        this.treatCompleteDate = treatCompleteDate;
        this.sendPhoneNum = sendPhoneNum;
        this.treatmentSolution = treatmentSolution;
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