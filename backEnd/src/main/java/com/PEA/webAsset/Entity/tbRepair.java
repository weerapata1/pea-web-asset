package com.PEA.webAsset.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import  jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(name = "repair_code")
    private String repairCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Bangkok")
    private LocalDateTime admitDate; //วันที่รับดำเนินการ

    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Bangkok")
    private LocalDateTime lastModifyDate; //วันที่ดำเนินการล่าสุด

    @Column(name = "sendPhoneNum ")
    private String sendPhoneNum ;// เบอร์โทรคนส่ง

    @Column(name = "defectDetail")
    private String defectDetail; //อาการเบื้องต้นที่ user กรอกมา

    @Column(name = "empSend")
    private String empSend; // คนนำเครื่องมาส่ง

    @Column(name = "adminReceive")
    private String adminReceive; // เจ้าหน้าที่รับเครื่อง

    @Column(name = "fixMethod")
    private String fixMethod; // วิธีการดำเนินการซ่อม

    @Column(name = "costOfRepair" ,precision = 6, scale = 2)
    private BigDecimal costOfRepair; // ค่าใช้จายในการดำเนินการ

    @ManyToOne(targetEntity = tbRepairStatus.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id",insertable = true,referencedColumnName="id")
    private tbRepairStatus repairStatus; //สถานะการส่งซ่อม

    @ManyToOne(targetEntity = tbDevice.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id",insertable=true, referencedColumnName = "device_id")
    private tbDevice device; //เครื่องที่ส่งซ่อม

    @ManyToOne(targetEntity = tbActiveStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "active_id" ,insertable = true ,referencedColumnName = "active_id")
    private tbActiveStatus isActive;

//    changeHardware;

//    ----------------------------------------------------------------------
    @Column(name = "adminDefectReview")
    private String adminDefectReview; // การวิเคราะห์จากเจ้าหน้าที่



//    @ManyToOne
//    @Column(name = "treatmentSolution")
//    private String treatmentSolution; // วิธีการซ่อม

    //    @Column(name="treatCompleteDate")
    //    private LocalDate treatCompleteDate; // วันที่ซ่อมเสร็จ
    //
    //    private String damageDetail;    //อาการที่เสียที่ admin พิจารณา
//    private String returnEmp; // หน้างานมารับเครื่องคืน
//    private LocalDate returnDate; // วันที่หน้างานมารับเครื่องคืน
//
//
//
////
////    @ManyToOne(targetEntity = tbEmployee.class,fetch = FetchType.EAGER)
////    @JoinColumn(name = "emp_id", insertable = true, referencedColumnName = "id")
////    private tbEmployee empSend; // คนนำเครื่องมาส่ง
//
//
////----------------------------------------------------------
//
//    public tbRepair(LocalDate SendDate, LocalDate admitDate, LocalDate treatCompleteDate,
//                    String sendPhoneNum, String treatmentSolution, String causesOfDamage, String adminReceive,
//                    String returnEmp, LocalDate returnDate, String damageDetail,
//                    tbRepairStatus repairStatus, tbDevice device
////            ,tbEmployee empSend
//    ){
//        this.sendDate = SendDate;
//        this.admitDate = admitDate;
//        this.treatCompleteDate = treatCompleteDate;
//        this.sendPhoneNum = sendPhoneNum;
//        this.treatmentSolution = treatmentSolution;
//        this.causesOfDamage = causesOfDamage;
//        this.adminReceive = adminReceive;
//        this.returnEmp = returnEmp;
//        this.returnDate = returnDate;
//        this.damageDetail = damageDetail;
//        this.repairStatus = repairStatus;
//        this.device = device;
////        this.empSend = empSend;
//    }

//    @PrePersist
//    public void prePersist() {
//        admitDate = LocalDateTime.now().withNano(0); // ตัด nanoseconds ออก
//    }

}