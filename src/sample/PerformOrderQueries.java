package sample;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nikolaj on 2017-05-05.
 */
public class PerformOrderQueries extends DBConnection{

    private PreparedStatement increaseMoneyBoss;
    private PreparedStatement increaseItemsSoldBoss;
    private PreparedStatement increaseMoneyEmployee;
    private PreparedStatement increaseItemsSoldEmployee;
    private PreparedStatement saleToMember;
    private PreparedStatement saleToGuest;
    private PreparedStatement getItemBeingSold;


    private String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    private com.mysql.jdbc.Connection c = null;

    public PerformOrderQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );

            increaseMoneyBoss = c.prepareStatement ( "UPDATE Boss SET Income=Income + ? WHERE UserName = ?" );
            increaseItemsSoldBoss = c.prepareStatement ( "UPDATE Boss SET GamesSold=GamesSold + ? WHERE UserName = ?;" );
            increaseMoneyEmployee = c.prepareStatement ( "UPDATE Employee SET Income=Income + ? WHERE UserName = ?;" );
            increaseItemsSoldEmployee = c.prepareStatement ( "UPDATE Employee SET GamesSold=GamesSold + ? WHERE UserName = ?;" );
            saleToMember = c.prepareStatement ( "insert into regularCustomerOrder(OrderNumber, DateOfOrder, Customer_UserName, Employees_UserName, Item_ArticleNo, Boss_UserName, Quantity, TotalPricePerItem) values(null, ?, (SELECT UserName FROM Customer WHERE UserName=?), (SELECT UserName FROM Employee WHERE UserName=?), (SELECT ArticleNo from Item where ArticleNo=?),(SELECT UserName from boss WHERE UserName=?), ?, ?)" );
            saleToGuest = c.prepareStatement ( "insert into guestorder(OrderNumber, DateOfOrder, Employees_UserName, Item_ArticleNo, Boss_UserName, TotalPricePerItem) VALUES(null, ?, (SELECT UserName FROM Employee WHERE UserName=?), (SELECT ArticleNo from Item where ArticleNo=?),(SELECT UserName from boss WHERE UserName=?), ?)" );
            getItemBeingSold = c.prepareStatement ( "SELECT * FROM Item WHERE ArticleNo = ?;" );
        } catch (SQLException ex) {
            System.err.println ( "the connection fails" );
        }

    }


    public void close() {
        try {
            c.close ();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }
    }

    public void increaseBossIncome(double price, String userName) {
        try {
            increaseMoneyBoss.setDouble ( 1, price );
            increaseMoneyBoss.setString ( 2, userName );
            increaseMoneyBoss.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }

    public void increaseGameSoldBoss(int gamesSold, String userName) {
        try {
            increaseItemsSoldBoss.setInt ( 1, gamesSold );
            increaseItemsSoldBoss.setString ( 2, userName );
            increaseItemsSoldBoss.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }


    public void increaseEmployeeIncome(double price, String userName) {
BigDecimal value = BigDecimal.valueOf ( price );
        BigDecimal rounded = value.round(new MathContext ( 2, RoundingMode.CEILING ));

        try {
            increaseMoneyEmployee.setString ( 1, String.valueOf(rounded));
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

    public void addRegularOrderToList(int dateOfOrder, String customerUserName, String employeeUserName, int itemArticleNo, String bossUserName, int quantity, double totalPricePerItem) {
        try {
            saleToMember.setInt ( 1, dateOfOrder );
            saleToMember.setString ( 2, customerUserName );
            saleToMember.setString ( 3, employeeUserName );
            saleToMember.setInt ( 4, itemArticleNo );
            saleToMember.setString ( 5, bossUserName );
            saleToMember.setInt ( 6, quantity );
            saleToMember.setDouble ( 7, totalPricePerItem );
            saleToMember.executeUpdate ();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }

    }

    public void addGuestOrderToList(int dateOfOrder, String employeeUserName, int itemArticleNo, String bossUserName, double totalPricePerItem) {

        try {
            saleToGuest.setInt ( 1, dateOfOrder );
            saleToGuest.setString ( 2, employeeUserName );
            saleToGuest.setInt ( 3, itemArticleNo );
            saleToGuest.setString ( 4, bossUserName );
            saleToGuest.setDouble ( 5, totalPricePerItem );

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
                int amountOfItems = rs.getInt ( 4 );
                String title = String.format ( rs.getString ( 5 ) );
                int releaseDate = rs.getInt ( 6 );
                Item item = new Item ( articleNoText, platformAbbreviation, price, amountOfItems, title, releaseDate );

                return item;
            }

        } catch (SQLException ex) {
            ex.printStackTrace ();
            //System.err.println ( "error on executing the query" );
        }

        return null;
    }

}


