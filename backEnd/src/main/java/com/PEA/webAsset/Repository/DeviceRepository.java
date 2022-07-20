package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DeviceRepository extends JpaRepository<tbDevice, Long> {

        @Query(value = "SELECT * from tb_device d " +
                        "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +

                        "WHERE ((d.dev_pea_no like CONCAT('%',:peaNo,'%')) " +
                        "OR (e.emp_id = :empId)  " +
                        "OR (e.emp_name = :empName)) " +
                        "AND (d.cc_id LIKE CONCAT(:ccLong,'%'))", nativeQuery = true)
        Page<tbDevice> findDeviceByPeaNoOrEmpIdOrEmpNameAndCC(@Param("peaNo") String peaNo,
                        @Param("empId") String empId, @Param("empName") String empName, @Param("ccLong") String ccLong,
                        Pageable pageable);

        @Query(value = "SELECT * from tb_device d " +
                        "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
                        "WHERE ((e.emp_id = :empId)  " +
                        "OR (e.emp_name = :empName)) " +
                        "AND (d.cc_id LIKE CONCAT(:ccLong,'%'))", nativeQuery = true)
        Page<tbDevice> findDeviceByEmpIdOrEmpNameAndCC(@Param("empId") String empId, @Param("empName") String empName,
                        @Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT * from tb_device d " +
                        // "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
                        "WHERE (d.cc_id LIKE CONCAT(:ccLong,'%'))", nativeQuery = true)
        Page<tbDevice> findDeviceByCcId(@Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT * from tb_device d " +
                        "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
                        "WHERE (d.dev_description LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR dev_serial_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR e.emp_id LIKE CONCAT('%',:textSearch,'%') " +
                        "OR e.emp_name LIKE CONCAT('%',:textSearch,'%')) " +
                        "AND (d.cc_id LIKE CONCAT(:ccLong,'%'))", nativeQuery = true)
        Page<tbDevice> findDeviceByCcIdAndTextSearch(@Param("ccLong") String ccLong,@Param("textSearch") String textSearch, Pageable pageable);

        @Query(value = "SELECT * from tb_device d " +
                        "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
                        // "WHERE ((e.emp_id = :empId) " +
                        // "OR (e.emp_name = :empName)) " +
                        // "AND (d.cc_id LIKE CONCAT(:ccLong,'%'))"
                        "WHERE (d.cc_id LIKE CONCAT(:ccLong,'%'))", nativeQuery = true)
        Page<tbDevice> findDeviceByEmpIdOrEmpNameAndCC2(
                        // @Param("empId") String empId, @Param("empName") String empName,
                        @Param("ccLong") String ccLong, Pageable pageable);

        // @Query(value = "SELECT * from tb_device d " +
        // // "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
        //                 "WHERE (d.dev_pea_no LIKE '53%')", nativeQuery = true)
        // Page<tbDevice> findAll53nopage();

        @Query(value = "SELECT * from tb_device d " +
        // "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
                        "WHERE (d.dev_pea_no LIKE '53%')", nativeQuery = true)
        Page<tbDevice> findAll53(Pageable pageable);

        @Query(value = "SELECT * from tb_device d " +
        // "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
                        "WHERE d.cc_id LIKE CONCAT(:ccLong,'%') " +
                        "AND d.dev_pea_no LIKE '53%'", nativeQuery = true)
        List<tbDevice> findDeviceForExcel53(@Param("ccLong") String ccLong);

        @Query(value = "SELECT * from tb_device d " +
        // "LEFT JOIN tb_employees e ON d.emp_id = e.emp_id " +
                        "WHERE (d.dev_description LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR dev_serial_no LIKE CONCAT('%',:textSearch,'%')) " +
                        // "OR e.emp_id LIKE CONCAT('%',:textSearch,'%') " +
                        // "OR e.emp_name LIKE CONCAT('%',:textSearch,'%')) " +
                        "AND (d.cc_id LIKE CONCAT(:ccLong,'%')) " +
                        "AND d.dev_left_price = 1 " +
                        "AND (d.dev_pea_no LIKE '53%')", nativeQuery = true)
        List<tbDevice> findDeviceForExcel53search(@Param("ccLong") String ccLong, @Param("textSearch") String textSearch);

        tbDevice findDeviceById(Long id);

}
