package com.PEA.webAsset.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.PEA.webAsset.Entity.TempDevice;
import com.PEA.webAsset.Entity.tbCostCenter;
import com.PEA.webAsset.Entity.tbEmployee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TempDeviceRepositoryImpl implements CustomTempDeviceRepository {

    @Autowired
    private CostCenterRepository costCenterRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

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
        String truncateSql = "TRUNCATE TABLE temp_device";
        // String insertSql = "INSERT INTO temp_device (dev_pea_no, dev_description,
        // dev_serial_no, dev_received_date, dev_received_price, dev_left_price,
        // cc_long_code, emp_id, update_at) "
        // + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String insertSql = "INSERT INTO temp_device (" +
                "dev_pea_no, dev_description, dev_serial_no, dev_received_date, dev_received_price, " +
                "dev_left_price, cc_long_code, emp_id, update_at, cc_long_code_string, emp_id_string" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        System.out.println("now " + now);

        entityManager.unwrap(Session.class).doWork(connection -> {
            try (
                    Statement truncateStatement = connection.createStatement();
                    PreparedStatement ps = connection.prepareStatement(insertSql)) {

                truncateStatement.execute(truncateSql);

                for (TempDevice device : devices) {

                    tbCostCenter costCenter = null;
                    if (device.getCcLongCodeString() != null) {
                        costCenter = costCenterRepo.findByCcLongCode(device.getCcLongCodeString()).orElse(null);
                        device.setTbCostCenter(costCenter);
                    }

                    tbEmployee employee = null;
                    if (device.getEmpIdString() != null) {
                        employee = employeeRepo.findEmpByEmpId(device.getEmpIdString()).orElse(null);
                        device.setTbEmployee(employee);
                    }

                    String ccLongCode = costCenter != null ? costCenter.getCcLongCode() : null;
                    String empId = employee != null ? employee.getEmpId() : null;

                    // System.out.println("ccLongCode: " + ccLongCode);
                    System.out.println("empId: " + empId);

                    System.out.println("Raw ccLongCodeString: " + device.getCcLongCodeString());
                    System.out.println("Resolved ccLongCode (FK): " + ccLongCode);

                    // System.out.println("nowFormatted " + nowFormatted);
                    ps.setString(1, device.getDevPeaNo());
                    ps.setString(2, device.getDevDescription());
                    ps.setString(3, device.getDevSerialNo());
                    ps.setString(4, device.getDevReceivedDate());
                    ps.setObject(5, parseDoubleSafe(device.getDevReceivedPrice().toString()), Types.DOUBLE);
                    ps.setObject(6, parseDoubleSafe(device.getDevLeftPrice().toString()), Types.DOUBLE);
                    ps.setObject(7, ccLongCode, Types.VARCHAR);
                    ps.setObject(8, empId, Types.VARCHAR);
                    // ps.setString(9, nowFormatted); // ✅ update_at as string
                    ps.setTimestamp(9, now);
                    ps.setString(10, ccLongCode == null ? device.getCcLongCodeString() : null);
                    ps.setString(11, empId == null ? device.getEmpIdString() : null);
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
