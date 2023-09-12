package com.hakim;

import com.hakim.pojo.*;
import com.hakim.repository.ShopRepository;
import com.hakim.resource.Art;
import com.hakim.utils.OwnerOption;
import com.hakim.utils.Printer;
import com.hakim.utils.Security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class App {
    private final ShopRepository shopRepository = new ShopRepository();
    private static final Scanner input = new Scanner(System.in);

    public App() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.auth();
    }

    /**
     * This method is for authentication.
     */
    private void auth() throws IOException {
        Shop shop = shopRepository.getShop();
        System.out.println(Art.LOGO_THREE);
        System.out.println();
        System.out.println("Choose an Option :");
        System.out.println("1. Owner (" + shop.getOwner().getName() + ")");
        System.out.println("2. Employee.");
        System.out.print(": ");
        int choice = input.nextInt();
        if (choice > shop.getEmployees().size() + 1) {
            System.out.println("** Invalid Option.");
        } else if (choice == 1) {
            System.out.println("Please enter your PIN : ");
            String pin = input.next();
            if (pin.equals(shop.getOwner().getPin())) {
                ownerOptions();
            } else {
                System.out.println("** Wrong Credentials!");
            }
        } else if (choice == 2) {
            System.out.println("Enter your name : ");
            String name = input.next();
            List<Employee> employeeList = shop.getEmployees().stream().filter(emp -> emp.getName().equals(name)).toList();
            if (employeeList.isEmpty()) {
                System.out.println("** Wrong Credentials!");
            } else {
                System.out.println("Enter PIN : ");
                String pin = input.next();
                if (!employeeList.get(0).getPin().equals(pin)) {
                    System.out.println("** Wrong Credentials!");
                } else {
                    employeeOptions(employeeList.get(0));
                }
            }
        }
    }

    /**
     * This method is for owners option.
     */
    private void ownerOptions() throws IOException {
        Shop shop = shopRepository.getShop();

        while (true) {
            System.out.println("Owner Options: ");
            System.out.println("1. Add Employee. ");
            System.out.println("2. Delete Employee. ");
            System.out.println("3. Employee List.");
            System.out.println("4. See Details.");
            System.out.println("5. Change Password.");
            System.out.println("6. Buy.");
            System.out.println("7. Lunch Salary.");
            System.out.println("8. Increase Salary.");
            System.out.println("9. Show Salary Details.");
            System.out.println("10. Show Capital Details.");
            System.out.println("11. Show Product Details.");
            System.out.println("12. Show Statistics.");
            System.out.println("13. Close Month Accounting.");
            System.out.println("14. exit.");
            System.out.print(": ");
            int option = input.nextInt();
            if (option == 1) {
                OwnerOption.createEmployee(shop);
                shopRepository.updateJson(shop);
                System.out.println("Employee added successfully!✨");
            } else if (option == 2) {
                System.out.println("Employee name : ");
                String name = input.next();
                OwnerOption.deleteEmployee(shop, name);
                shopRepository.updateJson(shop);
                System.out.println("Employee deleted successfully!✔");
            } else if (option == 3) {
                System.out.println("--------------Employee List-----------------");
                for (Employee emp : shop.getEmployees()) {
                    Printer.showPersonDetails(emp);
                }
            } else if (option == 4) {
                Owner owner = shop.getOwner();
                System.out.println("--------------Owner Details-----------------");
                Printer.showPersonDetails(owner);
            } else if (option == 5) {
                Security.changePassword(shop.getOwner());
                shopRepository.updateJson(shop);
                System.out.println("PIN updated successfully.");
            } else if (option == 6) {
                System.out.println("Enter Buy amount: ");
                int amount = input.nextInt();
                shop.buyProduct(amount);
                shop.getTransaction().addCost(amount);
                BuyHistory history = new BuyHistory(amount, LocalDateTime.now().toString());
                shop.getTransaction().addBuyHistory(history);
                shopRepository.updateJson(shop);
                System.out.println("Product bought successfully.\nProduct: " + shop.getTotal_product());
            } else if (option == 7) {
                OwnerOption.lunchSalary(shop);
                shopRepository.updateJson(shop);
                System.out.println("Salary Lunched successfully!");
            } else if (option == 8) {
                System.out.println("-----Salary Increase Option-----");
                System.out.println("Enter employee name : ");
                String name = input.next();
                OwnerOption.increaseEmployeeSalary(shop, name);
                shopRepository.updateJson(shop);
                System.out.println("Salary increased successfully!");
            } else if (option == 9) {
                Printer.showSalaryDetails(shop);
            } else if (option == 10) {
                Printer.showCapital(shop);
            } else if (option == 11) {
                Printer.showProduct(shop);
            } else if (option == 12) {
                Printer.showStatistics(shop);
            } else if (option == 13) {
                closeMonthAccount(shop);
                System.out.println("Month account closed.New Account started!");
            } else if (option == 14) {
                System.out.println("Bye.");
                break;
            }
        }
    }

    private void employeeOptions(Employee employee) throws IOException {
        Shop shop = shopRepository.getShop();
        while (true) {
            System.out.println("1. Change Password.");
            System.out.println("2. Sell.");
            System.out.println("3. close.");
            System.out.println("4. exit.");
            System.out.print(": ");

            int option = input.nextInt();
            if (option == 1) {
                Security.changePassword(employee);
                shopRepository.updateJson(shop);
                System.out.println("PIN updated successfully.");
            } else if (option == 2) {
                System.out.println("Product available: " + shop.getTotal_product());
                System.out.println("Enter Sell amount: ");
                int amount = input.nextInt();
                System.out.println("Enter profit amount: ");
                int profit = input.nextInt();
                shop.sellProduct(amount);
                shop.getTransaction().addIncome(amount);
                shop.getTransaction().addProfit(profit);
                SellHistory history = new SellHistory(amount, LocalDateTime.now().toString(), profit, employee.getName());
                shop.getTransaction().addSellHistory(history);
                shopRepository.updateJson(shop);
                System.out.println("Product Sold successfully.\nProduct: " + shop.getTotal_product());
            } else if (option == 3) {
                closeShop(shop);
                break;
            } else if (option == 4) {
                System.out.println("Bye.");
                break;
            }
        }
    }
    private void closeMonthAccount(Shop shop) throws IOException {
        Printer.showStatistics(shop);
        shop.getTransaction().clearData();
        shop.getTransaction().clearHistory();

        shopRepository.updateJson(shop);
    }
    private void closeShop(Shop shop) throws IOException {
        shop.getTransaction().updateDate();
        System.out.println("Shop closed.");
        System.out.println("Alhamdulillah for Everything.");

        shopRepository.updateJson(shop);
    }
}