package com.PEA.webAsset.Entity;

import lombok.*;

import  jakarta.persistence.*;

@Data
@Getter
@Setter
@Table(name = "tb_cost_center")
@Entity(name = "tb_cost_center")
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class tbCostCenter {
    @Id
    @SequenceGenerator(name = "cost_seq",sequenceName = "cost_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "cost_seq")
    @Column(name = "tb_cost_center_id")
    private  Long tbCostCenterId;

    @Column(name = "cc_long_code", unique = true, nullable = false)
    private String ccLongCode; //

    private String ccBusA; //

    private String ccAgencyName;// ชื่อสังกัด

    private String ccShortName; //

    private String ccFullName; //

    public tbCostCenter(String ccLongCode, String ccBusA, String ccAgencyName, String ccShortName, String ccFullName){
        this.ccLongCode = ccLongCode;
        this.ccBusA = ccBusA;
        this.ccAgencyName = ccAgencyName;
        this.ccShortName = ccShortName;
        this.ccFullName = ccFullName;
    }
}