package com.hakim.pojo;

public class SellHistory extends TransactionHistory{
    private String seller_name;
    private int profit;
    public SellHistory(int amount, String date,int profit,String seller_name) {
      super(amount,date);
      this.profit=profit;
      this.seller_name=seller_name;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
