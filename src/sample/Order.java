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
        this.orderNumber = orderNumber;
        this.employeeSSN = employeeSSN;
        this.dateOfOrder = dateOfOrder;
        this.cart = cart;
    }
    public Order(int orderNumber, CustomerAccount userID, EmployeeAccount employeeSSN, Date dateOfOrder, Cart cart) {
        this.orderNumber = orderNumber;
        this.userID = userID;
        this.employeeSSN = employeeSSN;
        this.dateOfOrder = dateOfOrder;
        this.cart = cart;
    }


}