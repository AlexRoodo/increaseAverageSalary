import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

class Department {
    private String departmentName;
    private ArrayList<Employee> employeesList;

    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String s) {
        this.departmentName = s;
    }

    public BigDecimal getAverageSalary() {
        return getTotalSalary().divide(new BigDecimal(employeesList.size()), RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalSalary() {
        BigDecimal totalSalary = new BigDecimal(0);
        for (Employee emp : employeesList) {
            totalSalary = totalSalary.add(emp.getSalary());
        }
        return totalSalary;
    }

    public ArrayList<Employee> getEmployeesList() {
        if (employeesList == null) {
            employeesList = new ArrayList<>();
        }
        return employeesList;
    }
}
