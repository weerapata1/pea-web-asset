package com.PEA.webAsset.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_employees")
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "tb_employees")
@ToString
public class tbEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq",sequenceName = "emp_seq")
    @Column(name = "id", unique = true,nullable = false)
    @NotNull
    private Long id;

    private String empId;

    private String empName;

}
