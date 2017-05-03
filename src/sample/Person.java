package sample;

/**
 * Created by Nikolaj on 2017-04-25.
 */
public abstract class Person {
    private int SSN = 0;

    private String firstName = null;

    private String surname = null;


    public Person() {

    }

    public Person(int SSN, String firstName, String surname) {
        setSSN ( SSN );
        setFirstName ( firstName );
        setSurname ( surname );
    }

    public void setSSN(int SSN) {

        this.SSN = SSN;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setSurname(String surName) {

        this.surname = surName;
    }

    public int getSSN() {
        return SSN;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getSurname() {

        return surname;
    }

}
