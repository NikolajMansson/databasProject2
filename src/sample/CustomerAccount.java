package sample;

import java.io.Serializable;
import java.util.Date;

public class CustomerAccount extends Account implements Serializable {
    private String id;
    private String password;

    public CustomerAccount(String id, String password) {
        super ( id, password );
        this.id = id;
        this.password = password;
    }

    public Date registrationDate() {
        // TODO implement here
        return null;
    }


}