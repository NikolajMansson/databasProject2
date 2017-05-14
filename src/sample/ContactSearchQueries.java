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

    private PreparedStatement employeeUserNameAsc;

    private PreparedStatement employeeSSNAsc;

    private PreparedStatement employeeSurnameAsc;



    private com.mysql.jdbc.Connection c = null;

    public ContactSearchQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );

            everyEmployeeAsc = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName, isEmployed FROM Employee ORDER BY UserName " );



            employeeUserNameAsc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName, isEmployed FROM Employee WHERE UserName = ? ORDER BY UserName ");



            employeeSSNAsc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName, isEmployed FROM Employee WHERE SSN = ? ORDER BY SSN ");



            employeeSurnameAsc = c.prepareStatement("SELECT SSN, FirstName, Surname, Email, UserName, isEmployed FROM Employee WHERE Surname = ? ORDER BY Surname ");



        } catch (SQLException ex) {
            System.err.println ( "the connection fails" );
        }

    }

    public ArrayList<Employee> getEmployeeDefaultContactSearch() {

        ArrayList<Employee> results = null;
        ResultSet resultSet = null;

            try {

                   resultSet = everyEmployeeAsc.executeQuery ();


                results = new ArrayList<> ();

                while (resultSet.next ()) {
                    results.add ( new Employee (
                            resultSet.getInt ( "SSN" ),
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getString ( "Email" ),
                            resultSet.getString ( "UserName" ),
                            resultSet.getInt("isEmployed"))
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
    public ArrayList<Employee> getEmployeeContactUserNameSearch(String userName) {
        ArrayList<Employee> results = null;
        ResultSet resultSet = null;

            try {
                    employeeUserNameAsc.setString ( 1, userName );

                    resultSet = employeeUserNameAsc.executeQuery ();


                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
                            resultSet.getInt ( "SSN" ),
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getString ( "Email" ),
                            resultSet.getString ( "UserName" ),
                            resultSet.getInt("isEmployed"))
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



    public ArrayList<Employee> getEmployeeContactSSNSearch(String SSN) {

        ArrayList<Employee> results = null;
        ResultSet resultSet = null;

            try {

                    employeeSSNAsc.setString ( 1, SSN );

                    resultSet = employeeSSNAsc.executeQuery ();


                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
                            resultSet.getInt ( "SSN" ),
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getString ( "Email" ),
                            resultSet.getString ( "UserName" ) ,
                            resultSet.getInt("isEmployed"))
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


    public ArrayList<Employee> getEmployeeContactSurnameSearch(String surname) {
        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
        try {

                    employeeSurnameAsc.setString ( 1, surname );

                    resultSet = employeeSurnameAsc.executeQuery ();


                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
                            resultSet.getInt ( "SSN" ),
                            resultSet.getString ( "FirstName" ),
                            resultSet.getString ( "Surname" ),
                            resultSet.getString ( "Email" ),
                            resultSet.getString ( "UserName" ) ,
                            resultSet.getInt("isEmployed"))
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
