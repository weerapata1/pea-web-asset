package com.PEA.webAsset.Entity;

import lombok.*;

import  jakarta.persistence.*;

@Data @Getter @Setter
@Table(name = "tb_device_compare")
@Entity(name = "tb_device_compare")
@NoArgsConstructor
@EqualsAndHashCode
public class tbDeviceCompare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator = "compare_srq")
    @SequenceGenerator(name = "compare_srq" ,sequenceName = "compare_srq")
    @Column(name = "tb_device_compare_id")
    private Long tbDeviceCompareId;

    private String devDescription;

    private String devReceivedDate;

    private Double devReceivedPrice;

    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "tb_device_type_id", insertable = true, referencedColumnName = "tb_device_type_id")
    private tbDeviceType tbDeviceType;

    private String devConcatPriceDate;

    //   Join tbDevice.class------------------------------
    // @OneToMany(mappedBy = "tbDeviceType")
    // private List<tbDevice> tbDevices = new ArrayList<tbDevice>();

}