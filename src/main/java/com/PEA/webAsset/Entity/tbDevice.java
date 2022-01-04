//package com.PEA.webAsset.Entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Data @Setter @Getter
//@NoArgsConstructor
//@EqualsAndHashCode
//@Table(name = "tb_device")
//@Entity(name = "tb_device")
//public class tbDevice {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "device_id")
//    @SequenceGenerator(name = "device_id", sequenceName = "device_id")
//    @Column(name = "device_id", unique = true)
//    @NotNull(message = "\t device_id is null \t")
//    private Long device_id;
//
//    private String dev_peaNo;
//
//    private String dev_serialNo;
//
//    private String dev_note;
//
//    //   Join tbContract.class------------------------------
//    @ManyToOne(targetEntity = tbContract.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "cont_id", insertable = true)
//    private tbContract tbContract;
//
//    //   Join tbCostCenter.class------------------------------
//    @ManyToOne(targetEntity = tbContract.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "cc_id", insertable = true)
//    private tbCostCenter tbCostCenter;
//
//    //   Join tbDeviceType.class------------------------------
//    @ManyToOne(targetEntity = tbContract.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "device_type_id", insertable = true)
//    private tbDeviceType tbDeviceType;
//
//    //   Join tbDeviceType.class------------------------------
//    @ManyToOne(targetEntity = tbEmployee.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "emp_id", insertable = true)
//    private tbEmployee tbEmployee;
//
//}