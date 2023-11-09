package com.PEA.webAsset.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
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
   
    //
    private String devDescription;
    // @Column(name ="dev_serialNo", unique = true)
    private String devSerialNo;

    private String devNote;

    // @JsonFormat(pattern="yyyy-MM-dd")
    private String devReceivedDate;

    private Double devReceivedPrice;

    private Double devLeftPrice;

    // Join tbCommitment.class------------------------------
    // @ManyToOne(targetEntity = tbCommitment.class, fetch = FetchType.EAGER)
    // @JoinColumn(name = "cont_id", insertable = true)
    // private tbCommitment tbCommitment;

    // Join tbCostCenter.class------------------------------
    @ManyToOne(targetEntity = tbCostCenterTest.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_id", insertable = true, referencedColumnName = "cc_id", nullable = true)
    private tbCostCenterTest tbCostCenterTest;
    // private String tbCostCenterTest;

    // Join tbEmployee.class------------------------------
    @ManyToOne(targetEntity = tbEmployee.class, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", insertable = true, nullable = true)
    private tbEmployee tbEmployee; 
    // private String tbEmployee;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime devUpdate;
    //
    // Join tbDeviceType.class------------------------------
    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dt_id", insertable = true, referencedColumnName = "dt_id", nullable = true)
    private tbDeviceType tbDeviceType;

    private String devConcatPriceDate;

    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean isDeleted;

    // @ManyToOne(targetEntity = tbDeviceBrand.class, fetch = FetchType.EAGER)
    // @JoinColumn(name = "brand_id", insertable = true,referencedColumnName = "id")
    // private tbDeviceBrand tbDeviceBrand;
    //
    // @ManyToOne (targetEntity = tbRepair.class , fetch = FetchType.EAGER)
    // @JoinColumn(name = "repairId", insertable = true,referencedColumnName =
    // "repairId")
    // private tbRepair tbRepair;

    public tbDevice(String devPeaNo ,String devDescription ,String devSerialNo ,String devReceivedDate ,
                    Double devReceivedPrice, Double devLeftPrice, 
                    tbCostCenterTest cc_id, 
                    // String cc_id,
                    tbEmployee emp_id,
                    LocalDateTime devUpdate, tbDeviceType dt_id
                    ){
        this.devPeaNo = devPeaNo;
        this.devDescription = devDescription;
        this.devSerialNo = devSerialNo;
        this.devReceivedDate = devReceivedDate;
        this.devReceivedPrice = devReceivedPrice;
        this.devLeftPrice = devLeftPrice;
        this.tbCostCenterTest = cc_id;
        this.tbEmployee = emp_id;
        this.devUpdate = devUpdate;
        this.tbDeviceType = dt_id;
    }

}