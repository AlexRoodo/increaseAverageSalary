import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;

class SourceExcelSheet {
    LinkedList<Department> departments = new LinkedList<Department>();

    private boolean isDepartmentExist (String checkedName) {
        boolean result = false;
        if (departments.isEmpty()) return false;
        for (Department dpt : departments) {
            if (dpt.getDepartmentName().equalsIgnoreCase(checkedName)) {
                result = true;
            }
        }
        return result;
    }

    void readFromExcel(String file) throws IOException{

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            String currentDepartmentName = row.getCell(0).getStringCellValue();
            String currentEmployeeName = row.getCell(1).getStringCellValue();
            BigDecimal currentEmployeeSalary = new BigDecimal(row.getCell(2).getNumericCellValue());

            if (!isDepartmentExist(currentDepartmentName)) {
                departments.add(new Department(currentDepartmentName));
            }
            departments.getLast().employeesList.add(
                    new Employee(currentEmployeeName, currentEmployeeSalary, currentDepartmentName));
            departments.getLast().increaseEmployeesAmount();
            departments.getLast().increaseTotalSalary(currentEmployeeSalary);
        }

        workbook.close();
    }
}
