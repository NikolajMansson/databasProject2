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
public class ItemSearchQueries extends DBConnection{

    private PreparedStatement defaultItemAsc;
    private PreparedStatement itemTitleAsc;
    private PreparedStatement itemPlatformAsc;
    private PreparedStatement itemDeveloperAsc;

    private com.mysql.jdbc.Connection c = null;

    public ItemSearchQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );

            defaultItemAsc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price, count(IDItem) AS rows, ReleaseDate FROM ItemCollection, Item, Game WHERE ArticleNo=ItemCollection_ArticleNo AND Game_Title = Title AND GuestOrder_OrderNumber IS NULL AND RegularCustomerOrder_OrderNumber IS NULL AND IsSold= 0 GROUP BY ArticleNo;" );

            itemTitleAsc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price, count(IDItem) AS rows, ReleaseDate FROM ItemCollection, Item, Game WHERE ArticleNo=ItemCollection_ArticleNo AND Game_Title = Title AND Game_Title LIKE ? AND GuestOrder_OrderNumber IS NULL AND RegularCustomerOrder_OrderNumber IS NULL AND IsSold= 0 GROUP BY ArticleNo;" );

            itemPlatformAsc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price, count(IDItem) AS rows, ReleaseDate FROM ItemCollection, Item, Game WHERE ArticleNo=ItemCollection_ArticleNo AND Game_Title = Title AND Platform_Abbreviation LIKE ? AND GuestOrder_OrderNumber IS NULL AND RegularCustomerOrder_OrderNumber IS NULL AND IsSold= 0 GROUP BY ArticleNo;" );

            itemDeveloperAsc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price, count(IDItem) AS rows, ReleaseDate FROM ItemCollection, Item, Game WHERE ArticleNo=ItemCollection_ArticleNo AND Game_Title = Title AND Developer LIKE ? AND GuestOrder_OrderNumber IS NULL AND RegularCustomerOrder_OrderNumber IS NULL AND IsSold= 0 GROUP BY ArticleNo;" );

        } catch (SQLException ex) {
            Alert validAlert = new Alert ( Alert.AlertType.ERROR, "No connection to database" );

            validAlert.showAndWait ();

        }

    }

    public ArrayList<SearchResultItem> getItemDefaultSearch() {

        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;

        try {

                resultSet = defaultItemAsc.executeQuery ();


            results = new ArrayList<> ();

            while (resultSet.next ()) {
                results.add ( new SearchResultItem (
                        resultSet.getInt ( "ArticleNo" ),
                        resultSet.getString ( "Game_Title" ),
                        resultSet.getString ( "Platform_Abbreviation" ),
                        resultSet.getString ( "Developer" ),
                        resultSet.getString ( "DescriptionOfPlot" ),
                        resultSet.getDouble ( "Price" ),
                        resultSet.getInt ("rows"),
                        resultSet.getInt("ReleaseDate")
                ) );
            }
            return results;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        } finally {
            close ();
        }

        return null;
    }


    public ArrayList<SearchResultItem> getItemTitleSearch(String text) {
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {

                itemTitleAsc.setString ( 1, text + "%" );

                resultSet = itemTitleAsc.executeQuery ();

            results = new ArrayList<> ();

            while (resultSet.next ()) {
                results.add ( new SearchResultItem (
                        resultSet.getInt ( "ArticleNo" ),
                        resultSet.getString ( "Game_Title" ),
                        resultSet.getString ( "Platform_Abbreviation" ),
                        resultSet.getString ( "Developer" ),
                        resultSet.getString ( "DescriptionOfPlot" ),
                        resultSet.getDouble ( "Price" ),
                        resultSet.getInt ( "rows" ),
                        resultSet.getInt("ReleaseDate")) );
            }
            return results;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }finally {
            close ();
        }
        return null;
    }

    public ArrayList<SearchResultItem> getItemPlatformSearch(String text) {
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {

                itemPlatformAsc.setString ( 1, text + "%" );

                resultSet = itemPlatformAsc.executeQuery ();

            results = new ArrayList<> ();

            while (resultSet.next ()) {
                results.add ( new SearchResultItem (
                        resultSet.getInt ( "ArticleNo" ),
                        resultSet.getString ( "Game_Title" ),
                        resultSet.getString ( "Platform_Abbreviation" ),
                        resultSet.getString ( "Developer" ),
                        resultSet.getString ( "DescriptionOfPlot" ),
                        resultSet.getDouble ( "Price" ),
                        resultSet.getInt ( "rows" ),
                        resultSet.getInt("ReleaseDate")) );
            }
            return results;


        } catch (SQLException sqlException) {
            sqlException.printStackTrace ();
        }finally {
            close ();
        }
        return null;
    }

    public ArrayList<SearchResultItem> getItemDeveloperSearch(String text) {
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {


                itemDeveloperAsc.setString ( 1, text + "%" );

                resultSet = itemDeveloperAsc.executeQuery ();


            results = new ArrayList<> ();

            while (resultSet.next ()) {
                results.add ( new SearchResultItem (
                        resultSet.getInt ( "ArticleNo" ),
                        resultSet.getString ( "Game_Title" ),
                        resultSet.getString ( "Platform_Abbreviation" ),
                        resultSet.getString ( "Developer" ),
                        resultSet.getString ( "DescriptionOfPlot" ),
                        resultSet.getDouble ( "Price" ),
                        resultSet.getInt ( "rows" ),
                        resultSet.getInt("ReleaseDate")) );
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
