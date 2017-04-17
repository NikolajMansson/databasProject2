package sample;

import java.io.Serializable;

/**
 * 
 */
public class BossAccount extends EmployeeAccount implements Serializable {


    public BossAccount(int SSN, String firstName, String surName, String id, String password, String employmentDate, int gamesSold, double income, String email) {
        super ( SSN, firstName, surName, id, password, employmentDate, gamesSold, income, email );
    }

    public void BossAccount() {
        // TODO implement here
    }


}