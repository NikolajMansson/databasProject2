package sample;

import java.util.ArrayList;

public class Game {

    private String gameTitle;

    private String genre;

    private String developer;

    private double price;

    private String descriptionOfPlot;

    private ArrayList<Platform> platformList = new ArrayList<> (  );


    public Game(String gameTitle, String genre, String developer, double price, String descriptionOfPlot, Platform platform) {
        this.gameTitle=gameTitle;
        this.genre=genre;
        this.developer=developer;
        this.price = price;
        this.descriptionOfPlot = descriptionOfPlot;
        platformList.add(platform);
    }

    public void addPlatformToList(Platform platform){
        platformList.add(platform);
    }

    public ArrayList<Platform> getPlatformList(){
        return platformList;
    }
    public String getGameTitle() {
        return gameTitle;
    }

    public String getGenre() {
        return genre;
    }

    public String getDeveloper() {
        return developer;
    }

    public double getPrice() {
        return price;
    }

    public String getDescriptionOfPlot() {
        return descriptionOfPlot;
    }
}