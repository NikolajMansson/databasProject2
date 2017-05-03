package sample;

import java.io.Serializable;

public class EmployeeAccount extends Account implements Serializable {


    public EmployeeAccount(String userName) {
        super ( userName );

    }

    public EmployeeAccount(String userName, String password) {
        super ( userName );
        this.setPassword ( password );

    }
}





