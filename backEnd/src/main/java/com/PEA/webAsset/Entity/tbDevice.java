package com.PEA.webAsset.Entity;

import lombok.*;

import jakarta.persistence.*;

import java.sql.Timestamp;
//import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tb_device")
@Table(name = "tb_device", indexes = {
        @Index(name = "idx_cc_long_code", columnList = "cc_long_code"),
        @Index(name = "idx_emp_id", columnList = "emp_id"),
        @Index(name = "idx_dev_pea_no", columnList = "dev_pea_no")
})
public class tbDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id", unique = true)
    private Long deviceId;

    @Column(name = "dev_pea_no", unique = true)
    private String devPeaNo;

    private String devDescription;

    private String devSerialNo;

    private String devReceivedDate;

    private Double devReceivedPrice;

    private Double devLeftPrice;

    private String devConcatPriceDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_long_code", referencedColumnName = "cc_long_code", nullable = true)
    private tbCostCenter tbCostCenter;

    @ManyToOne(targetEntity = tbEmployee.class, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id", nullable = true) // Reference emp_id in tbEmployee
    private tbEmployee tbEmployee;

    @Column(name = "cc_long_code_string", nullable = true)
    private String ccLongCodeString;

    @Column(name = "emp_id_string", nullable = true)
    private String empIdString;

    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isDeleted;

    @Column(name = "date_modified")
    private Timestamp dateModified;

    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "device_type_id", insertable = true, referencedColumnName = "device_type_id", nullable = true)
    private tbDeviceType tbDeviceType;

    @OneToOne(mappedBy = "installedFor")
    private tbEquipment equipment;

    public tbDevice(String devPeaNo, String devDescription, String devSerialNo, String devReceivedDate,
            Double devReceivedPrice, Double devLeftPrice,
            tbCostCenter tbCostCenter,
            tbEmployee tbEmployee,
            Timestamp dateModified, tbDeviceType device_type_id) {
        this.devPeaNo = devPeaNo;
        this.devDescription = devDescription;
        this.devSerialNo = devSerialNo;
        this.devReceivedDate = devReceivedDate;
        this.devReceivedPrice = devReceivedPrice;
        this.devLeftPrice = devLeftPrice;
        this.tbCostCenter = tbCostCenter;
        this.tbEmployee = tbEmployee;
        this.dateModified = dateModified;
        this.tbDeviceType = device_type_id;
    }
}