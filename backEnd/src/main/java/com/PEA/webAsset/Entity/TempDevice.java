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
@Entity(name = "temp_device")
@Table(name = "temp_device", indexes = {
        @Index(name = "idx_cc_long_code", columnList = "cc_long_code"),
        @Index(name = "idx_emp_id", columnList = "emp_id"),
        @Index(name = "idx_dev_pea_no", columnList = "dev_pea_no")
})
public class TempDevice {
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

    @Column(name = "cc_long_code_string", nullable = true)
    private String ccLongCodeString;

    @Column(name = "update_at")
    private Timestamp updateAt;

    @Column(name = "emp_id_string", nullable = true)
    private String empIdString;

    private Long deviceTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_long_code", referencedColumnName = "cc_long_code", nullable = true)
    private tbCostCenter tbCostCenter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id", nullable = true) // Reference emp_id in tbEmployee
    private tbEmployee tbEmployee;
}