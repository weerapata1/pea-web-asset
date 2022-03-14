package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;


@Data
@Getter
@Setter
@Table(name = "tb_cost_center")
@Entity(name = "tb_cost_center")
@NoArgsConstructor
@EqualsAndHashCode
public class tbCostCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cc_seq")
    @SequenceGenerator(name = "cc_seq", sequenceName = "cc_seq")
    @Column(name = "id")
    private Long id; //

    private String cc_long_code; //

    private String cc_short_code; //

    private String cc_agency_name;// ชื่อสังกัด

    private String cc_short_name; //

    private String cc_full_name; //

}