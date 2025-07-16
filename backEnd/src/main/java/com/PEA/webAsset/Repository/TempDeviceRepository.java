package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDevice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.PEA.webAsset.Entity.tbRepair;
import com.PEA.webAsset.Interface.TempDeviceInterface;
import com.PEA.webAsset.Entity.TempDevice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TempDeviceRepository extends JpaRepository<TempDevice, Long>, CustomTempDeviceRepository {

        public interface CustomTempDeviceRepository {
                void bulkInsertDevices(List<TempDevice> devices);
        }

        public interface TempDeviceSummary {
                String getDevConcatPriceDate();

                String getDevDescription();

                Date getDevReceivedDate();

                BigDecimal getDevReceivedPrice();
        }

        // @Modifying
        // @Query(value = "INSERT INTO tb_device (dev_pea_no, dev_description,
        // dev_serial_no, dev_received_date, dev_received_price, dev_left_price,
        // dev_cc_long_code, emp_id) "
        // +
        // "VALUES (:peaNo, :description, :serialNo, :receivedDate, :receivedPrice,
        // :leftPrice, :ccLongCode, :empId)", nativeQuery = true)
        // void bulkInsertDevices(@Param("devices") List<Object[]> devices);
        void bulkInsertDevices(List<TempDevice> devices);

        @Modifying
        @Transactional
        @Query(value = "UPDATE temp_device SET dev_concat_price_date = CONCAT(dev_received_price, '-', dev_received_date)", nativeQuery = true)
        int updateConcatPriceDate();

        @Modifying
        @Transactional
        @Query(value = "UPDATE temp_device\n" +
                        "LEFT JOIN tb_device_compare ON temp_device.dev_concat_price_date = tb_device_compare.dev_concat_price_date\n"
                        + //
                        "SET temp_device.device_type_id = tb_device_compare.device_type_id\n"
                        + //
                        "WHERE temp_device.dev_pea_no LIKE \"53%\" OR temp_device.dev_pea_no LIKE \"501%\"", nativeQuery = true)
        int updateTempDeviceType();

        @Query(value = "SELECT DISTINCT dev_concat_price_date, dev_description, dev_received_date, dev_received_price "
                        +
                        "FROM temp_device " +
                        "WHERE ((dev_pea_no LIKE '53%' OR dev_pea_no LIKE '501%') " +
                        "AND device_type_id IS NULL AND dev_pea_no LIKE '%0') " +
                        "ORDER BY dev_received_date DESC", countQuery = "SELECT COUNT(*) FROM temp_device WHERE ((dev_pea_no LIKE '53%' OR dev_pea_no LIKE '501%') AND device_type_id IS NULL AND dev_pea_no LIKE '%0')", nativeQuery = true)
        Page<TempDeviceInterface.TempDeviceSummary> checkNoMatch(Pageable pageable);

        // @Modifying
        // @Transactional
        // @Query(value = "INSERT INTO tb_device (\r\n" + //
        //                 "  dev_pea_no,\r\n" + //
        //                 "  dev_description,\r\n" + //
        //                 "  dev_received_date,\r\n" + //
        //                 "  dev_received_price,\r\n" + //
        //                 "  dev_concat_price_date,\r\n" + //
        //                 "  is_deleted,\r\n" + //
        //                 "  date_modified\r\n" + //
        //                 ")\r\n" + //
        //                 "SELECT \r\n" + //
        //                 "  t.dev_pea_no,\r\n" + //
        //                 "  t.dev_description,\r\n" + //
        //                 "  t.dev_received_date,\r\n" + //
        //                 "  t.dev_received_price,\r\n" + //
        //                 "  t.dev_concat_price_date,\r\n" + //
        //                 "  0,\r\n" + //
        //                 "  CURRENT_TIMESTAMP()\r\n" + //
        //                 "FROM temp_device t\r\n" + //
        //                 "ON DUPLICATE KEY UPDATE\r\n" + //
        //                 "  tb_device.dev_description = VALUES(dev_description),\r\n" + //
        //                 "  tb_device.dev_received_date = VALUES(dev_received_date),\r\n" + //
        //                 "  tb_device.dev_received_price = VALUES(dev_received_price),\r\n" + //
        //                 "  tb_device.dev_concat_price_date = VALUES(dev_concat_price_date),\r\n" + //
        //                 "  tb_device.is_deleted = 0,\r\n" + //
        //                 "  tb_device.date_modified = CURRENT_TIMESTAMP();", nativeQuery = true)
        // int upsertFromTempDevice();

        @Modifying
        @Query(value = "UPDATE tb_device d\r\n" + //
                        " JOIN temp_device t ON d.dev_pea_no = t.dev_pea_no\r\n" + //
                        " SET\r\n" + //
                        " d.dev_description = t.dev_description,\r\n" + //
                        " d.dev_received_date = t.dev_received_date,\r\n" + //
                        " d.dev_received_price = t.dev_received_price,\r\n" + //
                        " d.dev_concat_price_date = t.dev_concat_price_date,\r\n" + //
                        " d.is_deleted = 0,\r\n" + //
                        " d.date_modified = CURRENT_TIMESTAMP()\r\n" + //
                        " WHERE d.is_deleted = 0", nativeQuery = true)
        void updateFromTemp();

        @Modifying
        @Query(value = "INSERT INTO tb_device (\r\n" + //
                        " cc_long_code_string,date_modified,dev_concat_price_date,\r\n" + //
                        " dev_description,dev_left_price,dev_pea_no,\r\n" + //
                        " dev_received_date,dev_received_price,dev_serial_no,\r\n" + //                        
                        " emp_id_string,is_deleted,cc_long_code,device_type_id,emp_id)\r\n" + //
                        " SELECT\r\n" + //
                        " t.cc_long_code,CURRENT_TIMESTAMP,t.dev_concat_price_date,\r\n" + //
                        " t.dev_description,t.dev_left_price,t.dev_pea_no,\r\n" + //
                        " t.dev_received_date,t.dev_received_price,t.dev_serial_no,\r\n" + //
                        " emp_id_string,0,t.cc_long_code,t.device_type_id,t.emp_id\r\n" + //
                        " FROM temp_device t\r\n" + //
                        " WHERE NOT EXISTS (\r\n" + //
                        " SELECT 1 FROM tb_device d WHERE d.dev_pea_no = t.dev_pea_no)\r\n" + //
                        " AND EXISTS (SELECT 1 FROM tb_employee e WHERE e.emp_id = t.emp_id);", nativeQuery = true)
        int insertFromTemp();

        @Modifying
        @Query(value = "INSERT INTO tb_device (\r\n" + //
                        " cc_long_code_string,date_modified,dev_concat_price_date,\r\n" + //
                        " dev_description,dev_left_price,dev_pea_no,\r\n" + //
                        " dev_received_date,dev_received_price,dev_serial_no,\r\n" + //                        
                        " emp_id_string,is_deleted,cc_long_code,device_type_id,emp_id)\r\n" + //
                        " SELECT\r\n" + //
                        " t.cc_long_code,CURRENT_TIMESTAMP,t.dev_concat_price_date,\r\n" + //
                        " t.dev_description,t.dev_left_price,t.dev_pea_no,\r\n" + //
                        " t.dev_received_date,t.dev_received_price,t.dev_serial_no,\r\n" + //
                        " emp_id_string,0,t.cc_long_code,t.device_type_id,t.emp_id\r\n" + //
                        " FROM temp_device t\r\n" + //
                        " WHERE NOT EXISTS (\r\n" + //
                        " SELECT 1 FROM tb_device d WHERE d.dev_pea_no = t.dev_pea_no)\r\n" + //
                        " AND NOT EXISTS (SELECT 1 FROM tb_employee e WHERE e.emp_id = t.emp_id);", nativeQuery = true)
        int insertFromTemp2();

        @Modifying
        @Query(value = "UPDATE tb_device d\r\n" + //
                        " SET d.is_deleted = 1,\r\n" + //
                        " d.date_modified = CURRENT_TIMESTAMP()\r\n" + //
                        " WHERE NOT EXISTS (\r\n" + //
                        " SELECT 1 FROM temp_device t WHERE t.dev_pea_no = d.dev_pea_no)", nativeQuery = true)
        int softDeleteMissingDevices();
}
