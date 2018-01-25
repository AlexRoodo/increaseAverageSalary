import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

class TransferCandidates {
    private LinkedList<Employee> transferCandidatesList = new LinkedList<>();
    LinkedList<Employee> resultTransferCandidatesList = new LinkedList<>();

    void searchForCandidate (SourceExcelSheet sourceExcelSheet) {
        for (Department dprt : sourceExcelSheet.departments) {
            for (Employee empl : dprt.getEmployeesList()) {
                if(dprt.getAverageSalary()
                        .compareTo(dprt.getTotalSalary()
                                .subtract(empl.getSalary()
                                        .divide(new BigDecimal(dprt.getEmployeesList().size() - 1), RoundingMode.HALF_UP))) < 0) {
                    transferCandidatesList.add(empl);
                }
            }
        }
    }

    void filterCandidate (SourceExcelSheet sourceExcelSheet) {
        for (Employee empl : transferCandidatesList) {
            for (Department dprt : sourceExcelSheet.departments) {
                if (dprt.getAverageSalary()
                        .compareTo((dprt.getTotalSalary()
                                .add(empl.getSalary()))
                                .divide(new BigDecimal(dprt.getEmployeesList().size() + 1), RoundingMode.HALF_UP)) < 0) {
                    System.out.println("Добавление сотрудника " + empl.getName());
                    empl.targetDepartments.append(dprt.getDepartmentName()).append("\n");
                    resultTransferCandidatesList.add(empl);
                }
            }
        }
    }
}
