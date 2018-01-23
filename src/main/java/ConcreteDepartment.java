import java.util.ArrayList;

public class ConcreteDepartment extends Department {
    private String departmentName;
    private double averageSalary = 0;
    ArrayList<Employee> employeesList;

    public ConcreteDepartment(String departmentName) {
        this.departmentName = departmentName;
        employeesList = new ArrayList<Employee>();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void calculateAverageSalary() {
        int count = 0;

        for (Employee employee : employeesList) {
            averageSalary += employee.getSalary();
            count++;
        }

        averageSalary /= count;
    }
}
