package com.PEA.webAsset.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.PEA.webAsset.Entity.TempDevice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TempDeviceRepositoryImpl implements CustomTempDeviceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private Double parseDoubleSafe(String input) {
        if (input == null || input.trim().isEmpty() || input.trim().equals("\"\"")) {
            return null;
        }
        return Double.valueOf(input.trim());
    }

    private Long parseLongSafe(String input) {
        if (input == null || input.trim().isEmpty() || input.trim().equals("\"\"")) {
            return null;
        }
        return Long.valueOf(input.trim());
    }

    @Transactional
    @Override
    public void bulkInsertDevices(List<TempDevice> devices) {
        String sql = "INSERT INTO temp_device (dev_pea_no, dev_description, dev_serial_no, dev_received_date, dev_received_price, dev_left_price, cc_long_code, emp_id, update_at) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // LocalDate today = LocalDate.now();
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        System.out.println("now " + now);

        entityManager.unwrap(Session.class).doWork(connection -> {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (TempDevice device : devices) {
                    // System.out.println("nowFormatted " + nowFormatted);
                    ps.setString(1, device.getDevPeaNo());
                    ps.setString(2, device.getDevDescription());
                    ps.setString(3, device.getDevSerialNo());
                    ps.setString(4, device.getDevReceivedDate());
                    ps.setObject(5, parseDoubleSafe(device.getDevReceivedPrice().toString()), Types.DOUBLE);
                    ps.setObject(6, parseDoubleSafe(device.getDevLeftPrice().toString()), Types.DOUBLE);
                    ps.setString(7, device.getCcLongCode());
                    ps.setObject(8, parseLongSafe(device.getEmpId()), Types.BIGINT);
                    // ps.setString(9, nowFormatted); // ✅ update_at as string
                    ps.setTimestamp(9, now);
                    ps.addBatch();
                }
                ps.executeBatch(); // Execute the batch
                ps.clearBatch(); // Clear the batch
            } catch (SQLException e) {
                throw new RuntimeException("Failed to execute batch insert: " + e.getMessage(), e);
            }
        });
    }
}
