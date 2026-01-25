package com.PEA.webAsset.Service;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmpRoleId implements Serializable {
//    @Embeddable
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "role_id")
    private Long roleId;

    // ต้องมี
    public EmpRoleId() {}

    // แนะนำ
    public EmpRoleId(Long employeeId, Long roleId) {
        this.employeeId = employeeId;
        this.roleId = roleId;
    }

    // จำเป็นมาก
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmpRoleId)) return false;
        EmpRoleId that = (EmpRoleId) o;
        return Objects.equals(employeeId, that.employeeId)
                && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, roleId);
    }
}
