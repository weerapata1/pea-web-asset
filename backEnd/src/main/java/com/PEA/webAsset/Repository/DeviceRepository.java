package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDevice;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeviceRepository extends JpaRepository<tbDevice, Long>, CustomDeviceRepository {

        Collection<tbDevice> findDeviceByDevPeaNoContaining(String devPeaNo);

        public interface CustomDeviceRepository {
                void bulkInsertDevices(List<Object[]> devices);
        }

        @Query(value = "select * from tb_device d " +
                "where " +
                "d.dev_pea_no LIKE \"53%\"  "
                + "AND (d.dev_pea_no like CONCAT('%',:textSearch,'%') "
//                + "OR (d.)"
                +"OR d.dev_serial_no like CONCAT('%',:textSearch,'%')) "
                , nativeQuery = true)
        Collection<tbDevice> findByDevPeaNoOrDevSerialNoLike(String textSearch);

        @Query(value = "SELECT * from tb_device d " +
        // "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "WHERE is_deleted = 0 AND (d.dev_pea_no LIKE '53%' OR d.dev_pea_no LIKE '501%')", nativeQuery = true)
        Page<tbDevice> findAll53(Pageable pageable);

        @Query(value = "SELECT * from tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "WHERE ((d.dev_pea_no like CONCAT('%',:peaNo,'%')) " +
                        "OR (e.emp_id = :empId)  " +
                        "OR (e.emp_name = :empName)) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%'))", nativeQuery = true)
        Page<tbDevice> findDeviceByPeaNoOrEmpIdOrEmpNameAndCC(@Param("peaNo") String peaNo,
                        @Param("empId") String empId, @Param("empName") String empName, @Param("ccLong") String ccLong,
                        Pageable pageable);

        @Query(value = "SELECT * from tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "WHERE ((e.emp_id = :empId)  " +
                        "OR (e.emp_name = :empName)) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%'))", nativeQuery = true)
        Page<tbDevice> findDeviceByEmpIdOrEmpNameAndCC(@Param("empId") String empId, @Param("empName") String empName,
                        @Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE d.cc_long_code LIKE CONCAT(:ccLong,'%') " +
                        "AND is_deleted = 0 " +
                        "AND (d.dev_pea_no LIKE '53%' OR d.dev_pea_no LIKE '501%')", nativeQuery = true)
        Page<Object[]> findDeviceByCcId53(@Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE d.cc_long_code LIKE CONCAT(:ccLong,'%')" +
                        "AND d.dev_left_price = 1 " +
                        "AND (d.dev_pea_no LIKE '53%' OR d.dev_pea_no LIKE '501%')", nativeQuery = true)
        Page<Object[]> findDeviceByCcId153(@Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.cc_long_code LIKE CONCAT(:ccLong,'%'))", nativeQuery = true)
        Page<Object[]> findDeviceByCcId(@Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.cc_long_code LIKE CONCAT(:ccLong,'%'))" +
                        "AND (d.dev_left_price = 1)", nativeQuery = true)
        Page<Object[]> findDeviceByCcId1all(@Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.dev_description LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_serial_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.emp_id LIKE CONCAT('%',:textSearch,'%') " +
                        "OR e.emp_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_received_date LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_received_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_left_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.cc_long_code LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_full_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_short_name LIKE CONCAT('%',:textSearch,'%')) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%'))", nativeQuery = true)
        Page<Object[]> findDeviceByCcIdAndTextSearch(@Param("ccLong") String ccLong,
                        @Param("textSearch") String textSearch, Pageable pageable);

        // @Query(value = "SELECT d.device_id, d.dev_pea_no, d.dev_description,
        // d.dev_serial_no, d.dev_received_date, " +
        // "d.dev_received_price, d.dev_left_price, d.cc_long_code AS
        // device_cc_long_code, " +
        // "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " +
        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.dev_description LIKE CONCAT('%', :textSearch, '%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%', :textSearch, '%') " +
                        "OR d.dev_serial_no LIKE CONCAT('%', :textSearch, '%') " +
                        "OR d.emp_id LIKE CONCAT('%', :textSearch, '%') " +
                        "OR e.emp_name LIKE CONCAT('%', :textSearch, '%') " +
                        "OR d.dev_received_date LIKE CONCAT('%', :textSearch, '%') " +
                        "OR CAST(d.dev_received_price AS CHAR) LIKE CONCAT('%', :textSearch, '%') " +
                        "OR CAST(d.dev_left_price AS CHAR) LIKE CONCAT('%', :textSearch, '%') " +
                        "OR c.cc_full_name LIKE CONCAT('%', :textSearch, '%') " +
                        "OR c.cc_short_name LIKE CONCAT('%', :textSearch, '%')) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong, '%')) " +
                        "AND ((d.dev_pea_no LIKE '53%') OR (d.dev_pea_no LIKE '501%'))", nativeQuery = true)
        Page<Object[]> findDeviceByCcIdAndTextSearch53(@Param("ccLong") String ccLong,
                        @Param("textSearch") String textSearch, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.dev_description LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_serial_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.emp_id LIKE CONCAT('%',:textSearch,'%') " +
                        "OR e.emp_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_received_date LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_received_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        // "OR CAST(d.dev_left_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.cc_long_code LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_full_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_short_name LIKE CONCAT('%',:textSearch,'%')) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%')) " +
                        "AND d.dev_left_price = 1 " +
                        "AND ((d.dev_pea_no LIKE '53%') OR (d.dev_pea_no LIKE '501%'))", nativeQuery = true)
        Page<Object[]> findDeviceByCcIdAndTextSearch153(@Param("ccLong") String ccLong,
                        @Param("textSearch") String textSearch, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.dev_description LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_serial_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.emp_id LIKE CONCAT('%',:textSearch,'%') " +
                        "OR e.emp_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_received_date LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_received_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_left_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.cc_long_code LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_full_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_short_name LIKE CONCAT('%',:textSearch,'%')) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%')) " +
                        "AND d.dev_left_price = 1 ", nativeQuery = true)
        Page<Object[]> findDeviceByCcIdAndTextSearch1all(@Param("ccLong") String ccLong,
                        @Param("textSearch") String textSearch, Pageable pageable);

        @Query(value = "SELECT * from tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        // "WHERE ((e.emp_id = :empId) " +
                        // "OR (e.emp_name = :empName)) " +
                        // "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%'))"
                        "WHERE (d.cc_long_code LIKE CONCAT(:ccLong,'%'))", nativeQuery = true)
        Page<tbDevice> findDeviceByEmpIdOrEmpNameAndCC2(
                        // @Param("empId") String empId, @Param("empName") String empName,
                        @Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT * from tb_device d " +
        // "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "WHERE d.cc_long_code LIKE CONCAT(:ccLong,'%') " +
                        "AND ((d.dev_pea_no LIKE '53%') OR (d.dev_pea_no LIKE '501%'))", nativeQuery = true)
        List<tbDevice> findDeviceForExcel53(@Param("ccLong") String ccLong);

        @Query(value = "SELECT * from tb_device d " +
        // "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "WHERE (d.dev_description LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR dev_serial_no LIKE CONCAT('%',:textSearch,'%')) " +
                        // "OR e.emp_id LIKE CONCAT('%',:textSearch,'%') " +
                        // "OR e.emp_name LIKE CONCAT('%',:textSearch,'%')) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%')) " +
                        "AND d.dev_left_price = 1 " +
                        "AND ((d.dev_pea_no LIKE '53%') OR (d.dev_pea_no LIKE '501%'))", nativeQuery = true)
        List<tbDevice> findDeviceForExcel53search(@Param("ccLong") String ccLong,
                        @Param("textSearch") String textSearch);

        // tbDevice findDeviceById(Long tbDeviceId);

        Collection<tbDevice> findDeviceByDevPeaNoLike(String devPeaNo);

        @Query(value = "SELECT d.dev_pea_no, d.dev_description, e.emp_name, d.dev_received_date from tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "WHERE d.cc_long_code LIKE CONCAT(:region,'%') " +
                        "AND d.device_type_id = :tb_device_type_id " +
                        "AND (d.dev_pea_no LIKE '53%' OR d.dev_pea_no LIKE '501%')", nativeQuery = true)
        Page<Object[]> getDevice53unpageByccId(@Param("region") String region,
                        @Param("tb_device_type_id") String tb_device_type_id,
                        Pageable pageable);

        @Query(value = "SELECT * from tb_device d " +
        // "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "WHERE d.cc_long_code LIKE CONCAT(:region,'%') " +
                        "AND d.device_type_id = :tb_device_type_id " +
                        "AND year(d.dev_received_date) >= 2558 " +
                        "AND ((d.dev_pea_no LIKE '53%') OR (d.dev_pea_no LIKE '501%'))", nativeQuery = true)
        Page<tbDevice> getDevice53unpageByccIdOnly7Year(@Param("region") String region,
                        @Param("tb_device_type_id") String tb_device_type_id,
                        Pageable pageable);

        tbDevice findAllByDevPeaNo(String devPeaNo);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.cc_long_code LIKE CONCAT(:ccLong,'%') OR d.cc_long_code LIKE 'ZC%')" +
                        "AND ((d.dev_pea_no LIKE '53%') OR (d.dev_pea_no LIKE '501%'))", nativeQuery = true)
        Page<Object[]> findDeviceByCcId53zc(@Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.cc_long_code LIKE CONCAT(:ccLong,'%') OR d.cc_long_code LIKE 'ZC%') " +
                        "AND d.dev_left_price = 1 " +
                        "AND ((d.dev_pea_no LIKE '53%') OR (d.dev_pea_no LIKE '501%'))", nativeQuery = true)
        Page<Object[]> findDeviceByCcId153zc(@Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.cc_long_code LIKE CONCAT(:ccLong,'%') OR d.cc_long_code LIKE 'ZC%')", nativeQuery = true)
        Page<Object[]> findDeviceByCcIdzc(@Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.cc_long_code LIKE CONCAT(:ccLong,'%') OR d.cc_long_code LIKE 'ZC%') " +
                        "AND (d.dev_left_price = 1)", nativeQuery = true)
        Page<Object[]> findDeviceByCcId1allzc(@Param("ccLong") String ccLong, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.dev_description LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_serial_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.emp_id LIKE CONCAT('%',:textSearch,'%') " +
                        "OR e.emp_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_received_date LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_received_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_left_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.cc_long_code LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_full_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_short_name LIKE CONCAT('%',:textSearch,'%')) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%') OR d.cc_long_code LIKE 'ZC%') " +
                        "AND ((d.dev_pea_no LIKE '53%') OR (d.dev_pea_no LIKE '501%'))", nativeQuery = true)
        Page<Object[]> findDeviceByCcIdAndTextSearch53zc(@Param("ccLong") String ccLong,
                        @Param("textSearch") String textSearch, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.dev_description LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_serial_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.emp_id LIKE CONCAT('%',:textSearch,'%') " +
                        "OR e.emp_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_received_date LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_received_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        // "OR CAST(d.dev_left_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.cc_long_code LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_full_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_short_name LIKE CONCAT('%',:textSearch,'%')) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%') OR d.cc_long_code LIKE 'ZC%') " +
                        "AND d.dev_left_price = 1 " +
                        "AND ((d.dev_pea_no LIKE '53%') OR (d.dev_pea_no LIKE '501%'))", nativeQuery = true)
        Page<Object[]> findDeviceByCcIdAndTextSearch153zc(@Param("ccLong") String ccLong,
                        @Param("textSearch") String textSearch, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.dev_description LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_serial_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.emp_id LIKE CONCAT('%',:textSearch,'%') " +
                        "OR e.emp_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_received_date LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_received_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_left_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.cc_long_code LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_full_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_short_name LIKE CONCAT('%',:textSearch,'%')) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%') OR d.cc_long_code LIKE 'ZC%')", nativeQuery = true)
        Page<Object[]> findDeviceByCcIdAndTextSearchzc(@Param("ccLong") String ccLong,
                        @Param("textSearch") String textSearch, Pageable pageable);

        @Query(value = "SELECT d.device_id, d.dev_description, d.dev_pea_no, d.dev_serial_no, " +
                        "d.emp_id, d.dev_received_date, d.dev_received_price, d.dev_left_price, " + //
                        "d.cc_long_code as device_cc_long_code, " + // -- Avoid conflic
                        "c.cc_full_name, c.cc_short_name, e.emp_name, e.emp_rank " + //
                        "FROM tb_device d " +
                        "LEFT JOIN tb_employee e ON d.emp_id = e.emp_id " +
                        "LEFT JOIN tb_cost_center c ON d.cc_long_code = c.cc_long_code " +
                        "WHERE (d.dev_description LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_pea_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_serial_no LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.emp_id LIKE CONCAT('%',:textSearch,'%') " +
                        "OR e.emp_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.dev_received_date LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_received_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR CAST(d.dev_left_price as CHAR) LIKE CONCAT('%',:textSearch,'%') " +
                        "OR d.cc_long_code LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_full_name LIKE CONCAT('%',:textSearch,'%') " +
                        "OR c.cc_short_name LIKE CONCAT('%',:textSearch,'%')) " +
                        "AND (d.cc_long_code LIKE CONCAT(:ccLong,'%') OR d.cc_long_code LIKE 'ZC%') " +
                        "AND d.dev_left_price = 1 ", nativeQuery = true)
        Page<Object[]> findDeviceByCcIdAndTextSearch1allzc(@Param("ccLong") String ccLong,
                        @Param("textSearch") String textSearch, Pageable pageable);

        // @Modifying
        // @Query(value = "INSERT INTO tb_device (dev_pea_no, dev_description,
        // dev_serial_no, dev_received_date, dev_received_price, dev_left_price,
        // dev_cc_long_code, emp_id) "
        // +
        // "VALUES (:peaNo, :description, :serialNo, :receivedDate, :receivedPrice,
        // :leftPrice, :ccLongCode, :empId)", nativeQuery = true)
        // void bulkInsertDevices(@Param("devices") List<Object[]> devices);
        void bulkInsertDevices(List<Object[]> devices);

}
