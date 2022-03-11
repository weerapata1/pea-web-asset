package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<tbEmployee, Long> {
    tbEmployee findEmployeeById(Long id);
    tbEmployee findEmployeeByEmpId(String empId);
}