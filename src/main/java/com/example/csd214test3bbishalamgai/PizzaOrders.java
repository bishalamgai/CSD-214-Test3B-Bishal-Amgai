package com.example.csd214test3bbishalamgai;

import javafx.fxml.FXML;

public class PizzaOrders {
    @FXML
    private int id;
    @FXML
    private String customer_name;
    @FXML
    private String mobile_number;
    @FXML
    private String pizza_size;
    @FXML
    private int number_of_toppings;
    @FXML
    private int total_bill;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getPizza_size() {
        return pizza_size;
    }

    public void setPizza_size(String pizza_size) {
        this.pizza_size = pizza_size;
    }

    public int getNumber_of_toppings() {
        return number_of_toppings;
    }

    public void setNumber_of_toppings(int number_of_toppings) {
        this.number_of_toppings = number_of_toppings;
    }

    public int getTotal_bill() {
        return total_bill;
    }

    public void setTotal_bill(int total_bill) {
        this.total_bill = total_bill;
    }

    public PizzaOrders(int id, String customer_name, String mobile_number, String pizza_size, int number_of_toppings, int total_bill) {
        this.id = id;
        this.customer_name = customer_name;
        this.mobile_number = mobile_number;
        this.pizza_size = pizza_size;
        this.number_of_toppings = number_of_toppings;
        this.total_bill = total_bill;
    }
}
