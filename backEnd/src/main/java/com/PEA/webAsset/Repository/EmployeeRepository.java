package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbEmployee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<tbEmployee, Long> {

    tbEmployee findByEmpId(String empId);

    Optional<tbEmployee> findEmpByEmpId(String empId);

    @Query(value = "SELECT * from tb_employees e " +
    // "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
                    "WHERE e.cc_id LIKE CONCAT(:ccLong,'%')", nativeQuery = true)
    Page<tbEmployee> findEmployeeByCcId(@Param("ccLong") String ccLong, Pageable pageable);

//    test
    // @Query(value = "SELECT id FROM tb_employees ",nativeQuery = true)
    // Collection<Object[]> findAllUsersWithPagination();

//    Optional<tbEmployee> FindByEmployeeId(String empId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_employees " +
            "SET  emp_rule_id = \"2\" " +
            "WHERE emp_rule_id IS NULL;" ,nativeQuery = true)
    void updateEmpRule();
}