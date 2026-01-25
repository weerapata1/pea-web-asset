package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbActiveStatus;
import com.PEA.webAsset.Entity.tbRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface RepairRepository extends JpaRepository<tbRepair ,Long> {

    @Query(value = "Select Count(repair_id) from tb_repair ",nativeQuery = true)
    Long findSequentOfRepair();

//    @Query(value = "SELECT * FROM tbRepair t" +
//            "WHERE r.emp_id = :empId",nativeQuery = true)
//    Collection<tbRepair> findByEmpId(@Param("empId")String empId);
//    tbRepair findRepairByRepairNoId(String repairId);

//
    @Query(value = "SELECT * FROM tb_repair r " +
            "JOIN tb_device d ON r.device_id = d.device_id " +
            "WHERE d.cc_id = :location" ,nativeQuery = true)
    tbRepair findDeviceRepairByLocation(@Param("location")String location);
//
    @Query(value = "SELECT * FROM tb_repair r " +
            "WHERE r.status_id = :status" ,nativeQuery = true)
    Collection<tbRepair> findDeviceRepairByRepairStatusId(@Param("status")int status);

    Collection<tbRepair> findRepairByRepairCodeContainingOrDevice_DevPeaNoContaining(String repairCode, String devPeaNo);

//
//    @Query(value = "SELECT * FROM tb_repair r " +
//            "JOIN tb_device d ON r.device_id = d.id " +
//            "WHERE d.cc_id = :location AND r.status_id = :status" ,nativeQuery = true)
//    Collection<tbRepair> findDeviceRepairByLocationAndState(@Param("location")String location ,@Param("status")int status);
//
//    Collection<tbRepair> findByDeviceId(Long device);
//
//    @Query(value = "SELECT * FROM tb_repair r WHERE r.repair_id = :repairId",nativeQuery = true)
//    tbRepair findRepairByRepairId(String repairId);
//
    @Query(value = "SELECT r.* FROM tb_repair r " +
            "LEFT JOIN tb_device d ON r.device_id = d.device_id " +
            "LEFT JOIN tb_active_status a ON r.active_id = a.active_id " +
            "WHERE (r.repair_code LIKE %:textSearch% OR d.dev_pea_no LIKE %:textSearch%) " +
            "AND r.active_id = 1 "
            ,nativeQuery = true)
    Collection<tbRepair> findByRepairCodeOrPeaNoAndActStatus(String textSearch);

//    @Query(value = "SELECT r.* FROM tb_repair r " +
//            "LEFT JOIN tb_device d ON r.device_id = d.device_id " +
//            "LEFT JOIN tb_active_status a ON r.active_id = a.active_id " +
//            "WHERE (r.repair_code LIKE %:textSearch% OR d.dev_pea_no LIKE %:textSearch%) " +
//            "AND r.active_id = 1 "
//            ,nativeQuery = true)
    tbRepair findByRepairCode(String textSearch);

    @Query(value = "SELECT r.* FROM tb_repair r " +
            "WHERE r.active_id = 1"
            ,nativeQuery = true)
    Collection<tbRepair> findAllByIsActive(Long isActive);

    @Query(value = "SELECT * FROM tb_repair as r " +
                   "WHERE r.status_id = :status",nativeQuery = true)
    Collection<tbRepair> findByRepairStatus(String status);


}
