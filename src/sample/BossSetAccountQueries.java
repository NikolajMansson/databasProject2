package sample;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nikolaj on 2017-05-05.
 */
public class BossSetAccountQueries extends DBConnection {

    private PreparedStatement searchForPasswordBoss;



    private String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    private String correctPassword = null;
    private com.mysql.jdbc.Connection c = null;

    public BossSetAccountQueries() {

        try {
            this.c = (com.mysql.jdbc.Connection) DriverManager.getConnection ( DBURL );

            searchForPasswordBoss = c.prepareStatement ( "SELECT UserPassword FROM Employee WHERE UserName = ?" );


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

    public boolean searchForPasswordBoss(String id, String password) {
        ResultSet rs = null;
        try {
            searchForPasswordBoss.setString ( 1, id );
            rs = searchForPasswordBoss.executeQuery ();
            while (rs.next ()) {
                this.correctPassword = String.format ( rs.getString ( 1 ) );
            }

            if (correctPassword.equals ( password )) {
                return true;
            }

        } catch (SQLException ex) {
            System.err.println ( "error on executing the query" );
        }
        finally {
                close ();
        }
        return false;
    }

}
