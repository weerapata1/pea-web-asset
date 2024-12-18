package com.PEA.webAsset.Entity;

import lombok.*;

import  jakarta.persistence.*;

@Data
@Getter @Setter
@Entity(name = "tbEmpRole")
@Table(name = "tbEmpRole")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class tbEmpRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rule_seq")
    @SequenceGenerator(name = "rule_seq", sequenceName = "rule_seq")
    @Column(name = "id" ,unique = true , nullable = false)
    private Long id;

    private String roleName;


    public tbEmpRole(String roleName){
        this.roleName = roleName;
    }
}
