package sample;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class RegularCustomerOrder extends Order {
    private Customer customerUserName = null;
    private int quantity = 0;

    public RegularCustomerOrder(int orderNumber, EmployeeAccount employeeAccountUserName, int dateOfOrder, Boss bossUserName, Cart cart, Customer customerUserName, int quantity) {
        super ( orderNumber, dateOfOrder, cart );
        setEmployeeAccountUserName ( employeeAccountUserName );
        setBossUserName ( bossUserName );
        setCustomerUserName ( customerUserName );
        setQuantity ( quantity );
    }

    public RegularCustomerOrder(int orderNumber, BossAccount bossAccountUserName, int dateOfOrder, Cart cart, Customer customerUserName, int quantity) {
        super ( orderNumber, dateOfOrder, cart );
        setBossAccountUserName ( bossAccountUserName );
        setCustomerUserName ( customerUserName );
        setQuantity ( quantity );
    }

    public Customer getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(Customer userName) {
        this.customerUserName = userName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
