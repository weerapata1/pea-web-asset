package com.PEA.webAsset.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import  jakarta.persistence.*;
//import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "tb_device")
@ToString
@Table(
    name = "tb_device",
    indexes = {
        @Index(name = "idx_cc_long_code", columnList = "cc_long_code"), 
        @Index(name = "idx_emp_id", columnList = "emp_id") 
    }
)
public class tbDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id", unique = true)
    private Long deviceId;

    private String devPeaNo;
   
    private String devDescription;

    private String devSerialNo;

    private String devNote;

    private String devReceivedDate;

    private Double devReceivedPrice;

    private Double devLeftPrice;

    private String devConcatPriceDate;

    @Column(name = "cc_long_code_string", nullable = true)
    private String ccLongCodeString;

    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean isDeleted;

    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
    private LocalDate devUpdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_long_code", referencedColumnName = "cc_long_code", nullable = true)
    private tbCostCenter tbCostCenter;

    @ManyToOne(targetEntity = tbEmployee.class, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id", nullable = true) // Reference emp_id in tbEmployee

    private tbEmployee tbEmployee;

    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "device_type_id", insertable = true, referencedColumnName = "device_type_id", nullable = true)
    private tbDeviceType tbDeviceType;

    @OneToOne(mappedBy = "installedFor")
    private tbEquipment equipment;

    public tbDevice(String devPeaNo ,String devDescription ,String devSerialNo ,String devReceivedDate ,
                    Double devReceivedPrice, Double devLeftPrice, 
                    tbCostCenter tbCostCenter,
                    tbEmployee tbEmployee,
                    LocalDate devUpdate, tbDeviceType device_type_id
                    ){
        this.devPeaNo = devPeaNo;
        this.devDescription = devDescription;
        this.devSerialNo = devSerialNo;
        this.devReceivedDate = devReceivedDate;
        this.devReceivedPrice = devReceivedPrice;
        this.devLeftPrice = devLeftPrice;
        this.tbCostCenter = tbCostCenter;
        this.tbEmployee = tbEmployee;
        this.devUpdate = devUpdate;
        this.tbDeviceType = device_type_id;
    }
}