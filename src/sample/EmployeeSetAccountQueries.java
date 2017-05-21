package sample;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Nikolaj on 2017-04-25.
 */
public class EmployeeSetAccountQueries extends DBConnection {

    private PreparedStatement insertNewEmployee;
    private PreparedStatement searchForPasswordEmployee;
    private PreparedStatement searchForPasswordBoss;
    private PreparedStatement eraseEmployee;
    private PreparedStatement logInsertOfNewEmployee;
    private PreparedStatement getUserLog;
    private PreparedStatement removeFromLog;

    private String correctPassword = null;
    private com.mysql.jdbc.Connection c = null;

    public EmployeeSetAccountQueries() {

        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );
            searchForPasswordEmployee = c.prepareStatement ( "SELECT UserPassword FROM Employee WHERE UserName = ? AND PrivilegeLevel=1" );
            searchForPasswordBoss = c.prepareStatement ( "SELECT UserPassword FROM Employee WHERE UserName = ? AND PrivilegeLevel=0" );
            eraseEmployee = c.prepareStatement ( "UPDATE Employee SET isEmployed=0 WHERE UserName = ?" );
            insertNewEmployee = c.prepareStatement ( "insert into Employee values(?, ?, ?, now(), 0, 0, ?, ?, ?,  ?, 1)" );
            logInsertOfNewEmployee = c.prepareStatement ( "INSERT INTO Log(AddUserName, Employee_UserName) VALUES (?, ?)" );
            getUserLog = c.prepareStatement ( "select * from log;" );
            removeFromLog = c.prepareStatement ( "delete from log where idLog = ?;" );
        } catch (SQLException ex) {
            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "No connection to database" );

            validAlert.showAndWait ();
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
            insertNewEmployee.setInt ( 7, employeeStatus );
            insertNewEmployee.executeUpdate ();


        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                Alert validAlert = new Alert ( Alert.AlertType.ERROR, "Account with the username exists. No new account created." );

                validAlert.showAndWait ();

            }
            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "Enter a valid info on person" );

            validAlert.showAndWait ();
        } finally {
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
            } else if (!correctPassword.equals ( password )) {
                return false;
            }

        } catch (SQLException ex) {
            System.err.println ( "error on executing the query" );
        } finally {
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
        } finally {
            close ();
        }
        return false;
    }

    public void logNewEmployee(String newUser, String boss) {
        try {
            logInsertOfNewEmployee.setString ( 1, newUser );
            logInsertOfNewEmployee.setString ( 2, boss );
            logInsertOfNewEmployee.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            close ();
        }
    }

    public ArrayList<Log> getLog() {


        ArrayList<Log> results = null;
        ResultSet resultSet = null;

        try {

            resultSet = getUserLog.executeQuery ();

            results = new ArrayList<> ();

            while (resultSet.next ()) {
                results.add ( new Log (
                        resultSet.getInt ( "idLog" ),
                        resultSet.getString ( "event" ),
                        resultSet.getString ( "Employee_UserName" ),
                        resultSet.getTimestamp ( "Time" ) )
                );
            }
            return results;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        } finally {
            close ();
        }

        return null;

    }

    public void removeLogID(int logID) {
        try {
            removeFromLog.setInt ( 1, logID );
            logInsertOfNewEmployee.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            close ();
        }
    }

    public void removeEmployee(String userName) {
        try {
            eraseEmployee.setString ( 1, userName );
            eraseEmployee.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            close ();
        }
    }
}


