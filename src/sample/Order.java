package sample;

public class Order extends Cart {

    private int orderNumber = 0;

    private Boss bossUserName = null;

    private EmployeeAccount employeeAccountUserName = null;

    private BossAccount bossAccountUserName = null;

    private int dateOfOrder = 0;

    private Cart cart = null;

    public Order(int orderNumber, int dateOfOrder, Cart cart) {
        setOrderNumber ( orderNumber );
        setDateOfOrder ( dateOfOrder );
        setCart ( cart );
    }

    public Order(int orderNumber, EmployeeAccount employeeAccountUserName, int dateOfOrder, Boss bossUserName, Cart cart) {
        setOrderNumber ( orderNumber );
        setDateOfOrder ( dateOfOrder );
        setEmployeeAccountUserName ( employeeAccountUserName );
        setBossUserName ( bossUserName );
        setCart ( cart );
    }

    public Order(int orderNumber, BossAccount bossAccountUserName, int dateOfOrder, Cart cart) {
        setOrderNumber ( orderNumber );
        setDateOfOrder ( dateOfOrder );
        setBossAccountUserName ( bossAccountUserName );
        setCart ( cart );
    }

    public Boss getBossUserName() {
        return bossUserName;
    }

    public void setBossUserName(Boss bossUserName) {
        this.bossUserName = bossUserName;
    }

    public BossAccount getBossAccountUserName() {
        return bossAccountUserName;
    }

    public void setBossAccountUserName(BossAccount bossAccountUserName) {
        this.bossAccountUserName = bossAccountUserName;
    }

    public void setDateOfOrder(int dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public EmployeeAccount getEmployeeAccountUserName() {
        return employeeAccountUserName;
    }

    public void setEmployeeAccountUserName(EmployeeAccount employeeAccountUserName) {
        this.employeeAccountUserName = employeeAccountUserName;
    }

    public int getDateOfOrder() {
        return dateOfOrder;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


}