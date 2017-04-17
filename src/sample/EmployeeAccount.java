package sample;

import java.io.Serializable;

public class EmployeeAccount extends Account implements Serializable{
    private static int SSN;
    private String firstName;
    private String surName;
    private String employmentDate;
    private static int gamesSold;
    private static double income;
    private String email;
    private String id;
    private String password;


    private final static sample.EmployeeAccount instance = new sample.EmployeeAccount ( "", "" );

    public static sample.EmployeeAccount getInstance() {
        return instance;
    }

    public EmployeeAccount(String id, String password) {
        super (id, password );
        this.id = id;
        this.password = password;
    }

    @Override
    public String toString() {
        return id + " | " + password;
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