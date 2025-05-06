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

import java.util.Collection;
import java.util.Optional;


@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<tbEmployee, Long> {

    // tbEmployee findByEmpId(String empId);

    Optional<tbEmployee> findEmpByEmpId(String empId);

    @Query(value = "SELECT e.emp_id, e.emp_name, e.emp_dep_full, e.emp_rank, e.cc_long_code from tb_employee e " +
    // "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                    "WHERE e.cc_long_code LIKE CONCAT(:ccLong,'%')", nativeQuery = true)
    Page<Object[]> findEmployeeByCcId(@Param("ccLong") String ccLong, Pageable pageable);

//    test
    // @Query(value = "SELECT id FROM tb_employee ",nativeQuery = true)
    // Collection<Object[]> findAllUsersWithPagination();

//    Optional<tbEmployee> FindByEmployeeId(String empId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_employee " +
            "SET  emp_rule_id = \"2\" " +
            "WHERE emp_rule_id IS NULL;" ,nativeQuery = true)
    void updateEmpRule();

    @Query(value = "SELECT * FROM tb_employees e " +
            "WHERE e.emp_rule_id = :rule_id ",nativeQuery = true)
    Collection<tbEmployee> findEmpRule(@Param("rule_id")Long rule_id);


    @Query(value = "SELECT e.emp_id, e.emp_name, e.emp_dep_full, e.emp_rank, e.cc_long_code " +
    "FROM tb_employee e ", nativeQuery = true)
    Page<Object[]> getEmpAll2(Pageable pageable);

    @Query(value = "SELECT e.emp_id, e.emp_name, e.emp_rank, e.cc_long_code, cc.cc_short_name " +
                    "from tb_employee e LEFT JOIN tb_cost_center cc ON e.cc_long_code = cc.cc_long_code " +
                    "WHERE e.cc_long_code LIKE 'E301023060' OR e.cc_long_code LIKE 'E301023070'", nativeQuery = true)
    Page<Object[]> getInspectorList(Pageable pageable);


}