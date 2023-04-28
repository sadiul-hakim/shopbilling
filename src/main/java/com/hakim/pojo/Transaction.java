package com.hakim.pojo;

import java.time.LocalDateTime;

public class Transaction {
    private int totalIncome;
    private int totalCost;
    private String workday= LocalDateTime.now().toString();

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getWorkday() {
        return workday;
    }

    public void setWorkday(String workday) {
        this.workday = workday;
    }

    public void addCost(int amount){
        totalCost+=amount;
    }

    public void addIncome(int amount){
        totalIncome+=amount;
    }

    public void updateDate(){
        workday=LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "totalIncome=" + totalIncome +
                ", totalCost=" + totalCost +
                ", workday='" + workday + '\'' +
                '}';
    }
}
