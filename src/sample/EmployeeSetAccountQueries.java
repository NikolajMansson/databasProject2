package sample;

import java.sql.*;

/**
 * Created by Nikolaj on 2017-04-25.
 */
public class EmployeeSetAccountQueries extends DBConnection{

    private PreparedStatement insertNewEmployee;
    private PreparedStatement searchForPasswordEmployee;
    private PreparedStatement eraseEmployeeRegularOrder;
    private PreparedStatement eraseEmployeeGuestOrder;

    private String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    private String correctPassword = null;
    private com.mysql.jdbc.Connection c = null;

    public EmployeeSetAccountQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );
            searchForPasswordEmployee = c.prepareStatement ( "SELECT UserPassword FROM Employee WHERE UserName = ?" );
            eraseEmployeeRegularOrder = c.prepareStatement ( "UPDATE guestorder, employee SET Employees_UserName='NotActive' AND UserName='NotActive' WHERE (SELECT UserName FROM Employee WHERE UserName = ?) AND Employees_UserName = UserName;");
            eraseEmployeeGuestOrder = c.prepareStatement("UPDATE regularcustomerorder, employee SET Employees_UserName='NotActive' AND UserName='NotActive' WHERE (SELECT UserName FROM Employee WHERE UserName = ?) AND Employees_UserName = UserName;");
            insertNewEmployee = c.prepareStatement ( "INSERT INTO Employee(SSN, FirstName, Surname, EmploymentDate, GamesSold, Income, Email, UserName, UserPassword, Boss_UserName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);" );
        } catch (SQLException ex) {
            System.err.println ( "the connection fails" );
        }

    }

    public void addEmployeeToList(String ssn, String firstName, String surname, String employmentDate, String email, String username, String password) {


        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();

        try {
            insertNewEmployee.setInt ( 1, Integer.parseInt ( ssn ) );
            insertNewEmployee.setString ( 2, firstName );
            insertNewEmployee.setString ( 3, surname );
            insertNewEmployee.setInt ( 4, Integer.parseInt ( employmentDate ) );
            insertNewEmployee.setInt ( 5, 0 );
            insertNewEmployee.setDouble ( 6, 0.00 );
            insertNewEmployee.setString ( 7, email );
            insertNewEmployee.setString ( 8, username );
            insertNewEmployee.setString ( 9, password );
            insertNewEmployee.setString ( 10, account.getUserName () );
            insertNewEmployee.executeUpdate ();

        } catch (SQLException e) {
            e.printStackTrace ();
        }
        finally {
            close ();
        }
    }

    public void close() {
        try {
            c.close ();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }
    }

    public boolean searchForPasswordEmployee(String id, String password) {
        ResultSet rs = null;
        try {
            searchForPasswordEmployee.setString ( 1, id );
            rs = searchForPasswordEmployee.executeQuery ();
            while (rs.next ()) {
                this.correctPassword = String.format ( rs.getString ( 1 ) );
            }

            if (correctPassword.equals ( password )) {
                return true;
            } else if (!correctPassword.equals( password )){
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

    public void removeEmployee(String userName) {
        try {
            eraseEmployeeRegularOrder.setString ( 1, userName );
            eraseEmployeeRegularOrder.executeUpdate ();

            eraseEmployeeGuestOrder.setString ( 1, userName );
            eraseEmployeeGuestOrder.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        finally {
            close ();
        }
    }
}


