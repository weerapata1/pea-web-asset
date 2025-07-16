package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbCostCenter;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CostCenterRepository extends JpaRepository<tbCostCenter, Long> {
    
    Optional<tbCostCenter> findByCcLongCode(String ccLongCode);

    tbCostCenter findCcIdByCcFullName(String ccFullName);

    @Query(value = "SELECT c.cc_long_code, c.cc_short_name, c.cc_full_name from tb_cost_center c " +
            // "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
            "WHERE c.cc_long_code LIKE '%0' " + "OR c.cc_full_name LIKE '%สถานี%'", nativeQuery = true)
    Page<Object[]> onlyUse(Pageable pageable);

}