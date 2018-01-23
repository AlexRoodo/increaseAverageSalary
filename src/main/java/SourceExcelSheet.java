import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.LinkedList;

class SourceExcelSheet {
    private LinkedList<String> departmentsNames = new LinkedList<String>();
    private LinkedList<Department> departments = new LinkedList<Department>();

    void readFromExcel(String file) throws IOException{

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            String currentDepartmentName = row.getCell(0).getStringCellValue();
            String currentEmployeeName = row.getCell(1).getStringCellValue();
            double currentEmployeeSalary = row.getCell(2).getNumericCellValue();
            if (!departmentsNames.contains(currentDepartmentName)) {
                departmentsNames.add(currentDepartmentName);
                departments.add(new Department(currentDepartmentName));
            }
            departments.getLast().employeesList.add(
                    new Employee(currentEmployeeName, currentEmployeeSalary, currentDepartmentName));
        }


        workbook.close();
    }
}
