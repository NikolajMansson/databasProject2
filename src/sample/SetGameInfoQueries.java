package sample;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Nikolaj on 2017-04-25.
 */
public class SetGameInfoQueries extends DBConnection{

    private PreparedStatement insertNewGame;
    private PreparedStatement insertNewItem;
    private PreparedStatement insertNewPlatform;
    private PreparedStatement decreaseItemAmount;
    private PreparedStatement removeGame;

    private com.mysql.jdbc.Connection c = null;

    public SetGameInfoQueries() {

        try {

            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );
            insertNewItem = c.prepareStatement ( "INSERT INTO Item(Game_Title, Platform_Abbreviation, price, AmountOfItemsInStock) VALUES ((SELECT Title FROM Game WHERE Title = ?), (SELECT Abbreviation FROM Platform WHERE Abbreviation = ?), ?, ?);" );
            insertNewGame = c.prepareStatement ( "INSERT INTO Game (Title, Genre, Developer, DescriptionOfPlot) VALUES (?, ?, ?, ?);" );
            insertNewPlatform = c.prepareStatement ( "INSERT INTO Platform VALUES (?, ?, ?);" );
            decreaseItemAmount = c.prepareStatement ( "UPDATE Item SET AmountOfItemsInStock=AmountOfItemsInStock-? WHERE ArticleNo = ?;" );
            removeGame = c.prepareStatement ( "UPDATE Item SET AmountOfItemsInStock = 0 WHERE (Game_Title = ?); " );
        } catch (SQLException ex) {
            System.err.println ( "the connection fails" );
        }

    }

    public void close() {
        try {
            c.close ();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }
    }

    public void addGameToList(String title, String genre, String developer, String descriptionOfPlot) {
        try {
            insertNewGame.setString ( 1, title );
            insertNewGame.setString ( 2, genre );
            insertNewGame.setString ( 3, developer );
            insertNewGame.setString ( 4, descriptionOfPlot );
            insertNewGame.executeUpdate ();

        } catch (SQLException ex) {
            System.err.println ( "error on executing the query" );
        }

    }

    public void addItemToList(String abbreviation, String price, String amount, String title) {

        try {
            insertNewItem.setString ( 1, title );
            insertNewItem.setString ( 2, abbreviation );
            insertNewItem.setDouble ( 3, Double.parseDouble ( price ) );
            insertNewItem.setInt ( 4, Integer.parseInt ( amount ) );
            insertNewItem.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void addPlatformToList(String abbreviation, String fullname, String maker) {

        try {
            insertNewPlatform.setString ( 1, abbreviation );
            insertNewPlatform.setString ( 2, fullname );
            insertNewPlatform.setString ( 3, maker );
            insertNewPlatform.executeUpdate ();

        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void decreaseItemAmount(int amountOfItems, int articleId) {
        try {
            decreaseItemAmount.setInt ( 1, amountOfItems );
            decreaseItemAmount.setInt ( 2, articleId );
            decreaseItemAmount.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void removeGame(String title) {

        try {
            removeGame.setString ( 1, title );
            removeGame.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
}
