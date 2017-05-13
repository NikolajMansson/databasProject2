package sample;

import java.sql.*;

/**
 * Created by Nikolaj on 2017-04-25.
 */
public class EmployeeSetAccountQueries extends DBConnection{

    private PreparedStatement insertNewEmployee;
    private PreparedStatement searchForPasswordEmployee;
    private PreparedStatement searchForPasswordBoss;
    private PreparedStatement eraseEmployee;
    private PreparedStatement logInsertOfNewEmployee;

    private String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    private String correctPassword = null;
    private com.mysql.jdbc.Connection c = null;

    public EmployeeSetAccountQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );
            searchForPasswordEmployee = c.prepareStatement ( "SELECT UserPassword FROM Employee WHERE UserName = ? AND PrivilegeLevel=1" );
            searchForPasswordBoss = c.prepareStatement ( "SELECT UserPassword FROM Employee WHERE UserName = ? AND PrivilegeLevel=0" );
            eraseEmployee = c.prepareStatement ( "UPDATE Employee SET isEmployed=0 WHERE UserName = ?");
            insertNewEmployee = c.prepareStatement ( "insert into Employee values(?, ?, ?, now(), 0, 0, ?, ?, ?,  ?, 1)" );
            logInsertOfNewEmployee = c.prepareStatement ( "INSERT INTO Log(event, Employee_UserName) VALUES (?, ?)" );
        } catch (SQLException ex) {
            System.err.println ( "the connection fails" );
        }

    }

    public void addEmployeeToList(String ssn, String firstName, String surname, String email, String username, String password, int employeeStatus) {


        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();

        try {
            insertNewEmployee.setInt ( 1, Integer.parseInt ( ssn ) );
            insertNewEmployee.setString ( 2, firstName );
            insertNewEmployee.setString ( 3, surname );
            insertNewEmployee.setString ( 4, email );
            insertNewEmployee.setString ( 5, username );
            insertNewEmployee.setString ( 6, password );
            insertNewEmployee.setInt(7, employeeStatus);
            insertNewEmployee.executeUpdate ();


        } catch (SQLException e) {
            e.printStackTrace ();
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
    public boolean searchForPasswordBoss(String id, String password) {
        ResultSet rs = null;
        try {
            searchForPasswordBoss.setString ( 1, id );
            rs = searchForPasswordBoss.executeQuery ();
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

    public void logNewEmployee(String newUser, String boss){
        try{
            logInsertOfNewEmployee.setString(1, newUser);
            logInsertOfNewEmployee.setString(2, boss);
            logInsertOfNewEmployee.executeUpdate ();
        }
        catch (SQLException e){
            e.printStackTrace ();
        }
        finally {
            close ();
        }
    }

    public void removeEmployee(String userName) {
        try {
            eraseEmployee.setString ( 1, userName );
            eraseEmployee.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        finally {
            close ();
        }
    }
}


