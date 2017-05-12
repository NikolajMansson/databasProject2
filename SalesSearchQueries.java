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
    private PreparedStatement bossUsernameAsc;
    private PreparedStatement bossUsernameDesc;
    private PreparedStatement employeeUsernameAsc;
    private PreparedStatement employeeUsernameDesc;
    private PreparedStatement employeeSSNAsc;
    private PreparedStatement employeeSSNDesc;
    private PreparedStatement bossSSNAsc;
    private PreparedStatement bossSSNDesc;
    private PreparedStatement employeeSurnameAsc;
    private PreparedStatement employeeSurnameDesc;
    private PreparedStatement bossSurnameAsc;
    private PreparedStatement bossSurnameDesc;

    private String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    private com.mysql.jdbc.Connection c = null;

    public SalesSearchQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );

            everyEmployeeAsc = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee"  );

            everyEmployeeDesc = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee DESC" );

            employeeUsernameAsc = c.prepareStatement ("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE UserName = ?");

            employeeUsernameDesc = c.prepareStatement ("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE UserName = ? DESC");

            bossUsernameAsc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE UserName = ?");

            bossUsernameDesc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE UserName = ? DESC");

            employeeSSNAsc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE SSN = ?");

            employeeSSNDesc = c.prepareStatement( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE SSN = ? DESC" );

            bossSSNAsc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE SSN = ?");

            bossSSNDesc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE SSN = ? DESC");

            employeeSurnameAsc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE Surname = ?");

            employeeSurnameDesc = c.prepareStatement("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE Surname = ? DESC" );

            bossSurnameAsc = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE Surname = ?" );

            bossSurnameDesc = c.prepareStatement ("SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE Surname = ? DESC");
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

                    employeeUsernameDesc.setString ( 1, surname );

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
