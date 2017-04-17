package sample;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 */
public class CustomerAccount extends Account implements Serializable {


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


}