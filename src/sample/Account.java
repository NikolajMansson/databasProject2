package sample;

import java.io.Serializable;

public abstract class Account implements Serializable{

    private int SSN;

    private String firstName;

    private String surName;

    private String id;

    private String password;

    public Account(int SSN, String firstName, String surName, String id, String password) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.surName = surName;
        this.id = id;
        this.password = password;
    }

    public void setAccount(String id, String password) {
        this.id = id;
        this.password = password;
    }
    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }
    public void setSurName(String surName) {
        this.surName=surName;
    }
    public void setPassword(String password) {
        this.password=password;
    }
    public int getSSN(){
        return SSN;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getSurName(){
        return surName;
    }
    public String getId() {
        return id;
    }
    public String getPassword() {
       return password;
    }

}