package com.hakim.utils;

import com.hakim.pojo.*;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Printer {
    public static void showPersonDetails(Person person) {
        System.out.println("Name : " + person.getName());
        System.out.println("Address : ");
        System.out.println("- City : " + person.getAddress().getCity());
        System.out.println("- District : " + person.getAddress().getDistrict());
        System.out.println("- Sub District : " + person.getAddress().getSub_district());
        System.out.println("- Zip : " + person.getAddress().getZip());

        System.out.println("Contact : ");
        System.out.println("- Phone : ");
        for (String ph : person.getContact().getPhone()) {
            System.out.println("- - " + ph);
        }
        System.out.println("- Email : " + person.getContact().getEmail());
        System.out.println("-------------------End----------------------");
    }

    public static void showSalaryDetails(Shop shop) {
        System.out.println("-------Employee salary details-------");
        for(Salary salary:shop.getGiven_salary()){
            System.out.println("Employee: "+salary.getEmployee_name());
            System.out.println("Salary: "+salary.getSalary());
            System.out.println("Given Date: "+salary.getGiven_date());
            System.out.println("--------------------------------");
        }
    }

    public static void showCapital(Shop shop){
        int capital= shop.getCapital();
        int totalCost=shop.getTransaction().getTotalCost();
        int totalProfit=shop.getTransaction().getTotalCost();
        int totalIncome=shop.getTransaction().getTotalIncome();
        int endProduct=shop.getTotal_product();

        int endCapital=(capital+totalIncome+totalProfit+endProduct)-totalCost;

        System.out.println("On "+ LocalDate.now()+" total capital stands to : "+endCapital+" Taka.");
    }
    public static void showProduct(Shop shop){
        System.out.println("On "+ LocalDate.now()+" total available product amount : "+shop.getTotal_product()+" Taka.");
        System.out.println("------------------------");
        showBuyAndSellHistory(shop);
    }
    private static void showBuyAndSellHistory(Shop shop){
        System.out.println("Total purchasing: ");
        System.out.println("=================");
        for(BuyHistory buyHistory:shop.getTransaction().getBuyHistory()){
            System.out.println("Date : "+buyHistory.getDate());
            System.out.println("Amount : "+buyHistory.getAmount());
            System.out.println("-------------------------");
        }
        System.out.println("------------------------------------------");
        System.out.println("Total Selling: ");
        System.out.println("=================");
        for(SellHistory sellHistory:shop.getTransaction().getSellHistory()){
            System.out.println("Date : "+sellHistory.getDate());
            System.out.println("Amount : "+sellHistory.getAmount());
            System.out.println("Profit : "+sellHistory.getProfit());
            System.out.println("Seller : "+sellHistory.getSeller_name());
            System.out.println("-------------------------");
        }
        System.out.println("------------------------------------------");
    }
    public static void showStatistics(Shop shop){
        System.out.println("+--------------------+--------------------+");
        System.out.println("+==========✨"+shop.getName()+"✨==========+");
        System.out.println("-------------------------------------------");
        System.out.println("Owner : ");
        System.out.println("====== ");
        System.out.println("Name: "+shop.getOwner().getName());
        System.out.println("Address: "+getAddressString(shop.getOwner().getAddress()));
        System.out.println("-------------------------------------------");
        System.out.println("Location: ");
        System.out.println("========");
        System.out.println("- City: "+shop.getLocation().getCity());
        System.out.println("- District: "+shop.getLocation().getDistrict());
        System.out.println("- Sub-District: "+shop.getLocation().getSub_district());
        System.out.println("- Zip: "+shop.getLocation().getZip());
        System.out.println("-------------------------------------------");
        System.out.println("Employees: ");
        System.out.println("=========");
        for(Employee employee:shop.getEmployees()){
            System.out.println("Name: "+employee.getName());
            System.out.println("Address : "+getAddressString(employee.getAddress()));
            System.out.println("Salary: "+employee.getSalary());
            System.out.println("+-----------+----------+");
        }
        System.out.println("-------------------------------------------");
        showBuyAndSellHistory(shop);
        System.out.println("Available Product: "+shop.getTotal_product());
        System.out.println("-------------------------------------------");
        System.out.println("Economic Information: ");
        System.out.println("=====================");
        System.out.println("Initial Capital: "+shop.getCapital());
        System.out.println("Total Income: "+shop.getTransaction().getTotalIncome());
        System.out.println("Total Cost: "+shop.getTransaction().getTotalCost());
        System.out.println("Total Profit : "+shop.getTransaction().getProfit());
        System.out.println("Total Given Salary: "+OwnerOption.totalGivenSalary(shop)+" (counted in the total-cost).");
        showCapital(shop);
        System.out.println("+--------------------+--------------------+");
    }
    private static String getAddressString(Address address){
        StringJoiner joiner=new StringJoiner(",");
        joiner.add(address.getCity());
        joiner.add(address.getDistrict());
        joiner.add(address.getSub_district());
        joiner.add(String.valueOf(address.getZip()));

        return joiner.toString();
    }
}
