package com.PEA.webAsset.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @Setter @Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_device")
@Entity(name = "tb_device")
@ToString
public class tbDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "dev_seq")
    @SequenceGenerator(name = "dev_seq", sequenceName = "dev_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "\t device_id is null \t")
    private Long id;


    private String devPeaNo;

//    @Column(name ="dev_serialNo", unique = true)
    private String devSerialNo;


    private String devNote;

    private String devDescription;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime devUpdate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate devReceived;

//       Join tbCommitment.class------------------------------
    @ManyToOne(targetEntity = tbCommitment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cont_id", insertable = true)
    private tbCommitment tbCommitment;

    //       Join tbCostCenter.class------------------------------
    @ManyToOne(targetEntity = tbCostCenter.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_id", insertable = true)
    private tbCostCenter tbCostCenter;
//
    //   Join tbDeviceType.class------------------------------
    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "device_type_id", insertable = true)
    private tbDeviceType tbDeviceType;
//
    //   Join tbEmployee.class------------------------------
    @ManyToOne(targetEntity = tbEmployee.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", insertable = true)
    private tbEmployee tbEmployee;
//
//    @ManyToOne(targetEntity = tbDeviceBrand.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "brand_id", insertable = true,referencedColumnName = "id")
//    private tbDeviceBrand tbDeviceBrand;
//
    @ManyToOne (targetEntity = tbRepair.class , fetch = FetchType.EAGER)
    @JoinColumn(name = "repairId", insertable = true,referencedColumnName = "repairId")
    private tbRepair tbRepair;

}