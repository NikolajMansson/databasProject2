package sample;

/**
 * Created by Nikolaj on 2017-04-25.
 */
public class Person {
    private int SSN;

    private String firstName;

    private String surname;

    private Account account;

    public Person(){

    }
    public Person(int SSN, String firstName, String surname, Account account){
        setSSN(SSN);
        setFirstName ( firstName );
        setSurname ( surname );
        setAccount(account);
        this.SSN = SSN;
        this.firstName = firstName;
        this.surname = surname;
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
