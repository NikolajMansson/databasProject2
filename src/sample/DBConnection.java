package sample;

import com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {


    String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    Statement statement;
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
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "INSERT INTO Employees(SSN, FirstName, Surname, EmploymentDate, GamesSold, Income, Email, UserName, UserPassword, UserType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);" )) {
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
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "UPDATE Item SET amountOfItems=? WHERE ArticleID = ?;" )) {
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
    }

    public void removeEmployee(String userName) {
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "DELETE FROM Employees WHERE UserName = ?" )) {
                pst.setString ( 1, userName );
                pst.executeUpdate ();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void increaseEmployeeIncome(String userName) {
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try (java.sql.PreparedStatement pst = c.prepareStatement ( "UPDATE Employees SET Income=? WHERE UserName = ?;" )) {
                pst.setString ( 1, userName );
                pst.executeUpdate ();
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }


    public void addOrderToList(Order order) {
        // TODO implement here
    }

    public String searchForName(String id) {
        String firstName;
        try {
            ResultSet rs = statement.executeQuery ( "SELECT FirstName FROM Employees WHERE UserName ='" + id + "'" );


            while (rs.next ()) {
                firstName = String.format ( rs.getString ( 1 ) );
                return firstName;

            }

        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }
        return null;
    }


    //unstable code
    public void defaultSearch() {
        try {
            ArrayList<String> gameTitleInLibrary = new ArrayList<> ();
            ArrayList<Integer> articlenumbers = new ArrayList<> ();
            ArrayList<String> platformlist = new ArrayList<> ();
            ArrayList<Double> prices = new ArrayList<> ();

            ResultSet rs = statement.executeQuery ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title ORDER BY ArticleNo" );

            while (rs.next ()) {

                gameTitleInLibrary.add ( rs.getString ( 1 ) );
                articlenumbers.add ( rs.getInt ( 1 ) );
                platformlist.add ( rs.getString ( 1 ) );
                prices.add ( rs.getDouble ( 1 ) );
            }
        } catch (SQLException e) {
            System.out.println ( "error on executing the query" );
        }


    }

    public ArrayList<SearchResultItem> getItemDefaultSearch() {
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                PreparedStatement pst = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title ORDER BY ArticleNo" );


                    resultSet = pst.executeQuery ();


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

    public ArrayList<SearchResultItem> getItemTextSearch(String text) {
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {
            String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            try {
                PreparedStatement pst = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Game_Title LIKE ? ORDER BY ArticleNo ?" );

                pst.setString ( 1, text + "%" );

                resultSet = pst.executeQuery ();


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

}



