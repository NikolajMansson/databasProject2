package sample;

import java.io.Serializable;

public abstract class Account implements Serializable  {



    private String userName;

    private String password;

    public Account(String userName, String password) {
        setUserName ( userName );
        setPassword ( password );
    }


    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassword(String password) {

        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}