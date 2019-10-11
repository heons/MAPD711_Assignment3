package com.example.huenoh_mapd711_assignment3;

public class Customer {

    private int customerId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String postalCode;

    public Customer() {
    }

    public Customer(int customerId, String userName, String password) {
        this.customerId = customerId;
        this.userName = userName;
        this.password = password;
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.city = "";
        this.postalCode = "";
    }


    // Getters
    public int getCustomerId() {
        return customerId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }


    // Setters

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
