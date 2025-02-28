package com.PEA.webAsset.Share.ExcelService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.PEA.webAsset.Entity.tbCostCenter;
import com.PEA.webAsset.Entity.tbEmployee;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<tbEmployee> parseEmployeeFile(InputStream is) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            XSSFSheet sheet = workbook.getSheetAt(0); // Get the first sheet
            List<tbEmployee> employees = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // Skip the header
                XSSFRow row = sheet.getRow(i);

                if (row == null || row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                    continue; // Skip empty rows
                }

                tbEmployee employee = new tbEmployee();
                employee.setEmpId(formatter.formatCellValue(row.getCell(0))); // Column A: empId
                employee.setEmpName(formatter.formatCellValue(row.getCell(1))); // Column B: empName
                employee.setEmpRank(formatter.formatCellValue(row.getCell(2))); // Column C: empRole
                String costCenterCode = formatter.formatCellValue(row.getCell(3)); // Column D: costCenterCode

                // Store the costCenterCode temporarily in the employee object
                // (can be resolved in the service layer later)
                employee.setCostCenterCode(costCenterCode);

                employees.add(employee);
            }

            return employees;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }

    public static String extractCellValue(DataFormatter formatter, Cell cell, String fieldName, int rowIndex) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            if (fieldName != null) {
                System.out.println("Warning: Missing or empty " + fieldName + " in row: " + rowIndex);
            } else {
                return ""; 
            }
        }
        return formatter.formatCellValue(cell).trim();
    }
    
    public static String extractCellValue(DataFormatter formatter, Cell cell) {
        return extractCellValue(formatter, cell, null, 0);
    }

    public static Double extractCellNumericValue(Cell cell, String fieldName, int rowIndex) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return 0.0; // Default to 0.0 for numeric values
        }
        if (cell.getCellType() != CellType.NUMERIC) {
            throw new RuntimeException("Invalid numeric value for " + fieldName + " in row: " + rowIndex);
        }
        return cell.getNumericCellValue();
    }

}
