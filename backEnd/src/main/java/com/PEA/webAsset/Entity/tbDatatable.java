package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data @Getter @Setter
@Table(name = "tb_datatable")
@Entity(name = "tb_datatable")
@NoArgsConstructor
@EqualsAndHashCode
public class tbDatatable {
    @Id
    @GeneratedValue(generator = "id",strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "id", sequenceName = "id")
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private LocalDateTime birthdate;
    private String gender;
    private Float salary;
    // private Integer group_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Integer age;

        // //   Join tbCommitment.class------------------------------
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private tbDatatable_group group;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private tbDatatable_address address;
}