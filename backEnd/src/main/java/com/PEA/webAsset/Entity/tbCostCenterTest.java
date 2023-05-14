package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@Table(name = "tb_cost_center_test")
@Entity(name = "tb_cost_center_test")
// @Table(name = "tb_cost_center_test")
// @Entity(name = "tb_cost_center_test")
@EqualsAndHashCode
@NoArgsConstructor
public class tbCostCenterTest {
    @Id
//    @SequenceGenerator(name = "cost_seq",sequenceName = "cost_seq")
//    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "cost_seq")
//    private Long costCenterId;

    @Column(name = "cc_id")
    private String ccLongCode; //

    private String ccShortCode; //

    private String ccAgencyName;// ชื่อสังกัด

    private String ccShortName; //

    private String ccFullName; //

    public tbCostCenterTest(String ccLongCode, String ccShortCode, String ccAgencyName, String ccShortName, String ccFullName){
        this.ccLongCode = ccLongCode;
        this.ccShortCode = ccShortCode;
        this.ccAgencyName = ccAgencyName;
        this.ccShortName = ccShortName;
        this.ccFullName = ccFullName;
    }


}