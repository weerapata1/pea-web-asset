package com.PEA.webAsset.Entity;

import lombok.*;

import  jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter @Setter
@Entity(name = "tbRole")
@Table(name = "tbRole")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class tbRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rule_seq")
    @SequenceGenerator(name = "rule_seq", sequenceName = "rule_seq")
    @Column(name = "id" ,unique = true , nullable = false)
    private Long id;

    private String roleCode;
    private String roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<tbEmpRole> empRoles;


    public tbRole(String roleName){
        this.roleName = roleName;
    }
}
