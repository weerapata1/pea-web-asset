package com.PEA.webAsset.Entity;

import javax.persistence.*;
import lombok.*;

@Data @Getter @Setter
@Table(name = "tb_data123")
@Entity(name = "tb_data123")
@NoArgsConstructor
@EqualsAndHashCode
public class tbData {
    @Id
    @GeneratedValue(generator = "data_id",strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "data_id", sequenceName = "data_id")
    @Column(name = "data_id", nullable = false)
    private Integer data_id;

        private String name;
        private Float calories;
        private Float fat;
        private Float carbs;
        private Float protein;
        private String iron;
}
