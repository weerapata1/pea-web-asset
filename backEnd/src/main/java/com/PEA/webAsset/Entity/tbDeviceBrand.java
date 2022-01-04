package com.PEA.webAsset.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data @Getter @Setter
@EqualsAndHashCode
@Table(name = "device_brand")
@Entity(name = "device_brand")
public class tbDeviceBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "brand_id")
    @SequenceGenerator(name = "brand_id", sequenceName = "brand_id")
    private Long brand_id;

    private String brand_name;


}