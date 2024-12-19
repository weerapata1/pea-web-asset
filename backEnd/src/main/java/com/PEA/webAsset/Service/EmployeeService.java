package com.PEA.webAsset.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PEA.webAsset.Entity.tbCostCenter;
import com.PEA.webAsset.Entity.tbEmployee;
import com.PEA.webAsset.Repository.CostCenterRepository;
import com.PEA.webAsset.Repository.EmployeeRepository;
import com.PEA.webAsset.Share.ExcelService.ExcelHelper;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CostCenterRepository costCenterRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CostCenterRepository costCenterRepository) {
        this.employeeRepository = employeeRepository;
        this.costCenterRepository = costCenterRepository;
    }

    public List<tbEmployee> saveEmployeesFromFile(MultipartFile file) {
        DataFormatter formatter = new DataFormatter();

        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            XSSFSheet sheet = workbook.getSheetAt(0); // First sheet in the file
            List<tbEmployee> employees = new ArrayList<>();

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // Skip the header row
                XSSFRow row = sheet.getRow(i);

                if (row == null || row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                    continue; // Skip empty rows
                }

                tbEmployee employee = new tbEmployee();

                // Map specific columns to fields
                employee.setTbEmployeeId(Long.parseLong(ExcelHelper.extractCellValue(formatter, row.getCell(0)))); // Column
                                                                                                                   // A:
                                                                                                                   // ID
                employee.setEmpId(ExcelHelper.extractCellValue(formatter, row.getCell(1))); // Column B: Employee ID
                employee.setEmpName(ExcelHelper.extractCellValue(formatter, row.getCell(2))); // Column C: Employee Name
                employee.setEmpRole(ExcelHelper.extractCellValue(formatter, row.getCell(3))); // Column D: Employee Role
                employee.setEmpDepFull(ExcelHelper.extractCellValue(formatter, row.getCell(4))); // Column E: Department
                                                                                                 // Full Name

                // Handle Cost Center (Column F)
                String costCenterCode = ExcelHelper.extractCellValue(formatter, row.getCell(5)); // Column F: Cost
                                                                                                 // Center Code
                if (costCenterCode != null && !costCenterCode.isEmpty()) {
                    // Use the costCenterCode to fetch the associated tbCostCenter
                    tbCostCenter costCenter = costCenterRepository.findByCcLongCode(costCenterCode)
                            .orElseThrow(
                                    () -> new RuntimeException("Cost center not found for code: " + costCenterCode));
                    employee.setCostCenter(costCenter);
                }

                // employees.add(employee);
                Optional<tbEmployee> existingEmployee = employeeRepository.findEmpByEmpId(employee.getEmpId());
                if (existingEmployee.isPresent()) {
                    // Optionally update the existing record instead of throwing an error
                    tbEmployee existing = existingEmployee.get();
                    existing.setEmpName(employee.getEmpName());
                    existing.setEmpRole(employee.getEmpRole());
                    existing.setEmpDepFull(employee.getEmpDepFull());
                    employeeRepository.save(existing);
                } else {
                    employeeRepository.save(employee);
                }
            }

            // Save employees to the database
            return employeeRepository.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }
}
