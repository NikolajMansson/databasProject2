package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {

    public DBConnection() {
    }
    private java.sql.Statement statement;
    private String answerToQuery;
    private String DBURL=null;

    public void setDBURL(String id, String password){
        this.DBURL = String.format("jdbc:mysql://127.0.0.1:3306/GameStore?user="+id+"&password="+password+")");
    }

    public void DBConnection() {
        try {
            Connection c = (Connection) DriverManager.getConnection ( DBURL );
            this.statement = c.createStatement ();
        } catch (SQLException ex) {
            System.out.println ( "the connection fails" );
        }
    }
    public void setQuery(String query) {
        try {
            ResultSet rs = statement.executeQuery ( query );
        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }

    }

    public void setGameSearch(String query) {
        try {
            ResultSet rs = statement.executeQuery ( query );
            ArrayList<String> result = new ArrayList<> (  );

            while (rs.next ()) {
                String toArray =  String.format("Title: " + rs.getString ( 1 ) + "Genre: " + rs.getString ( 2 ) + "Developer: " + rs.getString ( 3 ) + "Price: " + rs.getString ( 4 ) + "Description of Plot: " + rs.getString ( 5 ) );

                result.add(toArray);

            }
            StringBuilder builder = new StringBuilder ();
            for (String string : result) {

                if (builder.length () > 0) {
                    builder.append ( " " );
                }

                builder.append ( String.format ( "%s%n", string ) );

            }
            this.answerToQuery = builder.toString ();

        } catch (SQLException ex) {
            System.out.println ( "error on executing the query" );
        }

    }
    public String getAnswerToQuery() {
        return answerToQuery;
    }


}