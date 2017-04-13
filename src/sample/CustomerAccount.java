package sample;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */
public class CustomerAccount extends Account {


    public CustomerAccount(int SSN, String firstName, String surName, String id, String password) {
        super ( SSN, firstName, surName, id, password );
    }

    public void CustomerAccount() {
        // TODO implement here
    }

    public void searchForGameInDB(Game game) {
        // TODO implement here
    }
    public Date registrationDate() {
        // TODO implement here
        return null;
    }
    public ArrayList<CustomerAccount> getCustomerArray() {
        // TODO implement here
        return null;
    }

}