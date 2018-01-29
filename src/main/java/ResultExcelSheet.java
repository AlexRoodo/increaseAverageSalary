import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

class ResultExcelSheet {

    public void writeResultToFile(String file, LinkedList<Transfer> transferLinkedList)
            throws IOException{
        int i = 0;

        try(Workbook book = new XSSFWorkbook()) {
            Sheet sheet = book.createSheet("Transfer");

            Iterator<Transfer> iterator = transferLinkedList.listIterator();
            String currentDepartmentName = transferLinkedList.getFirst().getCurrentDepartment()
                    .getName();


            while (iterator.hasNext()) {
                Transfer currentTransfer = iterator.next();
                if (!currentTransfer.getCurrentDepartment().getName()
                        .equalsIgnoreCase(currentDepartmentName)) {
                    Row row = sheet.createRow(i);
                    Cell departmentName = row.createCell(0);
                    departmentName.setCellValue(currentTransfer.getCurrentDepartment().getName());
                    Cell averageSalaryValue = row.createCell(1);
                    averageSalaryValue.setCellValue(currentTransfer.getCurrentDepartment()
                            .getAverageSalary().doubleValue());
                }
            }

            for (Transfer transfer : transferLinkedList) {
                Row row = sheet.createRow(i);
                Cell name = row.createCell(0);
                //name.setCellValue(currentEmployee.getName());
                Cell salary = row.createCell(1);
                //salary.setCellValue(currentEmployee.getSalary().toString());
                i++;
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);


            book.write(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при записи файла. Указан неверный путь к файлу для записи!");
        } catch (IOException e) {
            System.out.println("Ошибка при выводе данных!");
            System.exit(1);
        }
    }
}
