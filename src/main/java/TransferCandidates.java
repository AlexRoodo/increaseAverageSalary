import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

class TransferCandidates {
    private LinkedList<Employee> transferCandidatesList = new LinkedList<>();
    LinkedList<Employee> resultTransferCandidatesList = new LinkedList<>();

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
                BigDecimal newEmployeesAmount = new BigDecimal((dprt.getEmployeesAmount() + 1));
                BigDecimal newAverageSalary =
                        (dprt.getTotalSalary().add(empl.getSalary())).divide(newEmployeesAmount, RoundingMode.HALF_UP);
                if (dprt.getAverageSalary().compareTo(newAverageSalary) < 0) {
                    System.out.println("Добавление сотрудника " + empl.getName());
                    empl.targetDepartments.append(dprt.getDepartmentName()).append("\n");
                    resultTransferCandidatesList.add(empl);
                }
            }
        }
    }
}
