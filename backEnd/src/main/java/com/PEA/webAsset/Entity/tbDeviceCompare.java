package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;

@Data @Getter @Setter
@Table(name = "tb_device_compare")
@Entity(name = "tb_device_compare")
@NoArgsConstructor
@EqualsAndHashCode
public class tbDeviceCompare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "compare_srq")
    @SequenceGenerator(name = "compare_srq" ,sequenceName = "compare_srq")
    @Column(name = "compare_id")
    private Long compareId;

    private String devDescription;

    private String devReceivedDate;

    private Double devReceivedPrice;

    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "device_type", insertable = true, referencedColumnName = "dt_id")
    private tbDeviceType tbDeviceType;

    //   Join tbDevice.class------------------------------
    // @OneToMany(mappedBy = "tbDeviceType")
    // private List<tbDevice> tbDevices = new ArrayList<tbDevice>();

}