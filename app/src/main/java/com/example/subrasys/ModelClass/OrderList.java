package com.example.subrasys.ModelClass;

public class OrderList {

    int orderno;
    String customar_name;
    String date;
    int total_amount;

    public OrderList(int orderno, String date, int total_amount) {
        this.orderno = orderno;
        this.date = date;
        this.total_amount = total_amount;
    }
    public OrderList(int orderno,int total_amount) {
        this.orderno = orderno;
        this.total_amount = total_amount;
    }
    public OrderList(int orderno,int total_amount,String customar_name,   String date) {
        this.orderno = orderno;
        this.total_amount = total_amount;
        this.customar_name=customar_name;
        this.date=date;
    }

    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    public String getCustomar_name() {
        return customar_name;
    }

    public void setCustomar_name(String customar_name) {
        this.customar_name = customar_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }
}
