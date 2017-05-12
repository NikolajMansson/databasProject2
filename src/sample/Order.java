package sample;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Order  {

    private Employee bossUserName = null;

    private EmployeeAccount employeeAccountUserName = null;


    private LocalDateTime dateOfOrder = null;

    ArrayList<Item> itemList = new ArrayList<> (  );

    public Order(ArrayList<Item> itemList) {
        dateOfOrder = LocalDateTime.now();
        setItemList ( itemList );
    }

    public Order(EmployeeAccount employeeAccountUserName,LocalDateTime date,  ArrayList<Item> itemList) {

        setDateOfOrder(date);
        setEmployeeAccountUserName ( employeeAccountUserName );

        setItemList ( itemList );
    }

    public Employee getBossUserName() {
        return bossUserName;
    }

    public void setEmployeeUserName(Employee bossUserName) {
        this.bossUserName = bossUserName;
    }


    public void setDateOfOrder(LocalDateTime dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public EmployeeAccount getEmployeeAccountUserName() {
        return employeeAccountUserName;
    }

    public void setEmployeeAccountUserName(EmployeeAccount employeeAccountUserName) {
        this.employeeAccountUserName = employeeAccountUserName;
    }

    public LocalDateTime getDateOfOrder() {
        return dateOfOrder;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

}