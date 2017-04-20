package sample;

public class Order extends Cart {

    private Integer orderNumber;

    private CustomerAccount id;

    private Double totalPrice;

    private Integer totalQuantityItems;

    private EmployeeAccount employeeSSN;

    private String dateOfOrder;

    private Cart cart;

    public Order(int orderNumber, CustomerAccount id, EmployeeAccount employeeSSN, String dateOfOrder, Cart cart) {
        this.orderNumber = orderNumber;
        this.id = id;
        this.employeeSSN = employeeSSN;
        this.dateOfOrder = dateOfOrder;
        this.cart = cart;
    }


}