import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

class Department {
    private String departmentName;
    private BigDecimal totalSalary = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
    private BigDecimal employeesAmount = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
    private BigDecimal averageSalary = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
    ArrayList<Employee> employeesList;

    Department(String departmentName) {
        this.departmentName = departmentName;
        employeesList = new ArrayList<>();
    }

    String getDepartmentName() {
        return departmentName;
    }

    BigDecimal getAverageSalary() {
        return averageSalary;
    }

    BigDecimal getEmployeesAmount() {
        return employeesAmount;
    }

    void increaseEmployeesAmount() {
        this.employeesAmount = this.employeesAmount.add(new BigDecimal("1"));
    }

    BigDecimal getTotalSalary() {
        return totalSalary;
    }

    void increaseTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = this.totalSalary.add(totalSalary) ;
    }

    void calculateAverageSalary() {
        averageSalary = totalSalary.divide(employeesAmount, RoundingMode.HALF_UP);
    }

}
