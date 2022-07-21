package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbEmpRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmpRuleRepository extends JpaRepository<tbEmpRule, Long> {
}