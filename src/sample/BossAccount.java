package sample;

import java.util.ArrayList;

/**
 * 
 */
public class BossAccount extends EmployeeAccount {


    public BossAccount(int SSN, String firstName, String surName, String id, String password, String employmentDate, int gamesSold, double income, String email) {
        super ( SSN, firstName, surName, id, password, employmentDate, gamesSold, income, email );
    }

    public void BossAccount() {
        // TODO implement here
    }


    public ArrayList<BossAccount> getBossArray() {
        // TODO implement here
        return null;
    }

}