package com.PEA.webAsset.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "tbEmpRule")
@Table(name = "tbEmpRule")
@NoArgsConstructor
@EqualsAndHashCode
public class tbEmpRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rule_seq")
    @SequenceGenerator(name = "rule_seq", sequenceName = "rule_seq")
    @Column(name = "id" ,unique = true , nullable = false)
    private Long id;

    private String ruleName;
}
