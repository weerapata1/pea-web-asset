package com.PEA.webAsset.Entity;

import lombok.*;

import  jakarta.persistence.*;

import java.util.Set;

@Data
@Getter
@Setter
@Table(name = "tb_cost_center")
@Entity(name = "tb_cost_center")
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class tbCostCenter {
    
    // @SequenceGenerator(name = "cost_seq",sequenceName = "cost_seq")
    // @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "cost_seq")
    // @Column(name = "cost_center_id")
    // private  Long costCenterId;

//    @SequenceGenerator(name = "cost_seq",sequenceName = "cost_seq")
//    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "cost_seq")
    // @Column(name = "cc_id")
    // private  Long cc_id;
    @Id
    @Column(name = "cc_long_code", unique = true, nullable = false)
    private String ccLongCode; //

    private String ccBusA;//

    private String ccAgencyName;// ชื่อสังกัด

    private String ccShortName; //

    private String ccFullName; //

    private String ccProfitCode; //

    @OneToMany(mappedBy = "tbCostCenter",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<tbDevice> tbDeviceSet;

    // ccBusA Eq. cost center business area
    public tbCostCenter(String ccLongCode, String ccBusA, String ccAgencyName, String ccShortName, String ccFullName, String ccProfitCode){
        this.ccLongCode = ccLongCode;
        this.ccBusA = ccBusA;
        this.ccAgencyName = ccAgencyName;
        this.ccShortName = ccShortName;
        this.ccFullName = ccFullName;
        this.ccProfitCode = ccProfitCode;
    }
}