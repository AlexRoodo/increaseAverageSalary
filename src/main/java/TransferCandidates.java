import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

class TransferCandidates {
    private LinkedList<Employee> transferCandidatesList = new LinkedList<Employee>();
    LinkedList<Employee> resultTransferCandidatesList = new LinkedList<Employee>();

    void searchForCandidate (SourceExcelSheet sourceExcelSheet) {
        for (Department dprt : sourceExcelSheet.departments) {
            dprt.calculateAverageSalary();
            for (Employee empl : dprt.employeesList) {
                if (empl.getSalary().compareTo(dprt.getAverageSalary()) <= 0 ) {
                        transferCandidatesList.add(empl);
                }
            }
        }
    }

    void filterCandidate (SourceExcelSheet sourceExcelSheet) {
        for (Employee empl : transferCandidatesList) {
            for (Department dprt : sourceExcelSheet.departments) {
                BigDecimal newAverageSalary =
                        (dprt.getTotalSalary().add(empl.getSalary()))
                                .divide(dprt.getEmployeesAmount().add(new BigDecimal("1")), RoundingMode.HALF_UP);
                if (dprt.getAverageSalary().compareTo(newAverageSalary) < 0) {
                    System.out.println("Добавление сотрудника " + empl.getName());
                    empl.targetDepatments.append(dprt.getDepartmentName()).append("\n");
                    resultTransferCandidatesList.add(empl);
                }
            }
        }
    }
}
