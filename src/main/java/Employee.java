import java.math.BigDecimal;
import java.math.RoundingMode;

class Employee {
    private String name;
    private BigDecimal salary = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);

    public Employee(String name, BigDecimal salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}