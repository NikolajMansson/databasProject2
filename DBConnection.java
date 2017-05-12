package sample;

import com.mysql.jdbc.Connection;

public class DBConnection {


    private String DBURL = "jdbc:mysql://127.0.0.1:3306/GameShop?user=root&password=root";
    private Connection c;


    public void setDBURL(String id, String password) {
        this.DBURL = String.format ( "jdbc:mysql://127.0.0.1:3306/GameShop?user=" + id + "&password=" + password + ")" );
    }

    public DBConnection() {
    }

}



