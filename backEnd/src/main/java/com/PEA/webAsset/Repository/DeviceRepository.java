package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface DeviceRepository extends JpaRepository<tbDevice, Long> {

    @Query(value = "SELECT * from tb_device t WHERE t.cc_id = :cc_id", nativeQuery = true)
    Collection<tbDevice> findAllByCCId(
            @Param("cc_id") String cc_id);

    @Query(value = "SELECT * from tb_device d " +
            "INNER JOIN tb_cost_center c ON d.cc_id = c.id " +
            "INNER JOIN tb_employees e ON d.emp_id = e.emp_id " +
            "WHERE (c.cc_long_code like CONCAT(:costCenter,'%')) " +
            "OR (e.emp_id = :empId) " +
            "OR (e.emp_name = :empName)"
            , nativeQuery = true)
    Page<tbDevice> findDeviceByCcMoreOneOrEmpIdOrEmpName(@Param("costCenter") String costCenter, @Param("empId") String empId
            , @Param("empName") String empName, Pageable pageable);

    @Query(value = "SELECT * from tb_device d " +
            "INNER JOIN tb_cost_center c ON d.cc_id = c.id " +
            "INNER JOIN tb_employees e ON d.emp_id = e.emp_id " +
            "WHERE (e.emp_id = :empId)  " +
            "OR (e.emp_name = :empName)"
            , nativeQuery = true)
    Page<tbDevice> findDeviceByEmpIdOrEmpName(@Param("empId") String empId
            , @Param("empName") String empName, Pageable pageable);
}
