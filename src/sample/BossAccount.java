package sample;

import java.io.Serializable;

/**
 *
 */
public class BossAccount extends EmployeeAccount implements Serializable {

    public BossAccount(String userName, String password) {
        super (userName, password);
    }
}