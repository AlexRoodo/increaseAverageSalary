import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

class ResultExcelSheet {

    void saveResultToFile (String file, LinkedList<Employee> resultTransferCandidatesList)
            throws IOException{
        int i = 0;

        try(Workbook book = new XSSFWorkbook()) {
            Sheet sheet = book.createSheet("Transfer");

            for (Employee currentEmployee : resultTransferCandidatesList) {
                Row row = sheet.createRow(i);

                Cell name = row.createCell(0);
                name.setCellValue(currentEmployee.getName());
                Cell salary = row.createCell(1);
                salary.setCellValue(currentEmployee.getSalary().toString());
                Cell department = row.createCell(2);
                i++;
            }

            sheet.autoSizeColumn(0);

            book.write(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла. Указан неверный путь к файлу для записи!");
        } catch (IOException e) {
            System.out.println("Ошибка при вводе данных!");
        }
    }
}
