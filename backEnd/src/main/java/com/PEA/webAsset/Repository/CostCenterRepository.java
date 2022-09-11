package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbCostCenterTest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CostCenterRepository extends JpaRepository<tbCostCenterTest, Long> {
    tbCostCenterTest findByCcLongCode(String ccLongCode);

    tbCostCenterTest findCcIdByCcFullName(String ccFullName);

    @Query(value = "SELECT * from tb_cost_center_test c " +
    // "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
                    "WHERE c.cc_id LIKE '%0' " +
                    "OR c.cc_full_name LIKE '%สถานี%'", nativeQuery = true)
    Page<tbCostCenterTest> onlyUse(Pageable pageable);
   
}