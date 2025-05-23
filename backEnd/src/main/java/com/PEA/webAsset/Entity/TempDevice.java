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
        @Index(name = "idx_emp_id", columnList = "emp_id")
})
public class TempDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id", unique = true)
    private Long deviceId;

    private String devPeaNo;

    private String devDescription;

    private String devSerialNo;

    private String devReceivedDate;

    private Double devReceivedPrice;

    private Double devLeftPrice;

    private String devConcatPriceDate;

    private String ccLongCode;

    @Column(name = "update_at")
    private Timestamp updateAt;

    private String empId;
}