package com.techelevator.Items;


import java.math.BigDecimal;

public class Item {
    private String name;
    private BigDecimal price;
    private int quantity;
    private String category;

    public Item(String name, BigDecimal price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    //getters
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }


    //setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
