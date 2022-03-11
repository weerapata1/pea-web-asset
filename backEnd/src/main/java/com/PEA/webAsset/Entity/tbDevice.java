package com.PEA.webAsset.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data @Setter @Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_device")
@Entity(name = "tb_device")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class tbDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "\t device_id is null \t")
    private Long id;

    private String dev_peaNo;

    private String dev_serialNo;

    private String dev_note;

    private String dev_location;

    //   Join tbCommitment.class------------------------------
    @ManyToOne(targetEntity = tbCommitment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cont_id", insertable = true)
    private tbCommitment tbCommitment;

//       Join tbCostCenter.class------------------------------
    @ManyToOne(targetEntity = tbCostCenter.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_id", insertable = true)
    private tbCostCenter tbCostCenter;

    //   Join tbDeviceType.class------------------------------
    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "device_type_id", insertable = true)
    private tbDeviceType tbDeviceType;

    //   Join tbEmployee.class------------------------------
    @ManyToOne(targetEntity = tbEmployee.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", insertable = true)
    private tbEmployee tbEmployee;

    @ManyToOne(targetEntity = tbDeviceBrand.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", insertable = true)
    private tbDeviceBrand tbDevice;

    //   Join tbLocation.class------------------------------
    @ManyToOne(targetEntity = tbLocation.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "loc_id",insertable = true)
    private  tbLocation tbLocation;

    @OneToOne(mappedBy = "device")
    private tbRepair tbRepair;


}