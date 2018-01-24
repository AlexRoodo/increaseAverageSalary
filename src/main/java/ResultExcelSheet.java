import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

class ResultExcelSheet {
    private int i;

    void saveResultToFile (String file, LinkedList<Employee> resultTransferCandidatesList) throws IOException{
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Transfer");

        for (Employee currentEmployee : resultTransferCandidatesList) {
            Row row = sheet.createRow(i);

            Cell name = row.createCell(0);
            name.setCellValue(currentEmployee.getName());
            Cell salary = row.createCell(1);
            salary.setCellValue(currentEmployee.getSalary().toString());
            Cell departments = row.createCell(2);
            departments.setCellValue(currentEmployee.targetDepatments.toString());
            i++;
        }


        sheet.autoSizeColumn(1);

        book.write(new FileOutputStream(file));
        book.close();
    }
}
