package sample;

import java.util.ArrayList;

public class EmployeeAccount extends Account {

    private String firstName;
    private String surName;
    private String employmentDate;
    private int gamesSold;
    private double income;
    private String email;

    public EmployeeAccount(int SSN, String firstName, String surName, String id, String password,  String employmentDate, int gamesSold, double income, String email) {
        super ( SSN, firstName, surName, id, password );
        this.employmentDate = employmentDate;
        this.gamesSold= gamesSold;
        this.income = income;
        this.email=email;
    }

    public void EmployeeAccount(String firstName, String surName, String employmentDate, int gamesSold, double income, String email) {
        // TODO implement here
    }

    public ArrayList<EmployeeAccount> getEmployeeArray() {
        // TODO implement here
        return null;
    }


}