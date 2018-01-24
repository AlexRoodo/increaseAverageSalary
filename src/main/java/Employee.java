import java.math.BigDecimal;
import java.math.RoundingMode;

class Employee {
    private String name;
    private BigDecimal salary = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
    private String department;
    StringBuilder targetDepatments = new StringBuilder();

    Employee(String name, BigDecimal salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    String getName() {
        return name;
    }

    BigDecimal getSalary() {
        return salary;
    }

}