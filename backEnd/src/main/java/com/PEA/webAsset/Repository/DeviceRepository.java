package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeviceRepository extends JpaRepository<tbDevice, Long> {

    @Query(value = "SELECT * from tb_device d " +
            "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +

            "WHERE ((d.dev_pea_no like CONCAT('%',:peaNo,'%')) " +
            "OR (e.emp_id = :empId)  " +
            "OR (e.emp_name = :empName)) " +
            "AND (d.cc_id LIKE CONCAT(:ccLong,'%'))"
            , nativeQuery = true)
    Page<tbDevice> findDeviceByPeaNoOrEmpIdOrEmpNameAndCC(@Param("peaNo") String peaNo, @Param("empId") String empId
            , @Param("empName") String empName, @Param("ccLong") String ccLong, Pageable pageable);

    @Query(value = "SELECT * from tb_device d " +
            "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
            "WHERE ((e.emp_id = :empId)  " +
            "OR (e.emp_name = :empName)) " +
            "AND (d.cc_id LIKE CONCAT(:ccLong,'%'))"
            , nativeQuery = true)
    Page<tbDevice> findDeviceByEmpIdOrEmpNameAndCC(@Param("empId") String empId
            , @Param("empName") String empName, @Param("ccLong") String ccLong, Pageable pageable);

            @Query(value = "SELECT * from tb_device d " +
            "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +

        //     "WHERE ((d.dev_pea_no like CONCAT('%',:peaNo,'%')) " +
        //     "OR (e.emp_id = :empId)  " +
        //     "OR (e.emp_name = :empName)) " +
        //     "AND (d.cc_id LIKE CONCAT(:ccLong,'%'))"
                "WHERE (d.cc_id LIKE CONCAT(:ccLong,'%'))"
            , nativeQuery = true)
    Page<tbDevice> findDeviceByPeaNoOrEmpIdOrEmpNameAndCC2(
        //     @Param("peaNo") String peaNo, @Param("empId") String empId, @Param("empName") String empName,
            @Param("ccLong") String ccLong, Pageable pageable);

    @Query(value = "SELECT * from tb_device d " +
            "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
        //     "WHERE ((e.emp_id = :empId)  " +
        //     "OR (e.emp_name = :empName)) " +
        //     "AND (d.cc_id LIKE CONCAT(:ccLong,'%'))"
                "WHERE (d.cc_id LIKE CONCAT(:ccLong,'%'))"
            , nativeQuery = true)
    Page<tbDevice> findDeviceByEmpIdOrEmpNameAndCC2(
        //     @Param("empId") String empId, @Param("empName") String empName,
            @Param("ccLong") String ccLong, Pageable pageable);
}
