package com.PEA.webAsset.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public interface CustomDeviceRepository {

    void bulkInsertDevices(List<Object[]> devices);

    public class CustomDeviceRepositoryImpl implements CustomDeviceRepository {

        @PersistenceContext
        private EntityManager entityManager;

        @Transactional
        @Override
        public void bulkInsertDevices(List<Object[]> devices) {
            String sql = "INSERT INTO tb_device (dev_pea_no, dev_description, dev_serial_no, dev_received_date, dev_received_price, dev_left_price, cc_long_code, emp_id, cc_long_code_string) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            entityManager.unwrap(Session.class).doWork(connection -> {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    for (Object[] device : devices) {
                        ps.setObject(1, device[0], Types.VARCHAR); // dev_pea_no
                        ps.setObject(2, device[1], Types.VARCHAR); // dev_description
                        ps.setObject(3, device[2], Types.VARCHAR); // dev_serial_no
                        ps.setObject(4, device[3], Types.VARCHAR); // dev_received_date
                        ps.setObject(5, device[4], Types.DOUBLE); // dev_received_price
                        ps.setObject(6, device[5], Types.DOUBLE); // dev_left_price
                        ps.setObject(7, device[6], Types.VARCHAR); // cc_id
                        ps.setObject(8, device[7], Types.BIGINT); // emp_id (nullable)
                        ps.setObject(9, device[8], Types.VARCHAR); // cc_long_code_string
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
}
