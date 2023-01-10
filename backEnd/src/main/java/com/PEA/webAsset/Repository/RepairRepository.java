package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface RepairRepository extends JpaRepository<tbRepair ,Long> {
    @Query(value = "SELECT * FROM tbRepair t" +
            "WHERE r.emp_id = :empId",nativeQuery = true)
    Collection<tbRepair> findByEmpId(@Param("empId")String empId);

    @Query(value = "SELECT * FROM tb_repair r " +
            "JOIN tb_device d ON r.device_id = d.id " +
            "WHERE d.cc_id = :location" ,nativeQuery = true)
    Collection<tbRepair> findDeviceRepairByLocation(@Param("location")String location);

    @Query(value = "SELECT * FROM tb_repair r " +
            "WHERE r.status_id = :status" ,nativeQuery = true)
    Collection<tbRepair> findDeviceRepairByStatusId(@Param("status")int status);

    @Query(value = "SELECT * FROM tb_repair r " +
            "JOIN tb_device d ON r.device_id = d.id " +
            "WHERE d.cc_id = :location AND r.status_id = :status" ,nativeQuery = true)
    Collection<tbRepair> findDeviceRepairByLocationAndState(@Param("location")String location ,@Param("status")int status);

    Collection<tbRepair> findByDeviceId(Long device);




}
