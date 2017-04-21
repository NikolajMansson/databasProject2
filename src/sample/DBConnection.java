package sample;

import com.mysql.jdbc.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            statement.executeQuery ( "INSERT INTO employees(SSN, FirstName, Surname, EmploymentDate, GamesSold, " +
                    "Income, Email, UserName, UserPassword) VALUES (" + ssn + ", '" + firstName + "', '" + surname + "', "
                    + employmentDate + ", 0, 0, '" + email + "', '" + username + "', '" + password +
                    "'); CREATE USER'" + username + "'@'localhost'; SET PASSWORD FOR '" + username + "'@'localhost' = PASSWORD('" + password + "');"
            );
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
            statement.executeQuery ( "INSERT INTO Game(Title, Genre, Developer, DescriptionOfPlot)" +
                    " VALUES ('" + title + "', '" + genre + "', '" + developer + "', '"
                    + descriptionOfPlot + "');"
            );
        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }
    }

    public void addItemToList(String title, String platform, int articleNumber, double price){
        try {
            statement.executeQuery ( "INSERT INTO Item(Game_Title, Platform_Abbreviation, ArticleNumber, price)" +
                    " VALUES ('" + title + "', '" + platform + "', '" + articleNumber + "', '"
                    + price + "');");
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void addPlatformToList(String abbreviation, String fullname, String maker){
        try {
            statement.executeQuery ( "INSERT INTO Item(Abbreviation, FullName, Developer)" +
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
}



