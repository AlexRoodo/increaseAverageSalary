import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;

class SourceExcelSheet {
    LinkedList<Department> departments = new LinkedList<>();

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

    void readFromExcel(String file) throws InvalidOperationException, IOException {

        try(XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                String currentDepartmentName = row.getCell(0).getStringCellValue();
                String currentEmployeeName = row.getCell(1).getStringCellValue();
                BigDecimal currentEmployeeSalary = new BigDecimal(row.getCell(2).getNumericCellValue());

                if (!isDepartmentExist(currentDepartmentName)) {
                    departments.add(new Department(currentDepartmentName));
                }
                departments.getLast().employeesList.add(
                        new Employee(currentEmployeeName, currentEmployeeSalary));
                departments.getLast().increaseEmployeesAmount();
                departments.getLast().increaseTotalSalary(currentEmployeeSalary);
            }
        } catch (InvalidOperationException e) {
            System.out.println("Ошибка при чтении файла. Указан неверный путь к исходному файлу!");
        } catch (NullPointerException e) {
            System.out.println("Ошибка при чтении данных!");
        }
    }
}
