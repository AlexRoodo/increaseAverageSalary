import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;

class TransferCandidates {
    private LinkedList<Employee> transferCandidatesList = new LinkedList<>();
    private HashMap<String, Department> departmentHashMap = null;
    LinkedList<Employee> resultTransferCandidatesList = new LinkedList<>();

    void searchForCandidate () {
        for (String s : departmentHashMap.keySet()) {
            Department dpt = departmentHashMap.get(s);
            for (Employee emp : dpt.getEmployeesList()) {
                if(dpt.getAverageSalary()
                        .compareTo(dpt.getTotalSalary()
                                .subtract(emp.getSalary())
                                .divide(new BigDecimal(dpt.getEmployeesList().size() - 1), RoundingMode.HALF_UP)) < 0) {
                    transferCandidatesList.add(emp);
                }
            }
        }
    }

    void filterCandidate () {
        for (Employee empl : transferCandidatesList) {
            for (String s : departmentHashMap.keySet()) {
                Department dpt = departmentHashMap.get(s);
                if (dpt.getAverageSalary()
                        .compareTo((dpt.getTotalSalary()
                                .add(empl.getSalary()))
                                .divide(new BigDecimal(dpt.getEmployeesList().size() + 1), RoundingMode.HALF_UP)) < 0) {
                    System.out.println("Добавление сотрудника " + empl.getName());
                    resultTransferCandidatesList.add(empl);
                }
            }
        }
    }

    public HashMap<String, Department> getDepartmentHashMap() {
        if (departmentHashMap == null) {
            departmentHashMap = new HashMap<>();
        }
        return departmentHashMap;
    }

}
