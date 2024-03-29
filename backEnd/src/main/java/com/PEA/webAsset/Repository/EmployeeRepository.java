package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbEmployee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;


@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<tbEmployee, String>, CrudRepository<tbEmployee ,String> {
    tbEmployee findByEmpId(String empId);


    @Query(value = "SELECT * from tb_employees e " +
    // "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
                    "WHERE e.cc_id LIKE CONCAT(:ccLong,'%')", nativeQuery = true)
    Page<tbEmployee> findEmployeeByCcId(@Param("ccLong") String ccLong, Pageable pageable);

//    test
    // @Query(value = "SELECT id FROM tb_employees ",nativeQuery = true)
    // Collection<Object[]> findAllUsersWithPagination();

//    Optional<tbEmployee> FindByEmployeeId(String empId);
}