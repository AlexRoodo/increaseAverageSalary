import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;

class SourceExcelSheet {

    public void readFromExcel(String file, HashMap<String, Department> departmentHashMap)
            throws InvalidOperationException, IOException {

        try(XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if(!departmentHashMap.containsKey(row.getCell(0).getStringCellValue())) {
                    departmentHashMap.put(row.getCell(0).getStringCellValue(), new Department());
                }
                departmentHashMap.get(row.getCell(0).getStringCellValue()).getEmployeesList()
                        .add(new Employee(row.getCell(1).getStringCellValue(),
                                new BigDecimal(row.getCell(2).getNumericCellValue())));
            }

        } catch (InvalidOperationException e) {
            System.out.println("Ошибка при чтении файла. Указан неверный путь к исходному файлу!");
        } catch (NullPointerException e) {
            System.out.println("Ошибка при чтении данных!");
            System.exit(1);
        }
    }

    public void cellsInitCheck () {

    }
}
