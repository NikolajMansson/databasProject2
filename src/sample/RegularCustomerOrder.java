package sample;

import java.util.ArrayList;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class RegularCustomerOrder extends Order {
    private CustomerAccount customerUserName = null;
    private int quantity = 0;

    public RegularCustomerOrder(Account employeeAccountUserName, int dateOfOrder, BossAccount bossUserName, ArrayList<Item> itemList, CustomerAccount customerUserName, int quantity) {
        super ( dateOfOrder, itemList );
        setEmployeeAccountUserName ( (EmployeeAccount) employeeAccountUserName );
        setBossAccountUserName ( bossUserName );
        setCustomerUserName ( customerUserName );
        setQuantity ( quantity );
    }

    public RegularCustomerOrder(BossAccount bossAccountUserName, int dateOfOrder, ArrayList<Item> itemList, CustomerAccount customerUserName, int quantity) {
        super (dateOfOrder, itemList);
        setBossAccountUserName ( bossAccountUserName );
        setCustomerUserName ( customerUserName );
        setQuantity ( quantity );
    }

    public CustomerAccount getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(CustomerAccount userName) {
        this.customerUserName = userName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
