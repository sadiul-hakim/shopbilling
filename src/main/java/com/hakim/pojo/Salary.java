package com.hakim.pojo;

import java.time.LocalDateTime;

public class Salary {
    private String employee_name;
    private int salary;
    private String given_date= LocalDateTime.now().toString();

    public Salary() {
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getGiven_date() {
        return given_date;
    }

    public void setGiven_date(String given_date) {
        this.given_date = given_date;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "employee_name='" + employee_name + '\'' +
                ", salary='" + salary + '\'' +
                ", date='" + given_date + '\'' +
                '}';
    }
}
