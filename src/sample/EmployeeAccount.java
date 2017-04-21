package sample;

import java.io.Serializable;

public class EmployeeAccount extends Account implements Serializable {
    private static int SSN;
    private String firstName;
    private String surName;
    private String employmentDate;
    private static int gamesSold;
    private static double income;
    private String email;
    private String userName;
    private String password;

    public EmployeeAccount(String userName, String password) {
        super (userName, password );
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return userName + " | " + password;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public static int getGamesSold() {
        return gamesSold;
    }

    public static void setGamesSold(int gamesSold) {
        EmployeeAccount.gamesSold = gamesSold;
    }

    public static double getIncome() {
        return income;
    }

    public static void setIncome(double income) {
        EmployeeAccount.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}