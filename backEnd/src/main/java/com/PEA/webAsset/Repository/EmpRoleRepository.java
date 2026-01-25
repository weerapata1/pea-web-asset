package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmpRoleRepository extends JpaRepository<tbRole, Long> {
    tbRole findEmpRuleById(Long id);

}