import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class TransferCandidates {
    private HashMap<String, Department> departmentHashMap = null;

    public void searchForCandidate (LinkedList<Employee> transferList) {
        LinkedList<Employee> transferCandidatesList = new LinkedList<>();
        calc();
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
        System.out.println("Совпадений старых " + iter);


        for (Employee empl : transferCandidatesList) {
            for (String s : departmentHashMap.keySet()) {
                Department dpt = departmentHashMap.get(s);
                if (dpt.getAverageSalary()
                        .compareTo ((dpt.getTotalSalary()
                                .add(empl.getSalary()))
                                .divide (new BigDecimal(dpt.getEmployeesList().size() + 1),
                                        RoundingMode.HALF_UP)) < 0) {
                    System.out.println("Добавление сотрудника " + empl.getName());
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

    private void calc() {
        for (Department dpt : departmentHashMap.values()) {
            int n = dpt.getEmployeesList().size();
            ArrayList<Employee> arrayList = dpt.getEmployeesList();
            int iter = 0;
            // Порядковый номер элемента
            for (int i = 0; i < n; i ++) {
                // Количество слагаемых
                for (int j = 0; j < n; j++) {
                    int varSal = arrayList.get(i).getSalary().intValueExact();  // Целевая сумма
                    int quan = 1;                                               // Количество
                    // Перебор слагаемых
                    for (int t = i + 1; t < j; t ++) {
                        varSal += arrayList.get(t).getSalary().intValueExact();
                        quan++;
                    }
                    //if (isSatisfies(dpt, varSal, quan)) {
                        iter++;
                    //}
                }
            }
            System.out.println("Совпадений новых " + iter + " " + dpt.getName());

        }
    }

    private boolean isSatisfies (Department dpt, int targSum, int targQuantity) {
        int avgSal = dpt.getAverageSalary().intValueExact();
        int totSal = dpt.getTotalSalary().intValueExact();
        int empNum = dpt.getEmployeesList().size();

        if (avgSal < ((totSal - targSum)/(empNum - targQuantity))) {
            return true;
        }
        return false;
    }

}
