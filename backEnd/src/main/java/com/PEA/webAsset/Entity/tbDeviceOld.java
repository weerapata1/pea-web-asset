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
@Table(name = "tb_device_old")
@Entity(name = "tb_device_old")
@ToString
public class tbDeviceOld {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "old_seq")
    @SequenceGenerator(name = "old_seq", sequenceName = "old_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "\t device_id is null \t")
    private Long id;

    private String devPeaNo;
    //
    // private String tbEmployee;
    //
    private String devDescription;
    // @Column(name ="dev_serialNo", unique = true)
    private String devSerialNo;

    private String devNote;

    // @JsonFormat(pattern="yyyy-MM-dd")
    private String devReceivedDate;

    private Double devReceivedPrice;

    private Double devLeftPrice;

    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean isDeleted;

    // Join tbCommitment.class------------------------------
    // @ManyToOne(targetEntity = tbCommitment.class, fetch = FetchType.EAGER)
    // @JoinColumn(name = "cont_id", insertable = true)
    // private tbCommitment tbCommitment;

    // Join tbCostCenter.class------------------------------
    @ManyToOne(targetEntity = tbCostCenterTest.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_id", insertable = true, referencedColumnName = "cc_id")
    private tbCostCenterTest tbCostCenterTest;

    // Join tbEmployee.class------------------------------
    @ManyToOne(targetEntity = tbEmployee.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", insertable = true)
    private tbEmployee tbEmployee;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime devUpdate;
    //
    // Join tbDeviceType.class------------------------------
    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dt_id", insertable = true, referencedColumnName = "dt_id", nullable = true)
    private tbDeviceType tbDeviceType;

    private String devConcatPriceDate;

    // @ManyToOne(targetEntity = tbDeviceBrand.class, fetch = FetchType.EAGER)
    // @JoinColumn(name = "brand_id", insertable = true,referencedColumnName = "id")
    // private tbDeviceBrand tbDeviceBrand;
    //
    // @ManyToOne (targetEntity = tbRepair.class , fetch = FetchType.EAGER)
    // @JoinColumn(name = "repairId", insertable = true,referencedColumnName =
    // "repairId")
    // private tbRepair tbRepair;

}