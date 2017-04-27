package sample;

import java.util.Date;

public class Order extends Cart {

    private Integer orderNumber;

    private CustomerAccount userID;

    private Double totalPrice;

    private Integer totalQuantityItems;

    private EmployeeAccount employeeSSN;

    private Date dateOfOrder;

    private Cart cart;

    public Order(int orderNumber, EmployeeAccount employeeSSN, Date dateOfOrder, Cart cart) {
        setOrderNumber ( orderNumber );
        setEmployeeSSN ( employeeSSN );
        setDateOfOrder ( dateOfOrder );
        setCart ( cart );
    }
    public Order(int orderNumber, CustomerAccount userID, EmployeeAccount employeeSSN, Date dateOfOrder, Cart cart) {
        setUserID ( userID );
        setOrderNumber ( orderNumber );
        setEmployeeSSN ( employeeSSN );
        setDateOfOrder ( dateOfOrder );
        setCart ( cart );
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public CustomerAccount getUserID() {
        return userID;
    }

    public void setUserID(CustomerAccount userID) {
        this.userID = userID;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalQuantityItems() {
        return totalQuantityItems;
    }

    public void setTotalQuantityItems(Integer totalQuantityItems) {
        this.totalQuantityItems = totalQuantityItems;
    }

    public EmployeeAccount getEmployeeSSN() {
        return employeeSSN;
    }

    public void setEmployeeSSN(EmployeeAccount employeeSSN) {
        this.employeeSSN = employeeSSN;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}