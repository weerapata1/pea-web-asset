package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data @Getter @Setter
@Table(name = "tb_datatable_address")
@Entity(name = "tb_datatable_address")
@NoArgsConstructor
@EqualsAndHashCode
public class tbDatatable_address {
    @Id
    @GeneratedValue(generator = "id",strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "id", sequenceName = "id")
    @Column(name = "id", nullable = false)
    private Long id;
    private Long user_id;
    private String line1;
    private String line2;
    private String zipcode;
    private String mobile;
    private String fax;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "address_id")
    // private tbDatatable tbDatatable;
}