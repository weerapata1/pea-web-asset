package com.PEA.webAsset.Entity;

import com.PEA.webAsset.Service.EmpRoleId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "tbEmpRole")
@EqualsAndHashCode
@NoArgsConstructor
@Data
public class tbEmpRole {
    @EmbeddedId
    private EmpRoleId id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private tbEmployee employee;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private tbRole role;

    private LocalDateTime assignedAt;
}
