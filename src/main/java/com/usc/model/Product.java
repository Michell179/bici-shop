package com.usc.model;

import lombok.Setter;

public class Product {
    private int id;
    private String name;
    private double price;
    @Setter
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return name + " - $" + price + " (" + quantity + " en stock)";
    }
}
