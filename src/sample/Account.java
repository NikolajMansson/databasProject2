package sample;

import java.io.Serializable;

public abstract class Account implements Serializable {

    private int SSN;

    private String firstName;

    private String surName;

    private String userName;

    private String password;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSSN() {
        return SSN;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}