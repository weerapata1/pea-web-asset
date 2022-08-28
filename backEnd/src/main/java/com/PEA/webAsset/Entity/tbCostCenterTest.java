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
@NoArgsConstructor
@EqualsAndHashCode
public class tbCostCenterTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cc_seq")
    @SequenceGenerator(name = "cc_seq", sequenceName = "cc_seq")
    @Column(name = "cc_id")
    private String ccLongCode; //

    private String ccShortCode; //

    private String ccAgencyName;// ชื่อสังกัด

    private String ccShortName; //

    private String ccFullName; //

}