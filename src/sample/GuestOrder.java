package sample;

import java.util.ArrayList;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class GuestOrder extends Order {

    public GuestOrder(int dateOfOrder, ArrayList<Item> itemList) {
        super ( dateOfOrder, itemList );
    }

    public GuestOrder(EmployeeAccount employeeUserName, int dateOfOrder, BossAccount bossUserName, ArrayList<Item> itemList) {
        super ( dateOfOrder, itemList);
        setEmployeeAccountUserName ( employeeUserName );
        setBossAccountUserName ( bossUserName );
    }

    public GuestOrder(BossAccount bossAccountUserName, int dateOfOrder, ArrayList<Item> itemList) {
        super ( dateOfOrder, itemList);
        setBossAccountUserName ( bossAccountUserName );
    }
}
