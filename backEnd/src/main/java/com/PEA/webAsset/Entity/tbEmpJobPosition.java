package com.PEA.webAsset.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "tbEmpJobPosition")
@Table(name = "tbEmpJobPosition")
@NoArgsConstructor
@EqualsAndHashCode

public class tbEmpJobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "position_seq")
    @SequenceGenerator(name = "position_seq", sequenceName="position_seq")
    @Column(name = "id" ,unique = true , nullable = false)
    private Long id;

    private String pFullName;

    private String pShortName;
}
