package sample;

import javafx.scene.control.Alert;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Nikolaj on 2017-05-05.
 */
public class PerformOrderQueries extends DBConnection {

    private PreparedStatement checkForAvailableItems;
    private PreparedStatement increaseMoneyEmployee;
    private PreparedStatement increaseItemsSoldEmployee;
    private PreparedStatement saleToMember;
    private PreparedStatement saleToGuest;
    private PreparedStatement getItemBeingSold;
    private PreparedStatement decreaseItemAmount;
    private PreparedStatement setGuestOrderToItem;
    private PreparedStatement setMemberOrderToItem;


    private com.mysql.jdbc.Connection c = null;

    private int ordernumber = 0;
    private ArrayList<Integer> itemIDList = new ArrayList<> ();
    private ArrayList<Integer> intList = new ArrayList<> ();
    public PerformOrderQueries() {

        try {

            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );
            checkForAvailableItems = c.prepareStatement ( "select IDItem from item WHERE ItemCollection_ArticleNo = ? AND IsSold = 0;" );
            increaseMoneyEmployee = c.prepareStatement ( "UPDATE Employee SET Income=Income + ? WHERE UserName = ?;" );
            increaseItemsSoldEmployee = c.prepareStatement ( "UPDATE Employee SET GamesSold=GamesSold + ? WHERE UserName = ?;" );
            saleToMember = c.prepareStatement ( "insert into regularCustomerOrder(Customer_UserName, Employees_UserName, Quantity, TotalPricePerItem) values((SELECT UserName FROM Customer WHERE UserName=?), (SELECT UserName FROM Employee WHERE UserName=?), ?, ?);", saleToMember.RETURN_GENERATED_KEYS );
            saleToGuest = c.prepareStatement ( "insert into guestorder(Employees_UserName, quantity, TotalPricePerItem) values((SELECT UserName FROM Employee WHERE UserName=?), ?, ?);", saleToGuest.RETURN_GENERATED_KEYS );
            getItemBeingSold = c.prepareStatement ( "SELECT * FROM ItemCollection WHERE ArticleNo = ?;" );
            decreaseItemAmount = c.prepareStatement ( "UPDATE Item SET IsSold=1 WHERE IDItem = ?;" );
            setGuestOrderToItem = c.prepareStatement ( "UPDATE Item SET GuestOrder_OrderNumber=(SELECT OrderNumber FROM GuestOrder WHERE OrderNumber=?) WHERE IDItem = ?;" );
            setMemberOrderToItem = c.prepareStatement ( "UPDATE Item SET RegularCustomerOrder_Ordernumber=(SELECT OrderNumber FROM GuestOrder WHERE OrderNumber=?) WHERE IDItem = ?;" );
        } catch (SQLException ex) {
            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "No connection to database" );

            validAlert.showAndWait ();

        }
    }

    public ArrayList<Integer> getItemIDList(ArrayList<Item> accountNoList, ArrayList<Integer> quantityList) {
        ResultSet resultSet = null;
        try {
            for (int i = 0; i < accountNoList.size (); i++) {
                int quantity = quantityList.get ( i );



                        checkForAvailableItems.setInt ( 1, accountNoList.get ( i ).getArticleNumber () );
                        resultSet = checkForAvailableItems.executeQuery ();

                        while (resultSet.next ()) {

                            this.intList.add (
                                    resultSet.getInt ( "IDItem" )
                            );
                        }
                        try {
                            for (int k = 0; k < quantity; k++) {
                                int itemID = intList.get ( k );
                                this.itemIDList.add ( itemID );
                            }
                        }
                        catch (IndexOutOfBoundsException ex) {
                            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "There are not enough items. Log out and make a new order." );

                            validAlert.showAndWait ();
                            return null;
                        }


                for(int l = 0; l < intList.size (); l++){
                            this.intList.remove ( i );
                        }

                    }
            return itemIDList;
                } catch (SQLException e1) {
                    e1.printStackTrace ();





        } catch (NoSuchElementException e) {
            e.printStackTrace ();
        }
return null;
    }

    public void close() {
        try {
            c.close ();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }
    }


    public void increaseEmployeeIncome(double price, String userName) {

        try {
            increaseMoneyEmployee.setDouble ( 1, price );
            increaseMoneyEmployee.setString ( 2, userName );
            increaseMoneyEmployee.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }

    public void increaseGameSoldEmployee(int gamesSold, String userName) {

        try {
            increaseItemsSoldEmployee.setInt ( 1, gamesSold );
            increaseItemsSoldEmployee.setString ( 2, userName );
            increaseItemsSoldEmployee.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void addRegularOrderToList(String customerUserName, String employeeUserName, int itemArticleNo, int quantity, double totalPricePerItem, ArrayList<Integer> itemIdList) {
        try {
            saleToMember.setString ( 1, customerUserName );
            saleToMember.setString ( 2, employeeUserName );
            saleToMember.setInt ( 3, quantity );
            saleToMember.setDouble ( 4, totalPricePerItem );
            saleToMember.executeUpdate ();
            for (int i = 0; i < itemIdList.size (); i++) {
                decreaseItemAmount.setInt ( 1, itemIdList.get ( i ) );
                decreaseItemAmount.executeUpdate ();
            }
            ResultSet rs = saleToMember.getGeneratedKeys ();
            if (rs.next ()) {

                this.ordernumber = rs.getInt ( 1 );
            }


            for (int i = 0; i < itemIdList.size (); i++) {
                System.out.println ( ordernumber );
                setMemberOrderToItem.setInt ( 1, ordernumber );
                setMemberOrderToItem.setInt ( 2, itemIdList.get ( i ) );
                setMemberOrderToItem.executeUpdate ();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }

    }

    public void addGuestOrderToList(String employeeUserName, int quantity, double totalPricePerItem, int articleNo, ArrayList<Integer> itemIdList) {

        try {

            saleToGuest.setString ( 1, employeeUserName );
            saleToGuest.setInt ( 2, quantity );
            saleToGuest.setDouble ( 3, totalPricePerItem );
            saleToGuest.executeUpdate ();
            for (int i = 0; i < itemIdList.size (); i++) {
                decreaseItemAmount.setInt ( 1, itemIdList.get ( i ) );
                decreaseItemAmount.executeUpdate ();
            }

            ResultSet rs = saleToGuest.getGeneratedKeys ();
            if (rs.next ()) {

                this.ordernumber = rs.getInt ( 1 );
            }


            for (int i = 0; i < itemIdList.size (); i++) {
                System.out.println ( ordernumber );
                setGuestOrderToItem.setInt ( 1, ordernumber );
                setGuestOrderToItem.setInt ( 2, itemIdList.get ( i ) );
                setGuestOrderToItem.executeUpdate ();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }

    }

    public Item getSalesItem(int articleNo) {

        try {
            getItemBeingSold.setInt ( 1, articleNo );
            ResultSet rs = getItemBeingSold.executeQuery ();

            while (rs.next ()) {
                int articleNoText = rs.getInt ( 1 );
                String platformAbbreviation = String.format ( rs.getString ( 2 ) );
                double price = rs.getDouble ( 3 );
                String title = String.format ( rs.getString ( 4 ) );
                int releaseDate = rs.getInt ( 5 );
                Item item = new Item ( articleNoText, platformAbbreviation, price, title, releaseDate );

                return item;
            }

        } catch (SQLException ex) {
            ex.printStackTrace ();

        }

        return null;
    }

}


