package sample;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {


    String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";

    Statement statement;
    String correctPassword = null;
    public void setDBURL(String id, String password){
        this.DBURL = String.format("jdbc:mysql://127.0.0.1:3306/GameShop?user="+id+"&password="+password+")");
    }

    public DBConnection() {
        try {
            Connection c = (Connection) DriverManager.getConnection(DBURL);
            statement = c.createStatement();
        } catch (SQLException ex) {
            System.out.println("the connection fails");
        }
    }

    public boolean searchForPassword(String name, String password)
    {
        try {
            ResultSet rs = statement.executeQuery("SELECT UserPassword FROM Employees WHERE UserName ='" + name + "'");


            while(rs.next())
            {
                this.correctPassword = String.format(rs.getString(1));

            }

            if(correctPassword.equals( password )){
                return true;
            }


        }
        catch (SQLException ex)
        {
            System.out.println("error on executing the query");
        }
        return false;
    }

    public void searchForGames(String gameTitle)
    {
        try {
            ResultSet rs = statement.executeQuery("SELECT Game_Title FROM Item WHERE Game_Title ='" + gameTitle + "'");

        }
        catch (SQLException ex)
        {
            System.out.println("error on executing the query");
        }
    }

    public void addGameToList(String gameTitle, String genre, String developer, String description, String platform)
    {
        try {
            ResultSet rs = statement.executeQuery("INSERT INTO Game (Title, Genre, Developer, DescriptionOfPlot) VALUES ('" + gameTitle +"', '" + genre +"', '" + developer + "', '" + description + "');");

        }
        catch (SQLException ex)
        {
            System.out.println("error on executing the query");
        }
    }
}
