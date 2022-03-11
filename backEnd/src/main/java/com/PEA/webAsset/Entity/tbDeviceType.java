package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Getter @Setter
@Table(name = "tb_device_type")
@Entity(name = "tb_device_type")
@NoArgsConstructor
@EqualsAndHashCode
public class tbDeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "dt_id")
    @SequenceGenerator(name = "dt_id" ,sequenceName = "dt_id")
    @Column(name = "dt_id")
    private Long device_type_id;

    @Column(name = "dt_name")
    private String device_type_name;

    //   Join tbDevice.class------------------------------
    // @OneToMany(mappedBy = "tbDeviceType")
    // private List<tbDevice> tbDevices = new ArrayList<tbDevice>();


}