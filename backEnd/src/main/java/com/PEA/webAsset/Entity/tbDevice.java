package com.PEA.webAsset.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import  jakarta.persistence.*;
//import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_device")
@Entity(name = "tb_device")
@ToString
public class tbDevice { // use for
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "dev_seq")
    @SequenceGenerator(name = "dev_seq", sequenceName = "dev_seq")
    @Column(name = "id", unique = true)

    private Long id;

    private String devPeaNo;
   
    private String devDescription;

    private String devSerialNo;

    private String devNote;

    private String devReceivedDate;

    private Double devReceivedPrice;

    private Double devLeftPrice;

    private String devConcatPriceDate;

    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean isDeleted;

    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
    private LocalDate devUpdate;

    // Join tbCommitment.class------------------------------
    // @ManyToOne(targetEntity = tbCommitment.class, fetch = FetchType.EAGER)
    // @JoinColumn(name = "cont_id", insertable = true)
    // private tbCommitment tbCommitment;


    // Join tbCostCenter.class------------------------------
    @ManyToOne(targetEntity = tbCostCenter.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_long_code", referencedColumnName = "cc_long_code", nullable = true)
    private tbCostCenter tbCostCenter;
    // private String tbCostCenter;

    // Join tbEmployee.class------------------------------
    @ManyToOne(targetEntity = tbEmployee.class, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id", nullable = true) // Reference emp_id in tbEmployee

    private tbEmployee tbEmployee;
    // private String tbEmployee;

    //
    // Join tbDeviceType.class------------------------------
    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dt_id", insertable = true, referencedColumnName = "dt_id", nullable = true)
    private tbDeviceType tbDeviceType;

//    @OneToMany   // join tbContract
    @OneToOne(mappedBy = "installedFor")
    private tbEquipment equipment;


    public tbDevice(String devPeaNo ,String devDescription ,String devSerialNo ,String devReceivedDate ,
                    Double devReceivedPrice, Double devLeftPrice, 
                    tbCostCenter tbCostCenter,
                    // String cc_id,
                    tbEmployee tbEmployee,
                    LocalDate devUpdate, tbDeviceType dt_id
                    ){
        this.devPeaNo = devPeaNo;
        this.devDescription = devDescription;
        this.devSerialNo = devSerialNo;
        this.devReceivedDate = devReceivedDate;
        this.devReceivedPrice = devReceivedPrice;
        this.devLeftPrice = devLeftPrice;
        this.tbCostCenter = tbCostCenter;
        this.tbEmployee = tbEmployee;
        this.devUpdate = devUpdate;
        this.tbDeviceType = dt_id;
    }
}