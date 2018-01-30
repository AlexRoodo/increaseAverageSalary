package ru.tsconsulting.Objects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

public class Transfer implements Comparable<Transfer>{
    private LinkedList<Employee> employeesToTransfer = new LinkedList<>();
    private Department currentDepartment = null;
    private Department targetDepartment = null;
    private BigDecimal newCurrentDepartmentAvgSalary =
            new BigDecimal("0").setScale(2,RoundingMode.HALF_UP);
    private BigDecimal newTargetDepartmentAvgSalary =
            new BigDecimal("0").setScale(2,RoundingMode.HALF_UP);
    private BigDecimal combinationTotalSalary = new BigDecimal("0");

    public LinkedList<Employee> getEmployeesToTransfer() {
        if (employeesToTransfer == null) {
            employeesToTransfer = new LinkedList<>();
        }
        return employeesToTransfer;
    }

    public Department getCurrentDepartment() {
        return currentDepartment;
    }

    public void setCurrentDepartment(Department currentDepartment) {
        this.currentDepartment = currentDepartment;
    }

    public Department getTargetDepartment() {
        return targetDepartment;
    }

    public void setTargetDepartment(Department targetDepartment) {
        this.targetDepartment = targetDepartment;
    }

    public BigDecimal getNewCurrentDepartmentAvgSalary() {
        if (newCurrentDepartmentAvgSalary.compareTo(new BigDecimal("0")) == 0) {
            newCurrentDepartmentAvgSalary = getCurrentDepartment().getTotalSalary()
                    .subtract(getCombinationTotalSalary())
                    .divide(new BigDecimal(getCurrentDepartment().getEmployeesList().size() -
                            getEmployeesToTransfer().size()), RoundingMode.HALF_UP);
        }
        return newCurrentDepartmentAvgSalary;
    }

    public void setNewCurrentDepartmentAvgSalary(BigDecimal newCurrentDepartmentAvgSalary) {
        this.newCurrentDepartmentAvgSalary = newCurrentDepartmentAvgSalary;
    }

    public BigDecimal getNewTargetDepartmentAvgSalary() {
        if (newTargetDepartmentAvgSalary.compareTo(new BigDecimal("0")) == 0) {
            newTargetDepartmentAvgSalary = getTargetDepartment().getTotalSalary()
                    .add(getCombinationTotalSalary())
                    .divide(new BigDecimal(getTargetDepartment().getEmployeesList().size() +
                            getEmployeesToTransfer().size()), RoundingMode.HALF_UP);
        }
        return newTargetDepartmentAvgSalary;
    }

    public void setNewTargetDepartmentAvgSalary(BigDecimal newTargetDepartmentAvgSalary) {
        this.newTargetDepartmentAvgSalary = newTargetDepartmentAvgSalary;
    }

    public BigDecimal getCombinationTotalSalary() {
        if (combinationTotalSalary.compareTo(new BigDecimal("0")) == 0) {
            for (Employee employee : getEmployeesToTransfer()) {
                combinationTotalSalary = combinationTotalSalary.add(employee.getSalary());
            }
        }
        return combinationTotalSalary;
    }

    public void setCombinationTotalSalary(BigDecimal combinationTotalSalary) {
        this.combinationTotalSalary = combinationTotalSalary;
    }

    @Override
    public int compareTo(Transfer o) {
        if (this.currentDepartment.getName().equalsIgnoreCase(o.getCurrentDepartment().getName())) {
            if (this.targetDepartment.getName().equalsIgnoreCase(o.getTargetDepartment()
                    .getName())) {
                if (this.employeesToTransfer.size() == o.getEmployeesToTransfer().size()) {
                    for (int i = 0; i < this.employeesToTransfer.size(); i++) {
                        if (this.employeesToTransfer.get(i)
                                .compareTo(o.getEmployeesToTransfer().get(i)) != 0) {
                            return this.employeesToTransfer.get(i)
                                    .compareTo(o.getEmployeesToTransfer().get(i));
                        }

                    }
                }

                return this.employeesToTransfer.size() - o.getEmployeesToTransfer().size();
            }

            return this.targetDepartment.getName().compareTo(o.getTargetDepartment().getName());
        }
        return 0;
    }
}
