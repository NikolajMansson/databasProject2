package sample;

import java.io.Serializable;

/**
 *
 */
public class BossAccount extends EmployeeAccount implements Serializable {

    public BossAccount(String userName) {
        super ( userName );
    }

    public BossAccount(String userName, String password) {
        super ( userName );
        setPassword ( password );
    }


}