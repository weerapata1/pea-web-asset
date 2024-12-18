package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbEmpRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmpRoleRepository extends JpaRepository<tbEmpRole, Long> {
    tbEmpRole findEmpRuleById(Long id);

}