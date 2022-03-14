package com.PEA.webAsset.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data @Getter @Setter
@EqualsAndHashCode
@Table(name = "tb_device_brand")
@Entity(name = "tb_device_brand")
public class tbDeviceBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
    @Column(name = "id")
    private Long id;

    private String brand_name;

}