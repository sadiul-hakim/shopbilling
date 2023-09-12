package com.hakim.pojo;

public class TransactionHistory {
    private int amount;
    private String date;

    public TransactionHistory(int amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    public TransactionHistory() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
