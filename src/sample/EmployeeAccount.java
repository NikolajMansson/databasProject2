package sample;

import java.io.Serializable;

public class EmployeeAccount extends Account implements Serializable {
   private int priveledgelevel= 0;

    public EmployeeAccount(String userName) {
        super ( userName );
    }

    public EmployeeAccount(String userName, String password, int priveledgelevel) {
        super ( userName );
        this.setPassword ( password );
        setPrivelegelevel ( priveledgelevel );
    }

    public int getPriveledgelevel() {
        return priveledgelevel;
    }

    public void setPriveledgelevel(int priveledgelevel) {
        this.priveledgelevel = priveledgelevel;
    }
}





