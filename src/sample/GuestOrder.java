package sample;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class GuestOrder extends Order {

    public GuestOrder(int orderNumber, int dateOfOrder, Cart cart) {
        super ( orderNumber, dateOfOrder, cart );
    }

    public GuestOrder(int orderNumber, EmployeeAccount employeeAccountUserName, int dateOfOrder, Boss bossUserName, Cart cart) {
        super ( orderNumber, dateOfOrder, cart );
        setEmployeeAccountUserName ( employeeAccountUserName );
        setBossUserName ( bossUserName );
    }

    public GuestOrder(int orderNumber, BossAccount bossAccountUserName, int dateOfOrder, Cart cart) {
        super ( orderNumber, dateOfOrder, cart );
        setBossAccountUserName ( bossAccountUserName );
    }

}
