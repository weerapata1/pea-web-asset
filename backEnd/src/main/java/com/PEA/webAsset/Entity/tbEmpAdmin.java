package com.PEA.webAsset.Entity;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data @Getter @Setter
@Entity(name = "tbEmpAdmin")
@Table(name = "tbEmpAdmin")
@NoArgsConstructor
@EqualsAndHashCode
public class tbEmpAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ad_seq")
    @SequenceGenerator(sequenceName = "ad_seq", name = "ad_seq")
//    @NotNull(message = "id in tbEmpAdmin is null")
    private Long id;

//    @NotNull(message = ">> plz chk your adUserName is Null <<")
//    @Size(min = 6,max = 9 ,message = ">> plz chk your adUserName is less 5 or more 9 char <<")
//    @UniqueElements(message = ">> plz chk your adUserName is duplicate <<")
    private String adName;

    private String adEmp;

    private String empRole;  //ตำแหน่ง

//    @NotNull(message = ">> plz chk your adUserName is Null <<")
//    @Size(min = 6,max = 9 ,message = ">> plz chk your adUserName is less 5 or more 9 char <<")
//    @UniqueElements(message = ">> plz chk your adUserName is duplicate <<")
    @Column(name = "adUserName")
    private String adminUserName;

//    @NotNull(message = ">> plz chk your adPassWord is Null <<")
    @Column(name = "adPassWord")
    private  String adminPassword;

    private String adminSignature;

    public tbEmpAdmin(String adName, String adEmp, String empRole, String adminUserName, String adminPassword){
        this.adName = adName;
        this.adEmp = adEmp;
        this.empRole = empRole;
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
    }

}
