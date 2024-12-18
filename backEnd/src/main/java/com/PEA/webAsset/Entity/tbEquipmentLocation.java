package com.PEA.webAsset.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tbEquipmentLocation")
@Data @Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
public class tbEquipmentLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "equipLoc_Seq")
    @SequenceGenerator(name = "equipLoc_Seq",sequenceName = "equipLoc_Seq",allocationSize = 20)
    @Column(name = "id")
    private Long id;

    private String LocName;

    @OneToMany(mappedBy = "id")
    private Set<tbEquipment> equipments;

    public tbEquipmentLocation(String locName){
        this.LocName = locName;
    }
}
