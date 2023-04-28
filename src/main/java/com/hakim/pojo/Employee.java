package com.hakim.pojo;

public class Employee extends Person{
    private int salary;
    public Employee(String name, Address address, Contact contact, String pin, String role,int salary) {
        super(name, address, contact, pin, role);
        this.salary=salary;
    }
    public Employee(){}

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
