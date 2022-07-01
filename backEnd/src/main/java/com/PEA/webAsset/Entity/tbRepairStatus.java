package com.PEA.webAsset.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "tb_repair_status")
@Table(name = "tb_repair_status")
@NoArgsConstructor
@EqualsAndHashCode
public class tbRepairStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rps_id")
    @SequenceGenerator(name = "rps_id", sequenceName = "rps_id")
    @Column(name = "id", unique = true,nullable = false)
    private Integer id;

    private String StatusName;
}