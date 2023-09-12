package com.hakim.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private int totalIncome;
    private int totalCost;
    private int profit;
    private String workday= LocalDateTime.now().toString();
    private List<BuyHistory> buyHistory=new ArrayList<>();
    private List<SellHistory> sellHistory=new ArrayList<>();

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

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public List<BuyHistory> getBuyHistory() {
        return buyHistory;
    }

    public void setBuyHistory(List<BuyHistory> buyHistory) {
        this.buyHistory = buyHistory;
    }

    public List<SellHistory> getSellHistory() {
        return sellHistory;
    }

    public void setSellHistory(List<SellHistory> sellHistory) {
        this.sellHistory = sellHistory;
    }

    public void addProfit(int profit) {
        this.profit += profit;
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
    public void clearData(){
        totalIncome=0;
        totalCost=0;
        profit=0;
        workday=LocalDateTime.now().toString();
    }
    public void clearHistory(){
        buyHistory=new ArrayList<>();
        sellHistory=new ArrayList<>();
    }
    public void addBuyHistory(BuyHistory history){
        buyHistory.add(history);
    }
    public void addSellHistory(SellHistory history){
        sellHistory.add(history);
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "totalIncome=" + totalIncome +
                ", totalCost=" + totalCost +
                ", profit=" + profit +
                ", workday='" + workday + '\'' +
                ", buyHistory=" + buyHistory +
                ", sellHistory=" + sellHistory +
                '}';
    }
}
