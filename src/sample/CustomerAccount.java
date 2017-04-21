package sample;

import java.io.Serializable;

public class CustomerAccount extends Account implements Serializable {
    private String userID;
    private String password;
    private String registrationDate;

    public CustomerAccount(String id, String password) {
        super ( id, password );
        this.userID = id;
        this.password = password;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getregistrationDate() {
        return registrationDate;
    }


}