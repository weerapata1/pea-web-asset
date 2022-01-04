package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Getter @Setter
@Table(name = "tb_cost_center")
@Entity(name = "tb_cost_center")
@NoArgsConstructor
@EqualsAndHashCode
public class tbCostCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "host_id")
    @SequenceGenerator(name = "cc_id", sequenceName = "host_id")
    @Column(name = "cc_id")
    private Long cc_id; //

    private String cc_long_id; //

    private String cc_short_id; //

    private String cc_short_name; //

    private String cc_full_name; //

    private String cc_mother_name; // ชื่อสังกัด

    //   Join tbDevice.class------------------------------
    @OneToMany(mappedBy = "tbCostCenter")
    private List<tbDevice> tbDevices = new ArrayList<tbDevice>();

}