package com.example.huenoh_mapd711_assignment3;

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private String category;
    private String description;


    public Product() {
    }

    public Product(int productId, String productName, double price, int quantity, String category, String description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
    }

    // Getters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() { return description; }


    // Setters
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) { this.description = description; }

    // To send the object between activities.
    public static class ProductChild implements Serializable {

        public ProductChild() {}
    }
}
