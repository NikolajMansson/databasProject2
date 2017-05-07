package sample;

import java.sql.*;

/**
 * Created by Nikolaj on 2017-05-05.
 */
public class CustomerSetAccountQueries extends DBConnection{

    private PreparedStatement insertNewCustomer;
    private PreparedStatement searchForPasswordCustomer;


    private String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    private String correctPassword = null;
    private com.mysql.jdbc.Connection c = null;



    public CustomerSetAccountQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );
            searchForPasswordCustomer = c.prepareStatement ( "SELECT UserPassword FROM Customer WHERE UserName = ?" );
            insertNewCustomer = c.prepareStatement ( "INSERT INTO Customer(SSN, FirstName, Surname, RegistrationDate, UserName, UserPassword, Email) VALUES (?, ?, ?, ?, ?, ?, ?)");


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

    public void addCustomerToList(String ssn, String firstName, String surname, String registrationDate, String email, String username, String password) {


            try {
                insertNewCustomer.setInt ( 1, Integer.parseInt ( ssn ) );
                insertNewCustomer.setString ( 2, firstName );
                insertNewCustomer.setString ( 3, surname );
                insertNewCustomer.setInt ( 4, Integer.parseInt ( registrationDate ) );
                insertNewCustomer.setString ( 5, username );
                insertNewCustomer.setString ( 6, password );
                insertNewCustomer.setString ( 7, email );
                insertNewCustomer.executeUpdate ();

        } catch (SQLException e) {
            e.printStackTrace ();
        }
            finally {
                close ();
            }

    }

    public boolean searchForPasswordCustomer(String id, String password) {
        ResultSet rs = null;
        try {
            searchForPasswordCustomer.setString(1, id);
            rs = searchForPasswordCustomer.executeQuery ();
            while (rs.next ()) {
                this.correctPassword = String.format ( rs.getString ( 1 ) );
            }

            if (correctPassword.equals ( password )) {
                return true;
            }

        } catch (SQLException ex) {
            System.err.println ( "error on executing the query" );
        }
        finally {
            close ();
        }
        return false;
    }
}
