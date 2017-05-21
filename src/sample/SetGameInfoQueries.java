package sample;

import javafx.scene.control.Alert;

import java.sql.*;

/**
 * Created by Nikolaj on 2017-04-25.
 */
public class SetGameInfoQueries extends DBConnection {

    private PreparedStatement insertNewGame;
    private PreparedStatement insertNewItem;
    private PreparedStatement insertNewItemCollection;
    private PreparedStatement insertNewItemCollectionRelease;
    private PreparedStatement insertNewPlatform;
    private PreparedStatement removeGame;

    private int releaseDate = 0;

    private com.mysql.jdbc.Connection c = null;

    private int primaryKey = 0;

    public SetGameInfoQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );
            insertNewItem = c.prepareStatement ( "INSERT INTO Item(ItemCollection_ArticleNo) values(?);" );
            insertNewItemCollectionRelease = c.prepareStatement ( "insert into ItemCollection(Game_Title, Platform_Abbreviation, Price) values ((SELECT Title FROM Game WHERE Title=?), (SELECT Abbreviation FROM Platform WHERE Abbreviation=?), ?);", insertNewItemCollection.RETURN_GENERATED_KEYS );
            insertNewItemCollection = c.prepareStatement ( "insert into ItemCollection(Game_Title, Platform_Abbreviation, Price, ReleaseDate) values ((SELECT Title FROM Game WHERE Title=?), (SELECT Abbreviation FROM Platform WHERE Abbreviation=?), ?, ?);", insertNewItemCollection.RETURN_GENERATED_KEYS );
            insertNewGame = c.prepareStatement ( "INSERT INTO Game (Title, Genre, Developer, DescriptionOfPlot) VALUES (?, ?, ?, ?);" );
            insertNewPlatform = c.prepareStatement ( "INSERT INTO Platform VALUES (?, ?, ?);" );
            removeGame = c.prepareStatement ( "UPDATE Item SET IsSold=1 WHERE (ItemCollection_ArticleNo = ?); " );
        } catch (SQLException ex) {
            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "No connection to database" );

            validAlert.showAndWait ();

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

        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                return;
            }
            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "Enter a valid info on game" );

            validAlert.showAndWait ();
        }

    }
    public void pushCopies(int amount, int articleID){
        try {
            for (int i = 0; i < amount; i++) {
                insertNewItem.setInt ( 1, articleID );
                insertNewItem.executeUpdate ();
            }
        }
        catch(SQLException e){
            e.printStackTrace ();
        }
    }
    public void addItemToList(String abbreviation, String price, String amount, String title, int release) {
        int amountInt = Integer.parseInt ( amount );
        this.releaseDate = release;

            try {
                insertNewItemCollection.setString ( 1, title );
                insertNewItemCollection.setString ( 2, abbreviation );
                insertNewItemCollection.setDouble ( 3, Double.parseDouble ( price ) );
                insertNewItemCollection.setInt ( 4, releaseDate );
                insertNewItemCollection.executeUpdate ();
                ResultSet rs = insertNewItemCollection.getGeneratedKeys ();

                if (rs != null && rs.next ()) {
                    this.primaryKey = rs.getInt ( 1 );
                }

                for (int i = 0; i < amountInt; i++) {
                    insertNewItem.setInt ( 1, primaryKey );
                    insertNewItem.executeUpdate ();

                }
            } catch (
                    SQLException e)

            {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    return;
                }
                Alert validAlert = new Alert ( Alert.AlertType.ERROR, "Enter a valid info on item" );

                validAlert.showAndWait ();
            }
        }

    

    public void addPlatformToList(String abbreviation, String fullname, String maker) {

        try {
            insertNewPlatform.setString ( 1, abbreviation );
            insertNewPlatform.setString ( 2, fullname );
            insertNewPlatform.setString ( 3, maker );
            insertNewPlatform.executeUpdate ();

        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                return;
            }
            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "Enter a valid info on platform" );

            validAlert.showAndWait ();
        }
    }

    public void removeGame(int articleNo) {

        try {
            removeGame.setInt ( 1, articleNo );
            removeGame.executeUpdate ();
        } catch (SQLException e) {
            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "Not possible to remove game with this title" );

            validAlert.showAndWait ();

        }
    }
}
