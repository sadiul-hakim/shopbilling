package com.hakim.utils;

import com.hakim.pojo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class OwnerOption {
    private static final Scanner input = new Scanner(System.in);
    public static void lunchSalary(Shop shop){
        List<Salary> salaryList=shop.getGiven_salary();
        for(Employee employee:shop.getEmployees()){
            Salary salary=new Salary();
            salary.setEmployee_name(employee.getName());
            salary.setSalary(employee.getSalary());

            salaryList.add(salary);
            shop.getTransaction().addCost(employee.getSalary());
        }
        shop.setGiven_salary(salaryList);
    }

    public static void createEmployee(Shop shop) {
        Employee employee=new Employee();

        System.out.println("-----Add a new Employee-----");

        System.out.println("Enter employee name (no-space): ");
        String name = input.next();
        employee.setName(name);

        System.out.println("Employee address : ");
        Address address=new Address();
        System.out.println("Enter employee address - city :");
        String city = input.next();
        address.setCity(city);
        System.out.println("Enter employee address - district :");
        address.setDistrict(input.next());
        System.out.println("Enter employee address - sub-district :");
        address.setSub_district(input.next());
        System.out.println("Enter employee address - zip :");
        address.setZip(input.nextInt());
        employee.setAddress(address);

        System.out.println("Employee contact : ");
        Contact contact=new Contact();
        System.out.println("Enter employee contact - phone :");
        contact.getPhone().add(input.next());
        System.out.println("Enter employee contact - email :");
        contact.setEmail(input.next());
        employee.setContact(contact);

        System.out.println("Give employee a pin : ");
        employee.setPin(input.next());

        System.out.println("Fix employee salary : ");
        employee.setSalary(input.nextInt());

        employee.setRole("Employee");
        shop.getEmployees().add(employee);
    }

    public static void deleteEmployee(Shop shop,String name) throws IOException {
        List<Employee> employeeList = shop.getEmployees().stream()
                .filter(employee -> !employee.getName().equals(name)).toList();
        shop.setEmployees(employeeList);
    }

    public static void showCapital(Shop shop){
        int capital= shop.getCapital();
        int totalCost=shop.getTransaction().getTotalCost();
        int totalIncome=shop.getTransaction().getTotalIncome();

        int endCapital=(capital+totalIncome)-totalCost;

        System.out.println("On "+ LocalDate.now()+" total capital stands to : "+endCapital+" Taka.");
    }
    public static int totalGivenSalary(Shop shop){
        int total=0;
        for(Salary salary:shop.getGiven_salary()){
            total+= salary.getSalary();
        }
        return total;
    }
}
