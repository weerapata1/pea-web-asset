package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbCostCenterTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CostCenterRepository extends JpaRepository<tbCostCenterTest, Long> {
    tbCostCenterTest findByCcLongCode(String ccLongCode);
}