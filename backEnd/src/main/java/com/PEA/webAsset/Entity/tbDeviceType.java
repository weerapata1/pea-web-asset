package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;

@Data @Getter @Setter
@Table(name = "tb_device_type")
@Entity(name = "tb_device_type")
@NoArgsConstructor
@EqualsAndHashCode
public class tbDeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "dt_srq")
    @SequenceGenerator(name = "dt_srq" ,sequenceName = "dt_srq")
    @Column(name = "dt_id")
    private Long dtId;

    @Column(name = "dt_name")
    private String deviceTypeName;

    //   Join tbDevice.class------------------------------
    // @OneToMany(mappedBy = "tbDeviceType")
    // private List<tbDevice> tbDevices = new ArrayList<tbDevice>();


}