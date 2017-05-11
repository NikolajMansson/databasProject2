package sample;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Order  {

    private Boss bossUserName = null;

    private EmployeeAccount employeeAccountUserName = null;

    private BossAccount bossAccountUserName = null;

    private LocalDateTime dateOfOrder = null;

    ArrayList<Item> itemList = new ArrayList<> (  );

    public Order(ArrayList<Item> itemList) {
        dateOfOrder = LocalDateTime.now();
        setItemList ( itemList );
    }

    public Order(EmployeeAccount employeeAccountUserName,LocalDateTime date, Boss bossUserName, ArrayList<Item> itemList) {

        setDateOfOrder(date);
        setEmployeeAccountUserName ( employeeAccountUserName );
        setBossUserName ( bossUserName );
        setItemList ( itemList );
    }

    public Order(BossAccount bossAccountUserName,  ArrayList<Item> itemList) {
        dateOfOrder = LocalDateTime.now();
        setBossAccountUserName ( bossAccountUserName );
        setItemList ( itemList );
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