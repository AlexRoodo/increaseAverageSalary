package ru.tsconsulting.Logic;

import ru.tsconsulting.Objects.Department;
import ru.tsconsulting.Objects.Employee;
import ru.tsconsulting.Objects.Transfer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class TransferSearch {
    private HashMap<String, Department> departmentHashMap = null;
    private LinkedList<Transfer> transferLinkedList = null;

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

    public void searchForTransfers() {
        getTransferLinkedList();

        for (Department currentDepartment : getDepartmentHashMap().values()) {
            for (Department targetDepartment : getDepartmentHashMap().values()) {
                if (!currentDepartment.getName().equalsIgnoreCase(targetDepartment.getName())) {
                    combinationsSearch(currentDepartment, targetDepartment);
                }

            }
        }

        getTransferLinkedList().sort(Transfer::compareTo);
    }

    private void combinationsSearch(Department currentDepartment, Department targetDepartment) {
        boolean isCombinationFound = false;

        for (int mask = 0; mask < (1 << currentDepartment.getEmployeesList().size()); mask++) {
            Transfer transfer = new Transfer();
            transfer.setCurrentDepartment(currentDepartment);
            transfer.setTargetDepartment(targetDepartment);

            for (int i = 0; i < currentDepartment.getEmployeesList().size(); i++) {
                if ((mask & (1 << i)) != 0) {
                    transfer.getEmployeesToTransfer().add(currentDepartment.getEmployeesList().get(i));
                    isCombinationFound = true;
                }
            }
            if (transfer.getEmployeesToTransfer().size() !=
                    currentDepartment.getEmployeesList().size()
                    && isCombinationFound && transferFilter(transfer)) {
                    getTransferLinkedList().add(transfer);
            }
        }
    }

    private boolean transferFilter(Transfer transfer) {
        BigDecimal combinationTotalSalary = new BigDecimal("0");

        for (Employee employee : transfer.getEmployeesToTransfer()) {
            combinationTotalSalary = combinationTotalSalary.add(employee.getSalary());
        }
        return (transfer.getCurrentDepartment().getAverageSalary()
                .compareTo(transfer.getNewCurrentDepartmentAvgSalary()) < 0
                && transfer.getTargetDepartment().getAverageSalary()
                .compareTo(transfer.getNewTargetDepartmentAvgSalary()) < 0);
    }
}
