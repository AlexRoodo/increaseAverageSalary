package ru.tsconsulting.increaseAverageSalary.Objects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Employee implements Comparable<Employee>{
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

    @Override
    public int compareTo(Employee o) {
        return this.getName().compareTo(o.getName());
    }
}