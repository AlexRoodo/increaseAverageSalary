import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;

public class ResultExcelSheet {

    public void writeResultToFile(String file, LinkedList<Transfer> transferLinkedList)
            throws IOException{

        try(Workbook book = new XSSFWorkbook()) {
            Sheet sheet = book.createSheet("Transfer");
            Iterator<Transfer> iterator = transferLinkedList.listIterator();

            writeToRowsAndCells(sheet, iterator);

            book.write(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при записи файла. Указан неверный путь к файлу для записи!");
        } catch (IOException e) {
            System.out.println("Ошибка при выводе данных!");
            System.exit(1);
        }
    }

    private void writeToRowsAndCells (Sheet sheet, Iterator<Transfer> iterator) {
        int i = 0;
        int j = 0;
        String currentDepartmentName = "";
        String targetDepartmentName = "";
        while (iterator.hasNext()) {
            Transfer currentTransfer = iterator.next();

            if (!currentTransfer.getCurrentDepartment().getName()
                    .equalsIgnoreCase(currentDepartmentName)) {
                currentDepartmentName = currentTransfer.getCurrentDepartment().getName();
                targetDepartmentName = "";

                Row currentDepHeaderRow = sheet.createRow(i);

                Cell currentDepMessage = currentDepHeaderRow.createCell(0);
                currentDepMessage.setCellValue("Список возможных переводов из");

                Cell currentDepNameCell = currentDepHeaderRow.createCell(1);
                currentDepNameCell.setCellValue(currentTransfer.getCurrentDepartment().getName());

                Cell currentDepAvgSalaryCell = currentDepHeaderRow.createCell(2);
                currentDepAvgSalaryCell.setCellValue(currentTransfer.getCurrentDepartment()
                        .getAverageSalary().doubleValue());
                i++;
            }

            if (!currentTransfer.getTargetDepartment().getName()
                    .equalsIgnoreCase(targetDepartmentName)) {
                targetDepartmentName = currentTransfer.getTargetDepartment().getName();

                Row targetDepHeaderRow = sheet.createRow(i);

                Cell targetDepMessage = targetDepHeaderRow.createCell(0);
                targetDepMessage.setCellValue("Возможен перевод в");

                Cell targetDepNameCell = targetDepHeaderRow.createCell(1);
                targetDepNameCell.setCellValue(currentTransfer.getTargetDepartment().getName());

                Cell targetDepAvgSalaryCell = targetDepHeaderRow.createCell(2);
                targetDepAvgSalaryCell.setCellValue(currentTransfer.getTargetDepartment()
                        .getAverageSalary().doubleValue());
                i++;
                j = 0;
            }

            Row transferMessageRow = sheet.createRow(i);
            i++;

            Cell transferMessageCell = transferMessageRow.createCell(0);
            transferMessageCell.setCellValue("Вариант № " + j);
            j++;

            for (Employee employee : currentTransfer.getEmployeesToTransfer()) {
                Row employeeRow = sheet.createRow(i);
                i++;

                Cell employeeName = employeeRow.createCell(0);
                employeeName.setCellValue(employee.getName());

                Cell employeeSalary = employeeRow.createCell(1);
                employeeSalary.setCellValue(employee.getSalary().doubleValue());
            }

            Row transferResultRow = sheet.createRow(i);
            i++;

            Cell transferResultMessageCell = transferResultRow.createCell(0);
            transferResultMessageCell.setCellValue("Средняя зарплата после перевода ");

            Cell currentDepNameCell = transferResultRow.createCell(1);
            currentDepNameCell.setCellValue(currentDepartmentName);

            BigDecimal combinationTotalSalary = new BigDecimal("0");

            for (Employee employee : currentTransfer.getEmployeesToTransfer()) {
                combinationTotalSalary = combinationTotalSalary.add(employee.getSalary());
            }

            Cell currentDepResultCell = transferResultRow.createCell(2);
            currentDepResultCell.setCellValue(currentTransfer.getCurrentDepartment().getTotalSalary()
                    .subtract(combinationTotalSalary).divide(new BigDecimal(
                            currentTransfer.getCurrentDepartment().getEmployeesList().size() -
                            currentTransfer.getEmployeesToTransfer().size()), RoundingMode
                            .HALF_UP).doubleValue());

            Row transferTargetResultRow = sheet.createRow(i);
            i++;

            Cell targetDepNameCell = transferTargetResultRow.createCell(1);
            targetDepNameCell.setCellValue(targetDepartmentName);

            Cell targetDepResultName = transferTargetResultRow.createCell(2);
            targetDepResultName.setCellValue(currentTransfer.getTargetDepartment().getTotalSalary()
                    .add(combinationTotalSalary).divide(new BigDecimal(
                            currentTransfer.getTargetDepartment().getEmployeesList().size() +
                            currentTransfer.getEmployeesToTransfer().size()), RoundingMode
                            .HALF_UP).doubleValue());
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
    }
}
