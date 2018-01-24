import java.util.LinkedList;

class TransferCandidates {
    private LinkedList<Employee> transferCandidatesList = new LinkedList<Employee>();
    LinkedList<Employee> resultTransferCandidatesList = new LinkedList<Employee>();

    void searchForCandidate (SourceExcelSheet sourceExcelSheet) {
        for (Department dprt : sourceExcelSheet.departments) {
            dprt.calculateAverageSalary();
            for (Employee empl : dprt.employeesList) {
                if (empl.getSalary() <= dprt.getAverageSalary()) {
                        transferCandidatesList.add(empl);
                }
            }
        }
    }

    void filterCandidate (SourceExcelSheet sourceExcelSheet) {
        for (Employee empl : transferCandidatesList) {
            for (Department dprt : sourceExcelSheet.departments) {
                double newAverageSalary = (dprt.getTotalSalary() + empl.getSalary()) / (dprt.getEmployeesAmount() + 1);

                if (dprt.getAverageSalary() < newAverageSalary) {
                    empl.targetDepatments.append(dprt.getDepartmentName()).append(";\n");
                    resultTransferCandidatesList.add(empl);
                }
            }
        }
    }
}
