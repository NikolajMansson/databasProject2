package sample;

import java.util.Date;

public class Order extends Cart {

    public Order() {
    }

    private Integer orderNumber;

    private CustomerAccount id;

    private Double totalPrice;

    private Integer totalQuantityItems;

    private EmployeeAccount employeeSSN;

    private Date dateOfOrder;

    private Cart cart;


    public void setCart(Cart cart) {
        // TODO implement here
    }

}