package sample;

import java.util.ArrayList;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class GuestOrder extends Order {

    public GuestOrder( ArrayList<Item> itemList) {
        super (  itemList );
    }

    public GuestOrder(EmployeeAccount employeeUserName, BossAccount bossUserName, ArrayList<Item> itemList) {
        super (  itemList);
        setEmployeeAccountUserName ( employeeUserName );
        setBossAccountUserName ( bossUserName );
    }

    public GuestOrder(BossAccount bossAccountUserName, ArrayList<Item> itemList) {
        super ( itemList);
        setBossAccountUserName ( bossAccountUserName );
    }
}
