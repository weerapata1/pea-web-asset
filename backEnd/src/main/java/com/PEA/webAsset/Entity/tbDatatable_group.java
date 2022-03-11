package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data @Getter @Setter
@Table(name = "tb_datatable_group")
@Entity(name = "tb_datatable_group")
@NoArgsConstructor
@EqualsAndHashCode
public class tbDatatable_group {
    @Id
    @GeneratedValue(generator = "id",strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "id", sequenceName = "id")
    @Column(name = "id", nullable = false)

    private Long id;
    private String name;
    private String description;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "group_id")
    // private tbDatatable tbDatatable;

}