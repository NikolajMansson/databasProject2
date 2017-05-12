package sample;

import java.io.Serializable;

public abstract class Account implements Serializable {

    private String userName = null;
    private String password = null;
    private int privelegelevel = 0;

    public int getPrivelegelevel() {
        return privelegelevel;
    }

    public void setPrivelegelevel(int privelegelevel) {
        this.privelegelevel = privelegelevel;
    }

    public Account(String userName) {
        setUserName ( userName );
    }

    public Account(String userName, String password, int privelegelevel) {
        setUserName ( userName );
        setPassword ( password );
        setPrivelegelevel ( privelegelevel );
    }

    public void setUserName(String userName) {
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