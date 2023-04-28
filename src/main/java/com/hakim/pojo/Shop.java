package com.hakim.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Serializable {
    private String name="Hakim-Shop";
    private int capital=5_00_000;
    private int total_product=40_000;
    private Address location;
    private Owner owner;
    private List<Employee> employees=new ArrayList<>();
    private List<Salary> given_salary=new ArrayList<>();
    private Transaction transaction;

    public Shop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Salary> getGiven_salary() {
        return given_salary;
    }

    public void setGiven_salary(List<Salary> given_salary) {
        this.given_salary = given_salary;
    }

    public int getTotal_product() {
        return total_product;
    }

    public void setTotal_product(int total_product) {
        this.total_product = total_product;
    }
    public void buyProduct(int amount){
        total_product+=amount;
    }
    public void sellProduct(int amount){
        total_product-=amount;
    }
    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", capital=" + capital +
                ", total_product=" + total_product +
                ", location=" + location +
                ", owner=" + owner +
                ", employees=" + employees +
                ", given_salary=" + given_salary +
                ", transaction=" + transaction +
                '}';
    }
}
