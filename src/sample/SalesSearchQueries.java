package sample;

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
    private PreparedStatement everyEmployeeDesc;

    private PreparedStatement employeeUsernameAsc;
    private PreparedStatement employeeUsernameDesc;
    private PreparedStatement employeeSSNAsc;
    private PreparedStatement employeeSSNDesc;

    private PreparedStatement employeeSurnameAsc;
    private PreparedStatement employeeSurnameDesc;


    private String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    private com.mysql.jdbc.Connection c = null;

    public SalesSearchQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );

            everyEmployeeAsc = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee ORDER BY Surname"  );

            everyEmployeeDesc = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee ORDER BY Surname DESC" );

            employeeUsernameAsc = c.prepareStatement ("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE UserName = ? ORDER BY UserName");

            employeeUsernameDesc = c.prepareStatement ("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE UserName = ? ORDER BY UserName DESC");

            employeeSSNAsc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE SSN = ? ORDER BY SSN");

            employeeSSNDesc = c.prepareStatement( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE SSN = ? ORDER BY SSN DESC" );

            employeeSurnameAsc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE Surname = ? ORDER BY Surname");

            employeeSurnameDesc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE Surname = ? ORDER BY Surname DESC" );

        } catch (SQLException ex) {
            System.err.println ( "the connection fails" );
        }

    }

    public ArrayList<Employee> getEmployeeDefaultSalesSearch(boolean ascending) {

        ArrayList<Employee> results = null;
        ResultSet resultSet = null;

            try {
                if (ascending == true) {

                    resultSet = everyEmployeeAsc.executeQuery ();
                } else {

                    resultSet = everyEmployeeDesc.executeQuery ();
                }

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
    public ArrayList<Employee> getEmployeeSalesUserNameSearch(String userName, boolean ascending) {

        ArrayList<Employee> results = null;
        ResultSet resultSet = null;

            try {
                if (ascending == true) {
                    employeeUsernameAsc.setString ( 1, userName );

                    resultSet = employeeUsernameAsc.executeQuery ();
                } else {
                    employeeUsernameDesc.setString ( 1, userName );

                    resultSet = employeeUsernameDesc.executeQuery ();
                }

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


    public ArrayList<Employee> getEmployeeSalesSSNSearch(String SSN, boolean ascending) {

        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
            try {
                if (ascending == true) {
                    employeeSSNAsc.setString ( 1, SSN );

                    resultSet = employeeSSNAsc.executeQuery ();
                } else {
                    employeeSSNDesc.setString ( 1, SSN );

                    resultSet = employeeSSNDesc.executeQuery ();

                }
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

    public ArrayList<Employee> getEmployeeSalesSurnameSearch(String surname, boolean ascending) {

        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
            try {
                if (ascending == true) {

                    employeeSurnameAsc.setString ( 1, surname );

                    resultSet = employeeSurnameAsc.executeQuery ();
                } else {

                    employeeSurnameDesc.setString ( 1, surname );

                    resultSet = employeeSurnameDesc.executeQuery ();
                }

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
