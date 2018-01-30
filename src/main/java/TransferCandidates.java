import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

class TransferCandidates {
    private HashMap<String, Department> departmentHashMap = null;
    public LinkedList<Transfer> transferLinkedList = null;

    public void searchForCandidate () {
        transferLinkedList = new LinkedList<>();

        for (Department currentDepartment : departmentHashMap.values()) {
            for (Department targetDepartment : departmentHashMap.values()) {
                if (!currentDepartment.getName().equalsIgnoreCase(targetDepartment.getName())) {
                    combinationsSearch(currentDepartment, targetDepartment);
                }

            }
        }
        System.out.println(transferLinkedList.size());
    }

    public HashMap<String, Department> getDepartmentHashMap() {
        if (departmentHashMap == null) {
            departmentHashMap = new HashMap<>();
        }
        return departmentHashMap;
    }

    private void combinationsSearch(Department currentDepartment, Department targetDepartment) {
        boolean flag = false;

        for (int mask = 0; mask < (1 << currentDepartment.getEmployeesList().size()); mask++) {
            Transfer transfer = new Transfer();
            transfer.setCurrentDepartment(currentDepartment);
            transfer.setTargetDepartment(targetDepartment);

            for (int i = 0; i < currentDepartment.getEmployeesList().size(); i++) {
                if ((mask & (1 << i)) != 0) {
                    transfer.employeesToTransfer.add(currentDepartment.getEmployeesList().get(i));
                    flag = true;
                }
            }
            if (transfer.employeesToTransfer.size() != currentDepartment.getEmployeesList().size()
                    && flag && currentDeptFilter(transfer)) {
                    transferLinkedList.add(transfer);
            }
        }
    }

    public boolean currentDeptFilter (Transfer transfer) {
        BigDecimal combinationTotalSalary = new BigDecimal("0");

        for (Employee employee : transfer.employeesToTransfer) {
            combinationTotalSalary = combinationTotalSalary.add(employee.getSalary());
        }
        return (transfer.getCurrentDepartment().getAverageSalary()
                .compareTo(transfer.getCurrentDepartment().getTotalSalary()
                        .subtract(combinationTotalSalary)
                        .divide(new BigDecimal(
                        transfer.getCurrentDepartment().getEmployeesList().size() -
                        transfer.employeesToTransfer.size()), RoundingMode.HALF_UP)) < 0
                && transfer.getTargetDepartment().getAverageSalary()
                .compareTo(transfer.getCurrentDepartment().getTotalSalary()
                        .add(combinationTotalSalary)
                        .divide(new BigDecimal(
                        transfer.getCurrentDepartment().getEmployeesList().size() +
                                transfer.employeesToTransfer.size()), RoundingMode.HALF_UP)) < 0);
    }
}
