package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;
// // import java.time.LocalDateTime;

@Data @Getter @Setter
@Table(name = "tb_datatable_format")
@Entity(name = "tb_datatable_format")
@NoArgsConstructor
@EqualsAndHashCode
public class tbDatatable_format {
    @Id
    @GeneratedValue(generator = "id",strategy = GenerationType.IDENTITY)
//     // @SequenceGenerator(name = "id", sequenceName = "id")
//     // @Column(name = "id", nullable = false)
    private Long id;
    private Integer total;
    private Integer per_page;
    private Integer current_page;
    private Integer last_page;
    private Integer next_page_url;
    private Integer prev_page_url;
    private Integer from1;
    private Integer to1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "data", referencedColumnName = "id")
    private tbDatatable data;
    
}
