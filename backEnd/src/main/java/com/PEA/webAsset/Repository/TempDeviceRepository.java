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
                        "SET temp_device.device_type_id = tb_device_compare.device_type_id", nativeQuery = true)
        int updateTempDeviceType();

        @Query(value = "SELECT DISTINCT dev_concat_price_date, dev_description, dev_received_date, dev_received_price "
                        +
                        "FROM temp_device " +
                        "WHERE ((dev_pea_no LIKE '53%' OR dev_pea_no LIKE '501%') " +
                        "AND device_type_id IS NULL AND dev_pea_no LIKE '%0') " +
                        "ORDER BY dev_received_date DESC", countQuery = "SELECT COUNT(*) FROM temp_device WHERE ((dev_pea_no LIKE '53%' OR dev_pea_no LIKE '501%') AND device_type_id IS NULL AND dev_pea_no LIKE '%0')", nativeQuery = true)
        Page<TempDeviceInterface.TempDeviceSummary> checkNoMatch(Pageable pageable);

}
