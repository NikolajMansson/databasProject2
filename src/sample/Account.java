package sample;

public abstract class Account {


    private String id;

    private String password;

    private Integer SSN;

    private String firstName;

    private String surName;

    public Account(int SSN, String firstName, String surName, String id, String password) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.surName = surName;
        this.id = id;
        this.password = password;
    }

    public void getId() {
        // TODO implement here
    }
    public void getPassword() {
        // TODO implement here
    }

}