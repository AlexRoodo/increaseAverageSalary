import java.util.ArrayList;

public class Department {
    private String departmentName;
    private double totalSalary = 0;
    private int employeesAmount = 0;
    private double averageSalary = 0;
    ArrayList<Employee> employeesList;

    Department(String departmentName) {
        this.departmentName = departmentName;
        employeesList = new ArrayList<Employee>();
    }

    String getDepartmentName() {
        return departmentName;
    }

    double getAverageSalary() {
        return averageSalary;
    }

    double getEmployeesAmount() { return employeesAmount; }

    void increaseEmployeesAmount() { this.employeesAmount += 1; }

    double getTotalSalary() { return totalSalary; }

    void increaseTotalSalary(double totalSalary) { this.totalSalary += totalSalary; }

    void calculateAverageSalary() {
        int count = 0;

        for (Employee employee : employeesList) {
            averageSalary += employee.getSalary();
            count++;
        }

        averageSalary /= count;
    }

}
