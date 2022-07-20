package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;

@Data @Getter @Setter
@Entity(name = "tbEmpAdmin")
@Table(name = "tbEmpAdmin")
@NoArgsConstructor
@EqualsAndHashCode
public class tbEmpAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ad_seq")
    @SequenceGenerator(sequenceName = "ad_seq", name = "ad_seq")
    private Long id;

    private String adName;

    private String adEmp;

}
