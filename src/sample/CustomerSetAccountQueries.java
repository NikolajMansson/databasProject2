package sample;

import javafx.scene.control.Alert;

import java.sql.*;

/**
 * Created by Nikolaj on 2017-05-05.
 */
public class CustomerSetAccountQueries extends DBConnection{

    private PreparedStatement insertNewCustomer;
    private PreparedStatement searchForPasswordCustomer;
    private String correctPassword = null;
    private com.mysql.jdbc.Connection c = null;



    public CustomerSetAccountQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );
            searchForPasswordCustomer = c.prepareStatement ( "SELECT UserPassword FROM Customer WHERE UserName = ?" );
            insertNewCustomer = c.prepareStatement ( "INSERT INTO Customer(SSN, FirstName, Surname, UserName, UserPassword, Email) VALUES (?, ?, ?, ?, ?, ?)");


        } catch (SQLException ex) {
            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "No connection to database" );

            validAlert.showAndWait ();
        }

    }

    public void close() {
        try {
            c.close ();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }
    }

    public void addCustomerToList(String ssn, String firstName, String surname, String email, String username, String password) {


            try {
                insertNewCustomer.setInt ( 1, Integer.parseInt ( ssn ) );
                insertNewCustomer.setString ( 2, firstName );
                insertNewCustomer.setString ( 3, surname );
                insertNewCustomer.setString ( 4, username );
                insertNewCustomer.setString ( 5, password );
                insertNewCustomer.setString ( 6, email );
                insertNewCustomer.executeUpdate ();

        }catch (SQLException e) {
                if(e instanceof SQLIntegrityConstraintViolationException){
                    Alert validAlert = new Alert ( Alert.AlertType.ERROR, "Account with the username exists. No new account created." );

                    validAlert.showAndWait ();

                }
                Alert validAlert = new Alert ( Alert.AlertType.ERROR, "Enter a valid info on person" );

                validAlert.showAndWait ();
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
            } else if (!correctPassword.equals(password)){
                return false;
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
