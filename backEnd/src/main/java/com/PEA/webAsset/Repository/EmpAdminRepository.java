package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbEmpAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmpAdminRepository extends JpaRepository<tbEmpAdmin, Long> {
    tbEmpAdmin findAllByAdminUserName(String adminUserName);

    tbEmpAdmin findAdminPasswordByAdminUserName(String AdminUserName);
}