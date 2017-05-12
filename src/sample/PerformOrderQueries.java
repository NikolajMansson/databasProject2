package sample;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.*;

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

            increaseMoneyEmployee = c.prepareStatement ( "UPDATE Employee SET Income=Income + ? WHERE UserName = ?;" );
            increaseItemsSoldEmployee = c.prepareStatement ( "UPDATE Employee SET GamesSold=GamesSold + ? WHERE UserName = ?;" );
            saleToMember = c.prepareStatement ( "insert into regularCustomerOrder(Customer_UserName, Employees_UserName, Item_ArticleNo, Quantity, TotalPricePerItem) values((SELECT UserName FROM Customer WHERE UserName=?), (SELECT UserName FROM Employee WHERE UserName=?), (SELECT ArticleNo from Item where ArticleNo=?), ?, ?);" );
            saleToGuest = c.prepareStatement ( "insert into guestorder(Employees_UserName, Item_ArticleNo, quantity, TotalPricePerItem) values( (SELECT UserName FROM Employee WHERE UserName=?), (SELECT ArticleNo from Item where ArticleNo=?), ?, ?);");
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




    public void increaseEmployeeIncome(double price, String userName) {
BigDecimal value = BigDecimal.valueOf ( price );
        BigDecimal rounded = value.round(new MathContext ( 2, RoundingMode.CEILING ));

        try {
            increaseMoneyEmployee.setDouble ( 1, price);
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

    public void addRegularOrderToList(String customerUserName, String employeeUserName, int itemArticleNo, int quantity, double totalPricePerItem) {
        try {
            saleToMember.setString ( 1, customerUserName );
            saleToMember.setString ( 2, employeeUserName );
            saleToMember.setInt ( 3, itemArticleNo );
            saleToMember.setInt ( 4, quantity );
            saleToMember.setDouble ( 5, totalPricePerItem );
            saleToMember.executeUpdate ();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }

    }

    public void addGuestOrderToList(String employeeUserName, int itemArticleNo, int quantity, double totalPricePerItem) {

        try {


            saleToGuest.setString ( 1, employeeUserName );
            saleToGuest.setInt ( 2, itemArticleNo );
            saleToGuest.setInt ( 3, quantity );
            saleToGuest.setDouble ( 4, totalPricePerItem );
            saleToGuest.executeUpdate ();

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


