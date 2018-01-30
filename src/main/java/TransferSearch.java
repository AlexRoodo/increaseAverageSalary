import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

class TransferSearch {
    private HashMap<String, Department> departmentHashMap = null;
    private LinkedList<Transfer> transferLinkedList = null;

    public void searchForTransfers() {
        getTransferLinkedList();

        for (Department currentDepartment : departmentHashMap.values()) {
            for (Department targetDepartment : departmentHashMap.values()) {
                if (!currentDepartment.getName().equalsIgnoreCase(targetDepartment.getName())) {
                    combinationsSearch(currentDepartment, targetDepartment);
                }

            }
        }

        transferLinkedList.sort(Transfer::compareTo);
    }

    public HashMap<String, Department> getDepartmentHashMap() {
        if (departmentHashMap == null) {
            departmentHashMap = new HashMap<>();
        }
        return departmentHashMap;
    }

    public LinkedList<Transfer> getTransferLinkedList() {
        if (transferLinkedList == null) {
            transferLinkedList = new LinkedList<>();
        }
        return transferLinkedList;
    }

    private void combinationsSearch(Department currentDepartment, Department targetDepartment) {
        boolean flag = false;

        for (int mask = 0; mask < (1 << currentDepartment.getEmployeesList().size()); mask++) {
            Transfer transfer = new Transfer();
            transfer.setCurrentDepartment(currentDepartment);
            transfer.setTargetDepartment(targetDepartment);

            for (int i = 0; i < currentDepartment.getEmployeesList().size(); i++) {
                if ((mask & (1 << i)) != 0) {
                    transfer.getEmployeesToTransfer().add(currentDepartment.getEmployeesList().get(i));
                    flag = true;
                }
            }
            if (transfer.getEmployeesToTransfer().size() != currentDepartment.getEmployeesList().size()
                    && flag && currentDeptFilter(transfer)) {
                    transferLinkedList.add(transfer);
            }
        }
    }

    private boolean currentDeptFilter (Transfer transfer) {
        BigDecimal combinationTotalSalary = new BigDecimal("0");

        for (Employee employee : transfer.getEmployeesToTransfer()) {
            combinationTotalSalary = combinationTotalSalary.add(employee.getSalary());
        }
        return (transfer.getCurrentDepartment().getAverageSalary()
                .compareTo(transfer.getCurrentDepartment().getTotalSalary()
                        .subtract(combinationTotalSalary)
                        .divide(new BigDecimal(
                        transfer.getCurrentDepartment().getEmployeesList().size() -
                        transfer.getEmployeesToTransfer().size()), RoundingMode.HALF_UP)) < 0
                && transfer.getTargetDepartment().getAverageSalary()
                .compareTo(transfer.getCurrentDepartment().getTotalSalary()
                        .add(combinationTotalSalary)
                        .divide(new BigDecimal(
                        transfer.getCurrentDepartment().getEmployeesList().size() +
                                transfer.getEmployeesToTransfer().size()), RoundingMode.HALF_UP)) < 0);
    }
}
