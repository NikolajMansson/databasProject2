package sample;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nikolaj on 2017-05-05.
 */
public class ContactSearchQueries extends DBConnection{

    private PreparedStatement everyEmployeeAsc;
    private PreparedStatement everyEmployeeDesc;
    private PreparedStatement employeeUserNameAsc;
    private PreparedStatement employeeUserNameDesc;
    private PreparedStatement bossUserNameAsc;
    private PreparedStatement bossUserNameDesc;
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

    public ContactSearchQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );

            everyEmployeeAsc = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Employee ORDER BY UserName " );

            everyEmployeeDesc = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Employee ORDER BY UserName DESC" );

            employeeUserNameAsc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE UserName = ? ORDER BY UserName ");

            employeeUserNameDesc = c.prepareStatement( "SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE UserName = ? ORDER BY UserName DESC" );

            bossUserNameAsc = c.prepareStatement( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE UserName = ? ORDER BY UserName " );

            bossUserNameDesc = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE UserName = ? ORDER BY UserName DESC" );

            employeeSSNAsc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE SSN = ? ORDER BY SSN ");

            employeeSSNDesc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE SSN = ? ORDER BY SSN DESC");

            bossSSNAsc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE SSN = ? ORDER BY SSN ");

            bossSSNDesc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE SSN = ? ORDER BY SSN DESC");

            employeeSurnameAsc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE Surname = ? ORDER BY Surname ");

            employeeSurnameDesc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE Surname = ? ORDER BY Surname DESC");

            bossSurnameAsc = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE Surname = ? ORDER BY Surname"  );

            bossSurnameDesc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE Surname = ? ORDER BY Surname DESC");

        } catch (SQLException ex) {
            System.err.println ( "the connection fails" );
        }

    }

    public ArrayList<Employee> getBossDefaultContactSearch(boolean ascending) {

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
                            resultSet.getInt ( "SSN" ),
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getString ( "Email" ),
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
    public ArrayList<Employee> getEmployeeContactUserNameSearch(String userName, boolean ascending) {
        ArrayList<Employee> results = null;
        ResultSet resultSet = null;

            try {
                if (ascending == true) {
                    employeeUserNameAsc.setString ( 1, userName );

                    resultSet = employeeUserNameAsc.executeQuery ();
                } else {

                    employeeUserNameDesc.setString ( 1, userName );

                    resultSet = employeeUserNameDesc.executeQuery ();
                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
                            resultSet.getInt ( "SSN" ),
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getString ( "Email" ),
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



    public ArrayList<Employee> getEmployeeContactSSNSearch(String SSN, boolean ascending) {

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
                            resultSet.getInt ( "SSN" ),
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getString ( "Email" ),
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


    public ArrayList<Employee> getEmployeeContactSurnameSearch(String surname, boolean ascending) {
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
                            resultSet.getInt ( "SSN" ),
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getString ( "Email" ),
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

    public void close() {
        try {
            c.close ();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }
    }


}
