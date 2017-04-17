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


    private final static sample.EmployeeAccount instance = new sample.EmployeeAccount ( SSN, "", "", "", "", "", gamesSold, income, "" );

    public static sample.EmployeeAccount getInstance() {
        return instance;
    }



    public EmployeeAccount(int SSN, String firstName, String surName, String id, String password, String employmentDate, int gamesSold, double income, String email) {
        super ( SSN, firstName, surName, id, password );
        this.SSN = SSN;
        this.firstName = firstName;
        this.surName= surName;
        this.id = id;
        this.password = password;
        this.employmentDate = employmentDate;
        this.gamesSold = gamesSold;
        this.income = income;
        this.email = email;
    }

    @Override
    public String toString() {
        return id + " | " + password;
    }

    public String getId() {
        return id;
    }

    public void setId(String username) {
        this.id = username;
    }

    public String getPassword(String password) {

        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }


}