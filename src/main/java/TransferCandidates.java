import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class TransferCandidates {
    private HashMap<String, Department> departmentHashMap = null;
    private LinkedList<Transfer> transferLinkedList = null;

    public void searchForCandidate (LinkedList<Employee> transferList) {
        LinkedList<Employee> transferCandidatesList = new LinkedList<>();
        combinationsSearch();
        int iter = 0;

        for (String s : departmentHashMap.keySet()) {
            Department dpt = departmentHashMap.get(s);
            for (Employee emp : dpt.getEmployeesList()) {
                if(dpt.getAverageSalary()
                        .compareTo (dpt.getTotalSalary()
                                .subtract(emp.getSalary())
                                .divide (new BigDecimal(dpt.getEmployeesList().size() - 1),
                                        RoundingMode.HALF_UP)) < 0) {
                    iter++;
                    transferCandidatesList.add(emp);
                }
            }
        }


        for (Employee empl : transferCandidatesList) {
            for (String s : departmentHashMap.keySet()) {
                Department dpt = departmentHashMap.get(s);
                if (dpt.getAverageSalary()
                        .compareTo ((dpt.getTotalSalary()
                                .add(empl.getSalary()))
                                .divide (new BigDecimal(dpt.getEmployeesList().size() + 1),
                                        RoundingMode.HALF_UP)) < 0) {
                    transferList.add(empl);
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

    private void combinationsSearch() {
        LinkedList<Employee> combination = new LinkedList<>();
        for (Department currentDepartment : departmentHashMap.values()) {
            for (int mask = 0; mask < (1 << currentDepartment.getEmployeesList().size()); mask++) {
                for (int i = 0; i < currentDepartment.getEmployeesList().size(); i++) {
                    if ((mask & (1 << i)) != 0) {
                        combination.add(currentDepartment.getEmployeesList().get(i));
                    }
                }

            }
        }
        System.out.println(combination.size());
    }

}
