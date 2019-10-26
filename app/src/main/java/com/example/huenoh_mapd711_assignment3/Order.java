package com.example.huenoh_mapd711_assignment3;

import java.io.Serializable;

public class Order implements Serializable {
    private int orderId;
    private int customerId;
    private int productId;
    private int employeeId;
    private int quantity;
    private String orderDate;
    private String status;

    public Order() {
    }

    public Order(int orderId, int customerId, int productId, int employeeId, int quantity, String orderDate, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.employeeId = employeeId;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getQuantity() { return quantity; }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // To send the object between activities.
    public static class OrderChild implements Serializable {

        public OrderChild() {}
    }
}
