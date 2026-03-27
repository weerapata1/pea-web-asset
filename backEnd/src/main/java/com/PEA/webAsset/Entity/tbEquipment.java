package com.PEA.webAsset.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDate;

@Entity(name ="tbEquipment")
@Data @Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tbEquipment")
@ToString
public class tbEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "equipment_seq")
    @SequenceGenerator(name = "equipment_seq",sequenceName = "equipment_seq")
    private Long id;

    @Column(unique = true)
//    @Null(message = "serialNumber Equipment is Null")
    private String serialNumber;

    private String equipmentDescription;

    private LocalDate receiveDate;

    private LocalDate usedDate;

    private Boolean isActivated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "installedFor", insertable = true, referencedColumnName = "device_id")
    private tbDevice installedFor;

    @ManyToOne(targetEntity = tbDeviceType.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "deviceType", insertable = true, referencedColumnName = "device_type_id")
    private tbDeviceType deviceType;

    @ManyToOne(targetEntity = tbEquipmentLocation.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "loc_id",referencedColumnName = "id",insertable = true)
    private tbEquipmentLocation equipmentLocation;


    tbEquipment(String serialNumber, String equipmentDescription, LocalDate receiveDate, LocalDate usedDate, Boolean isActivated){
        this.serialNumber = serialNumber;
        this.equipmentDescription = equipmentDescription;
        this.receiveDate = receiveDate;
        this.usedDate = usedDate;
        this.isActivated = isActivated;
    }

}
