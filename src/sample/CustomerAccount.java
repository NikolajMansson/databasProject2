package sample;

import java.io.Serializable;

public class CustomerAccount extends Account implements Serializable {

    public CustomerAccount(String userName) {
        super ( userName );

    }

    public CustomerAccount(String userName, String password) {
        super ( userName );
        setPassword ( password );

    }

}