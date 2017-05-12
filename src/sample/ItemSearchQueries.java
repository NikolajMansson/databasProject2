package sample;

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
    private PreparedStatement defaultItemDesc;
    private PreparedStatement itemTitleAsc;
    private PreparedStatement itemTitleDesc;
    private PreparedStatement itemPlatformAsc;
    private PreparedStatement itemPlatformDesc;
    private PreparedStatement itemDeveloperAsc;
    private PreparedStatement itemDeveloperDesc;


    public String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    public com.mysql.jdbc.Connection c = null;

    public ItemSearchQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );

            defaultItemAsc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND AmountOfItemsInStock > 0 ORDER BY ArticleNo" );

            defaultItemDesc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND AmountOfItemsInStock > 0 ORDER BY ArticleNo DESC" );

            itemTitleAsc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Game_Title LIKE ? AND AmountOfItemsInStock > 0 ORDER BY ArticleNo" );

            itemTitleDesc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Game_Title LIKE ? AND AmountOfItemsInStock > 0 ORDER BY ArticleNo DESC" );

            itemPlatformAsc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Platform_Abbreviation LIKE ? AND AmountOfItemsInStock > 0 ORDER BY ArticleNo" );

            itemPlatformDesc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Platform_Abbreviation LIKE ? AND AmountOfItemsInStock > 0 ORDER BY ArticleNo DESC" );

            itemDeveloperAsc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Developer LIKE ? AND AmountOfItemsInStock > 0 ORDER BY ArticleNo" );

            itemDeveloperDesc = c.prepareStatement ( "SELECT ArticleNo, Game_Title, Platform_Abbreviation, Developer, DescriptionOfPlot, Price FROM Item, Game WHERE Title=Game_Title AND Developer LIKE ? AND AmountOfItemsInStock > 0 ORDER BY ArticleNo DESC" );


        } catch (SQLException ex) {
            System.err.println ( "the connection fails" );
        }

    }

    public ArrayList<SearchResultItem> getItemDefaultSearch(boolean ascending) {

        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;

        try {
            if (ascending == true) {
                resultSet = defaultItemAsc.executeQuery ();
            } else {
                resultSet = defaultItemDesc.executeQuery ();
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
            close ();
        }

        return null;
    }


    public ArrayList<SearchResultItem> getItemTitleSearch(String text, boolean ascending) {
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {
            if (ascending == true) {
                itemTitleAsc.setString ( 1, text + "%" );

                resultSet = itemTitleAsc.executeQuery ();
            } else {

                itemTitleDesc.setString ( 1, text + "%" );

                resultSet = itemTitleDesc.executeQuery ();

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
        }finally {
            close ();
        }
        return null;
    }

    public ArrayList<SearchResultItem> getItemPlatformSearch(String text, boolean ascending) {
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {
            if (ascending == true) {
                itemPlatformAsc.setString ( 1, text + "%" );

                resultSet = itemPlatformAsc.executeQuery ();
            } else {
                itemPlatformDesc.setString ( 1, text + "%" );

                resultSet = itemPlatformDesc.executeQuery ();

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
        }finally {
            close ();
        }
        return null;
    }

    public ArrayList<SearchResultItem> getItemDeveloperSearch(String text, boolean ascending) {
        ArrayList<SearchResultItem> results = null;
        ResultSet resultSet = null;
        try {
            if (ascending == true) {

                itemDeveloperAsc.setString ( 1, text + "%" );

                resultSet = itemDeveloperAsc.executeQuery ();
            } else {

                itemDeveloperDesc.setString ( 1, text + "%" );

                resultSet = itemDeveloperDesc.executeQuery ();

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
