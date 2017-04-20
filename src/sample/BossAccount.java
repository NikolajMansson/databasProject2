package sample;

import java.io.Serializable;

/**
 *
 */
public class BossAccount extends EmployeeAccount implements Serializable {


    public BossAccount(String id, String password) {
        super ( id, password );
    }


}