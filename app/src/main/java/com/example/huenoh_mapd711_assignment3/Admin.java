package com.example.huenoh_mapd711_assignment3;

public class Admin {
    private int employeeId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;


    public Admin() {
    }

    public Admin(int employeeId, String userName, String password, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    // Getters
    public int getEmployeeId() {
        return employeeId;
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


    // Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
}
