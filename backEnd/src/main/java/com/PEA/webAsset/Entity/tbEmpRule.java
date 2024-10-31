package com.PEA.webAsset.Entity;

import lombok.*;

import  jakarta.persistence.*;

@Data
@Getter @Setter
@Entity(name = "tbEmpRule")
@Table(name = "tbEmpRule")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class tbEmpRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rule_seq")
    @SequenceGenerator(name = "rule_seq", sequenceName = "rule_seq")
    @Column(name = "id" ,unique = true , nullable = false)
    private Long id;

    private String ruleName;


    public tbEmpRule(String ruleName){
        this.ruleName = ruleName;
    }
}
