package sample;

import javafx.scene.control.Alert;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nikolaj on 2017-05-05.
 */
public class SalesSearchQueries extends DBConnection{

    private PreparedStatement everyEmployeeAsc;
    private PreparedStatement employeeUsernameAsc;
    private PreparedStatement employeeSSNAsc;
    private PreparedStatement employeeSurnameAsc;

    private com.mysql.jdbc.Connection c = null;

    public SalesSearchQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );

            everyEmployeeAsc = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee ORDER BY Surname"  );

            employeeUsernameAsc = c.prepareStatement ("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE UserName = ? ORDER BY UserName");

            employeeSSNAsc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE SSN = ? ORDER BY SSN");

            employeeSurnameAsc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE Surname = ? ORDER BY Surname");

        } catch (SQLException ex) {
            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "No connection to database" );

            validAlert.showAndWait ();
        }

    }

    public ArrayList<Employee> getEmployeeDefaultSalesSearch() {

        ArrayList<Employee> results = null;
        ResultSet resultSet = null;

            try {

                    resultSet = everyEmployeeAsc.executeQuery ();


                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getInt ( "EmploymentDate" ),
                            resultSet.getInt ( "GamesSold" ),
                            resultSet.getDouble ( "Income" ),
                            resultSet.getString ( "UserName" ) )
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
    public ArrayList<Employee> getEmployeeSalesUserNameSearch(String userName) {

        ArrayList<Employee> results = null;
        ResultSet resultSet = null;

            try {
                    employeeUsernameAsc.setString ( 1, userName );

                    resultSet = employeeUsernameAsc.executeQuery ();

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getInt ( "EmploymentDate" ),
                            resultSet.getInt ( "GamesSold" ),
                            resultSet.getDouble ( "Income" ),
                            resultSet.getString ( "UserName" ) ) );

                }
                return results;

            } catch (SQLException sqlException) {
                sqlException.printStackTrace ();
            } finally {
                close ();
            }

        return null;
    }


    public ArrayList<Employee> getEmployeeSalesSSNSearch(String SSN) {

        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
            try {
                    employeeSSNAsc.setString ( 1, SSN );

                    resultSet = employeeSSNAsc.executeQuery ();

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getInt ( "EmploymentDate" ),
                            resultSet.getInt ( "GamesSold" ),
                            resultSet.getDouble ( "Income" ),
                            resultSet.getString ( "UserName" ) ) );

                }
                return results;

            } catch (SQLException sqlException) {
                sqlException.printStackTrace ();
            } finally {
                close ();
            }

        return null;
    }

    public ArrayList<Employee> getEmployeeSalesSurnameSearch(String surname) {

        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
            try {


                    employeeSurnameAsc.setString ( 1, surname );

                    resultSet = employeeSurnameAsc.executeQuery ();


                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getInt ( "EmploymentDate" ),
                            resultSet.getInt ( "GamesSold" ),
                            resultSet.getDouble ( "Income" ),
                            resultSet.getString ( "UserName" ) ) );
                }
                return results;
            } catch (SQLException sqlException) {
                sqlException.printStackTrace ();
            }finally {
                close ();
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

}
