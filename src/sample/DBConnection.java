package sample;

import com.mysql.jdbc.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {


    String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    Statement statement;
    String correctPassword = null;

    public void setDBURL(String id, String password) {
        this.DBURL = String.format ( "jdbc:mysql://127.0.0.1:3306/GameShop?user=" + id + "&password=" + password + ")" );
    }

    public DBConnection() {
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            statement = c.createStatement ();
        } catch (SQLException ex) {
            System.out.println ( "the connection fails" );
        }
    }

    public boolean searchForPasswordEmployee(String id, String password) {
        try {
            ResultSet rs = statement.executeQuery ( "SELECT UserPassword FROM Employees WHERE UserName ='" + id + "'" );


            while (rs.next ()) {
                this.correctPassword = String.format ( rs.getString ( 1 ) );

            }

            if (correctPassword.equals ( password )) {
                return true;
            }


        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }
        return false;
    }

    public boolean searchForPasswordCustomer(String id, String password) {
        try {
            ResultSet rs = statement.executeQuery ( "SELECT UserPassword FROM Customer WHERE UserName ='" + id + "'" );


            while (rs.next ()) {
                this.correctPassword = String.format ( rs.getString ( 1 ) );

            }

            if (correctPassword.equals ( password )) {
                return true;
            }


        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }
        return false;
    }

    public void addEmployeeToList(String ssn, String firstName, String surname, String employmentDate, String email, String username, String password) {

        try {

            statement.executeUpdate ( "INSERT INTO Employees(SSN, FirstName, Surname, EmploymentDate, GamesSold, Income, Email, UserName, UserPassword, UserType) VALUES (790312, 'Elin', 'Kristiansson', 20050305, 0, 0, 'elin.kristiansson@gmail.com', 'boss3Store', '123', 'Boss'); " );




        } catch (SQLException ex) {

            System.out.println ( "error on executing the query" );

        }

    }

    public void addCustomerToList(String ssn, String firstName, String surname, String registrationDate, String email, String username, String password) {

        try {

            statement.executeQuery ( "INSERT INTO Customer(SSN, FirstName, Surname, RegistrationDate,  " +

                    "Email, UserName, UserPassword) VALUES (" + ssn + ", '" + firstName + "', '" + surname + "', "

                    + registrationDate + ", '" + email + "', '" + username + "', '" + password +

                    "'); CREATE USER'" + username + "'@'localhost'; SET PASSWORD FOR '" + username + "'@'localhost' = PASSWORD('" + password + "');"

            );

        } catch (SQLException ex) {

            System.out.println ( "error on executing the query" );

        }

    }

    public void addGameToList(String title, String genre, String developer, String descriptionOfPlot) {
        try {
            statement.executeUpdate ( "INSERT INTO Game(Title, Genre, Developer, DescriptionOfPlot)" +
                    " VALUES ('" + title + "', '" + genre + "', '" + developer + "', '"
                    + descriptionOfPlot + "');"
            );
        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }
    }

    public void addItemToList(String title, String platform, String price, String amountOfItems){
        try {
            statement.executeUpdate ( "INSERT INTO Item (Game_Title, Platform_Abbreviation, price, amountOfItems) VALUES  ((SELECT Title FROM GAME WHERE Title = 'NHL2016'), (SELECT Abbreviation FROM Platform WHERE Abbreviation = 'XBOX1'), 299.00, 10);");
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void addPlatformToList(String abbreviation, String fullname, String maker){
        try {
            statement.executeUpdate ( "INSERT INTO Item(Abbreviation, FullName, Developer)" +
                    " VALUES ('" + abbreviation + "', '" + fullname + "', '" + maker + "');");
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void removeItem(int articleId) {
        // TODO implement here
    }

    public void addOrderToList(Order order) {
        // TODO implement here
    }

    public String searchForName(String id) {
        String firstName;
        try {
            ResultSet rs = statement.executeQuery ( "SELECT FirstName FROM Employees WHERE UserName ='" + id + "'" );


            while (rs.next ()) {
                firstName = String.format ( rs.getString ( 1 ) );
                return firstName;

            }

        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }
        return null;
    }

    public void searchforGameTitle(String title)
    {

    }


    //unstable code
    public void defaultSearch()
    {
        try
        {
            ArrayList<String> gameTitleInLibrary = new ArrayList<>();
            ArrayList<Integer> articlenumbers = new ArrayList<>();
            ArrayList<String> platformlist = new ArrayList<>();
            ArrayList<Double> prices = new ArrayList<>();

            ResultSet rs = statement.executeQuery("SELECT ArticleNumber,Game_Title,Platform_Abbreviation,price FROM item" );

            while (rs.next())
            {

                gameTitleInLibrary.add(rs.getString(1));
                articlenumbers.add(rs.getInt(1));
                platformlist.add(rs.getString(1));
                prices.add(rs.getDouble(1));
            }
        } catch (SQLException e) {
            System.out.println ( "error on executing the query" );
        }

    }
}



