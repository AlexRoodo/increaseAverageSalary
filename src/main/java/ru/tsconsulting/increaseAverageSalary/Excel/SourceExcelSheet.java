package ru.tsconsulting.increaseAverageSalary.Excel;

import ru.tsconsulting.increaseAverageSalary.Objects.*;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;

public class SourceExcelSheet {

    public void readFromExcel(String file, HashMap<String, Department> departmentHashMap)
            throws InvalidOperationException, IOException {

        try(XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (!cellsInitCheck(row)) {
                    break;
                }
                String firstCellValue = row.getCell(0).getStringCellValue();
                if(!departmentHashMap.containsKey(firstCellValue)) {
                    departmentHashMap.put(firstCellValue, new Department(firstCellValue));
                }
                departmentHashMap.get(firstCellValue).getEmployeesList()
                        .add(new Employee(row.getCell(1).getStringCellValue(),
                                new BigDecimal(row.getCell(2).getNumericCellValue())));
            }

        } catch (InvalidOperationException e) {
            System.out.println("Ошибка при чтении файла. Указан неверный путь к исходному файлу!");
        } catch (NullPointerException e) {
            System.out.println("Ошибка при чтении данных! Проверьте заполнение таблицы.");
            System.exit(1);
        }
    }

    public boolean cellsInitCheck (Row row) {
        boolean result = true;
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (i == 2 && cell.getNumericCellValue() < 0) {
                result = false;
            } else if (i != 2 && cell.getStringCellValue().isEmpty()) {
                result = false;
            }
        }
        return result;
    }
}
