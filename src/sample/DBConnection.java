package sample;

import com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {


    String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    Statement statement = null;
    String correctPassword = null;

    public void setDBURL(String id, String password) {
        this.DBURL = String.format ( "jdbc:mysql://127.0.0.1:3306/GameShop?user=" + id + "&password=" + password + ")" );
    }

    public DBConnection() {
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            statement = c.createStatement ();
        } catch (SQLException ex) {
            System.out.println ( "the connection fails" );
        }
    }

    public boolean searchForPasswordEmployee(String id, String password) {
        try {
            ResultSet rs = statement.executeQuery ( "SELECT UserPassword FROM Employees WHERE UserName ='" + id + "'" );


            while (rs.next ()) {
                this.correctPassword = String.format ( rs.getString ( 1 ) );

            }

            if (correctPassword.equals ( password )) {
                return true;
            }


        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }
        return false;
    }

    public boolean searchForPasswordCustomer(String id, String password) {
        try {
            ResultSet rs = statement.executeQuery ( "SELECT UserPassword FROM Customer WHERE UserName ='" + id + "'" );


            while (rs.next ()) {
                this.correctPassword = String.format ( rs.getString ( 1 ) );

            }

            if (correctPassword.equals ( password )) {
                return true;
            }


        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }
        return false;
    }


    public void addEmployeeToList(String ssn, String firstName, String surname, String employmentDate, String email, String username, String password) {

        try {

            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "INSERT INTO Employee(SSN, FirstName, Surname, EmploymentDate, GamesSold, Income, Email, UserName, UserPassword, UserType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);" )) {
                pst.setInt ( 1, Integer.parseInt ( ssn ) );
                pst.setString ( 2, firstName );
                pst.setString ( 3, surname );
                pst.setInt ( 4, Integer.parseInt ( employmentDate ) );
                pst.setInt ( 5, 0 );
                pst.setDouble ( 6, 0.00 );
                pst.setString ( 7, email );
                pst.setString ( 8, username );
                pst.setString ( 9, password );
                pst.setString ( 10, "salesman" );
                pst.executeUpdate ();


            } catch (SQLException ex) {

                ex.printStackTrace ();

            }

        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void addCustomerToList(String ssn, String firstName, String surname, String registrationDate, String email, String username, String password) {

        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "INSERT INTO Customer(SSN, FirstName, Surname, RegistrationDate, UserName, UserPassword, Email) VALUES (?, ?, ?, ?, ?, ?, ?);" )) {
                pst.setInt ( 1, Integer.parseInt ( ssn ) );
                pst.setString ( 2, firstName );
                pst.setString ( 3, surname );
                pst.setInt ( 4, Integer.parseInt ( registrationDate ) );
                pst.setString ( 5, username );
                pst.setString ( 6, password );
                pst.setString ( 7, email );
                pst.executeUpdate ();
            }


        } catch (SQLException e) {

            e.printStackTrace ();

        }

    }

    public void addGameToList(String title, String genre, String developer, String descriptionOfPlot) {

        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "INSERT INTO Game (Title, Genre, Developer, DescriptionOfPlot) VALUES (?, ?, ?, ?);" )) {
                pst.setString ( 1, title );
                pst.setString ( 2, genre );
                pst.setString ( 3, developer );
                pst.setString ( 4, descriptionOfPlot );
                pst.executeUpdate ();

            } catch (SQLException ex) {
                System.out.println ( "error on executing the query" );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void addItemToList(String abbreviation, String price, String amount, String title) {
        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "INSERT INTO Item(Game_Title, Platform_Abbreviation, price, amountOfItems) VALUES ((SELECT Title FROM Game WHERE Title = ?), (SELECT Abbreviation FROM Platform WHERE Abbreviation = ?), ?, ?);" )) {

                pst.setString ( 1, title );
                pst.setString ( 2, abbreviation );
                pst.setDouble ( 3, Double.parseDouble ( price ) );
                pst.setInt ( 4, Integer.parseInt ( amount ) );
                pst.executeUpdate ();
            }

        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void addPlatformToList(String abbreviation, String fullname, String maker) {
        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "INSERT INTO Platform VALUES (?, ?, ?);" )) {
                pst.setString ( 1, abbreviation );
                pst.setString ( 2, fullname );
                pst.setString ( 3, maker );
                pst.executeUpdate ();

            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void decreaseItemAmount(int amountOfItems, int articleId) {
        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "UPDATE Item SET amountOfItems=? WHERE ArticleNo = ?;" )) {
                pst.setInt ( 1, amountOfItems );
                pst.setInt ( 2, articleId );
                pst.executeUpdate ();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void removeGame(String title) {

        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "DELETE FROM Game WHERE Title = ?;" )) {
                pst.setString ( 1, title );
                pst.executeUpdate ();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "DELETE FROM Item WHERE Game_Title = ?;" )) {
                pst.setString ( 1, title );
                pst.executeUpdate ();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }

    public void removeEmployee(String userName) {
        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "DELETE FROM Employee WHERE UserName = ?" )) {
                pst.setString ( 1, userName );
                pst.executeUpdate ();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void removeBoss(String userName) {
        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "DELETE FROM Boss WHERE UserName = ?" )) {
                pst.setString ( 1, userName );
                pst.executeUpdate ();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void increaseBossIncome(double price, String userName) {
        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "UPDATE Boss SET Income=Income + ? WHERE UserName = ?;" )) {
                pst.setDouble ( 1, price );
                pst.setString ( 2, userName );
                pst.executeUpdate ();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }

    public void increaseGameSoldBoss(int gamesSold, String userName){
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        Connection c = null;
        try {
            c = (Connection) DriverManager.getConnection ( DBURL );

        try (java.sql.PreparedStatement pst = c.prepareStatement ( "UPDATE Boss SET GamesSold=GamesSold + ? WHERE UserName = ?;" )) {
            pst.setInt ( 1, gamesSold );
            pst.setString ( 2, userName );
            pst.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    } catch (SQLException e) {
        e.printStackTrace ();
    }
    }

    public void increaseEmployeeIncome(double price, String userName) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "UPDATE Employees SET Income=Income + ? WHERE UserName = ?;" )) {
                pst.setDouble ( 1, price );
                pst.setString ( 2, userName );
                pst.executeUpdate ();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }
    public void increaseGameSoldEmployee(int gamesSold, String userName){
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        Connection c = null;
        try {
            c = (Connection) DriverManager.getConnection ( DBURL );

            try (java.sql.PreparedStatement pst = c.prepareStatement ( "UPDATE Employee SET GamesSold=GamesSold + ? WHERE UserName = ?;" )) {
                pst.setInt ( 1, gamesSold );
                pst.setString ( 2, userName );
                pst.executeUpdate ();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
    public void addRegularOrderToList(int dateOfOrder, String customerUserName, String employeeUserName, int itemArticleNo, String bossUserName, int quantity, double totalPricePerItem) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "insert into regularCustomerOrder(OrderNumber, DateOfOrder, Customer_UserName, Employees_UserName, Item_ArticleNo, Boss_UserName, Quantity, TotalPricePerItem) values(null, ?, (SELECT UserName FROM Customer WHERE UserName=?), (SELECT UserName FROM Employee WHERE UserName=?), (SELECT ArticleNo from Item where ArticleNo=?),(SELECT UserName from boss WHERE UserName=?), ?, ?)" )) {


                pst.setInt (1, dateOfOrder );
                pst.setString(2, customerUserName);
                pst.setString (3, employeeUserName );
                pst.setInt (4, itemArticleNo );
                pst.setString (5, bossUserName );
                pst.setInt(6, quantity);
                pst.setDouble (7, totalPricePerItem );
                pst.executeUpdate ();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void addGuestOrderToList(int dateOfOrder, String employeeUserName, int itemArticleNo, String bossUserName, double totalPricePerItem) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "insert into guestorder(OrderNumber, DateOfOrder, Employees_UserName, Item_ArticleNo, Boss_UserName, TotalPricePerItem) VALUES(null, ?, (SELECT UserName FROM Employee WHERE UserName=?), (SELECT ArticleNo from Item where ArticleNo=?),(SELECT UserName from boss WHERE UserName=?), ?)" )) {


                pst.setInt (1, dateOfOrder );
                pst.setString (2, employeeUserName );
                pst.setInt (3, itemArticleNo );
                pst.setString (4, bossUserName );
                pst.setDouble (5, totalPricePerItem );

            } catch (SQLException sqlException) {
                sqlException.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }


    public ArrayList<Boss> getBossDefaultContactSearch(boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Boss> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss" );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss DESC" );

                    resultSet = pst.executeQuery ();
                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Boss (
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Employee> getEmployeeContactUserNameSearch(String userName, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE UserName = ?" );

                    pst.setString ( 1, userName );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE UserName = ? DESC" );

                    pst.setString ( 1, userName );

                    resultSet = pst.executeQuery ();
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();

                }
            }
        } catch (
                SQLException e)

        {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Boss> getBossContactUserNameSearch(String userName, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Boss> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE UserName = ?" );

                    pst.setString ( 1, userName );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE UserName = ? DESC" );

                    pst.setString ( 1, userName );

                    resultSet = pst.executeQuery ();
                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Boss (
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Employee> getEmployeeContactSSNSearch(String SSN, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE SSN = ?" );

                    pst.setString ( 1, SSN );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE SSN = ? DESC" );

                    pst.setString ( 1, SSN );

                    resultSet = pst.executeQuery ();

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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Boss> getBossContactSSNSearch(String SSN, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Boss> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE SSN = ?" );

                    pst.setString ( 1, SSN );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE SSN = ? DESC" );

                    pst.setString ( 1, SSN );

                    resultSet = pst.executeQuery ();

                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Boss (
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Employee> getEmployeeContactSurnameSearch(String surname, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE Surname = ?" );

                    pst.setString ( 1, surname );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Employee WHERE Surname = ? DESC" );

                    pst.setString ( 1, surname );

                    resultSet = pst.executeQuery ();
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Boss> getBossContactSurnameSearch(String surname, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Boss> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE Surname = ?" );

                    pst.setString ( 1, surname );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT SSN, FirstName, Surname, Email, UserName FROM Boss WHERE Surname = ? DESC" );

                    pst.setString ( 1, surname );

                    resultSet = pst.executeQuery ();

                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Boss (
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Boss> getBossDefaultSalesSearch(boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Boss> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss" );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM BossDESC" );

                    resultSet = pst.executeQuery ();
                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Boss (
                            //SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Employee> getEmployeeSalesUserNameSearch(String userName, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE UserName = ?" );

                    pst.setString ( 1, userName );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE UserName = ? DESC" );

                    pst.setString ( 1, userName );

                    resultSet = pst.executeQuery ();
                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
                            //SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();

                }
            }
        } catch (
                SQLException e)

        {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Boss> getBossSalesUserNameSearch(String userName, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Boss> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE UserName = ?" );

                    pst.setString ( 1, userName );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE UserName = ? DESC" );

                    pst.setString ( 1, userName );

                    resultSet = pst.executeQuery ();
                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Boss (
                            //SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Employee> getEmployeeSalesSSNSearch(String SSN, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE SSN = ?" );

                    pst.setString ( 1, SSN );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE SSN = ? DESC" );

                    pst.setString ( 1, SSN );

                    resultSet = pst.executeQuery ();

                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
                            //SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Boss> getBossSalesSSNSearch(String SSN, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Boss> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE SSN = ?" );

                    pst.setString ( 1, SSN );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE SSN = ? DESC" );

                    pst.setString ( 1, SSN );

                    resultSet = pst.executeQuery ();

                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Boss (
                            //SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Employee> getEmployeeSalesSurnameSearch(String surname, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Employee> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE Surname = ?" );

                    pst.setString ( 1, surname );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee WHERE Surname = ? DESC" );

                    pst.setString ( 1, surname );

                    resultSet = pst.executeQuery ();
                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Employee (
//SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Employee
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<Boss> getBossSalesSurnameSearch(String surname, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<Boss> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE Surname = ?" );

                    pst.setString ( 1, surname );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss WHERE Surname = ? DESC" );

                    pst.setString ( 1, surname );

                    resultSet = pst.executeQuery ();
                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {

                    results.add ( new Boss (

                            //SELECT FirstName, Surname, EmploymentDate, GamesSold, Income, UserName FROM Boss
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
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<SearchResultItem> getItemDefaultSearch(boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );

            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title ORDER BY ArticleNo" );
                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title ORDER BY ArticleNo DESC" );
                    resultSet = pst.executeQuery ();
                }

                results = new ArrayList<> ();

                while (resultSet.next ()) {
                    results.add ( new SearchResultItem (
                            resultSet.getInt ( "ArticleNo" ),
                            resultSet.getString ( "Game_Title" ),
                            resultSet.getString ( "Platform_Abbreviation" ),
                            resultSet.getString ( "Developer" ),
                            resultSet.getString ( "DescriptionOfPlot" ),
                            resultSet.getDouble ( "Price" ) ) );
                }
                return results;
            } catch (SQLException sqlException) {
                sqlException.printStackTrace ();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        } finally {
            try {
                resultSet.close ();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace ();

            }
        }
        return null;
    }

    public ArrayList<SearchResultItem> getItemTitleSearch(String text, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Game_Title LIKE ? ORDER BY ArticleNo" );

                    pst.setString ( 1, text + "%" );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Game_Title LIKE ? ORDER BY ArticleNo DESC" );

                    pst.setString ( 1, text + "%" );

                    resultSet = pst.executeQuery ();

                }
                results = new ArrayList<> ();

                while (resultSet.next ()) {
                    results.add ( new SearchResultItem (
                            resultSet.getInt ( "ArticleNo" ),
                            resultSet.getString ( "Game_Title" ),
                            resultSet.getString ( "Platform_Abbreviation" ),
                            resultSet.getString ( "Developer" ),
                            resultSet.getString ( "DescriptionOfPlot" ),
                            resultSet.getDouble ( "Price" ) ) );
                }
                return results;


            } catch (SQLException sqlException) {
                sqlException.printStackTrace ();
            } finally {
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();

                }
            }
        } catch (
                SQLException e)

        {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<SearchResultItem> getItemPlatformSearch(String text, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Platform_Abbreviation LIKE ? ORDER BY ArticleNo" );

                    pst.setString ( 1, text + "%" );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Platform_Abbreviation LIKE ? ORDER BY ArticleNo DESC" );

                    pst.setString ( 1, text + "%" );

                    resultSet = pst.executeQuery ();

                }
                results = new ArrayList<> ();

                while (resultSet.next ()) {
                    results.add ( new SearchResultItem (
                            resultSet.getInt ( "ArticleNo" ),
                            resultSet.getString ( "Game_Title" ),
                            resultSet.getString ( "Platform_Abbreviation" ),
                            resultSet.getString ( "Developer" ),
                            resultSet.getString ( "DescriptionOfPlot" ),
                            resultSet.getDouble ( "Price" ) ) );
                }
                return results;


            } catch (SQLException sqlException) {
                sqlException.printStackTrace ();
            } finally {
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();

                }
            }
        } catch (
                SQLException e)

        {
            e.printStackTrace ();
        }
        return null;
    }

    public ArrayList<SearchResultItem> getItemDeveloperSearch(String text, boolean ascending) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                if (ascending == true) {
                    PreparedStatement pst = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Developer LIKE ? ORDER BY ArticleNo" );

                    pst.setString ( 1, text + "%" );

                    resultSet = pst.executeQuery ();
                } else {
                    PreparedStatement pst = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Developer LIKE ? ORDER BY ArticleNo DESC" );

                    pst.setString ( 1, text + "%" );

                    resultSet = pst.executeQuery ();

                }


                results = new ArrayList<> ();

                while (resultSet.next ()) {
                    results.add ( new SearchResultItem (
                            resultSet.getInt ( "ArticleNo" ),
                            resultSet.getString ( "Game_Title" ),
                            resultSet.getString ( "Platform_Abbreviation" ),
                            resultSet.getString ( "Developer" ),
                            resultSet.getString ( "DescriptionOfPlot" ),
                            resultSet.getDouble ( "Price" ) ) );
                }
                return results;


            } catch (SQLException sqlException) {
                sqlException.printStackTrace ();
            } finally {
                try {
                    resultSet.close ();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace ();

                }
            }
        } catch (
                SQLException e)

        {
            e.printStackTrace ();
        }
        return null;
    }

    public Item getSalesItem(int articleNo) {
        String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            ResultSet rs = statement.executeQuery ( "SELECT * FROM Item WHERE ArticleNo = " + articleNo + ";" );


            while (rs.next ()) {
                int articleNoText = rs.getInt ( 1 );
                String platformAbbreviation = String.format ( rs.getString ( 2 ) );
                double price = rs.getDouble ( 3 );
                int amountOfItems = rs.getInt ( 4 );
                String title = String.format ( rs.getString ( 5 ) );
                int releaseDate = rs.getInt ( 6 );
                Item item = new Item ( articleNoText, platformAbbreviation, price, amountOfItems, title, releaseDate );

                return item;

            }

        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }

        return null;
    }


}



