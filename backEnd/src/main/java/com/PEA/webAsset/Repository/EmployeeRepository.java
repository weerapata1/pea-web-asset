package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<tbEmployee, Long> {
    tbEmployee findByEmpId(String empId);

//    test
    // @Query(value = "SELECT id FROM tb_employees ",nativeQuery = true)
    // Collection<Object[]> findAllUsersWithPagination();



}