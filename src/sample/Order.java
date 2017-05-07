package sample;

import java.util.ArrayList;

public abstract class Order  {

    private Boss bossUserName = null;

    private EmployeeAccount employeeAccountUserName = null;

    private BossAccount bossAccountUserName = null;

    private int dateOfOrder = 0;

    ArrayList<Item> itemList = new ArrayList<> (  );

    public Order(int dateOfOrder, ArrayList<Item> itemList) {
        setDateOfOrder ( dateOfOrder );
        setItemList ( itemList );
    }

    public Order(EmployeeAccount employeeAccountUserName, int dateOfOrder, Boss bossUserName, ArrayList<Item> itemList) {
        setDateOfOrder ( dateOfOrder );
        setEmployeeAccountUserName ( employeeAccountUserName );
        setBossUserName ( bossUserName );
        setItemList ( itemList );
    }

    public Order(BossAccount bossAccountUserName, int dateOfOrder, ArrayList<Item> itemList) {
        setDateOfOrder ( dateOfOrder );
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

    public void setDateOfOrder(int dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
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

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

}