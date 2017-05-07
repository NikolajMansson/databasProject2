package sample;

import java.util.ArrayList;

public class Game {
    public Game(String gameTitle) {
        setGameTitle ( gameTitle );
    }

    private String gameTitle = null;

    private String genre = null;

    private String developer = null;

    private String descriptionOfPlot = null;

    private ArrayList<Platform> platformList = new ArrayList<> ();

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setDescriptionOfPlot(String descriptionOfPlot) {
        this.descriptionOfPlot = descriptionOfPlot;
    }

    public void setPlatformList(ArrayList<Platform> platformList) {
        this.platformList = platformList;
    }

    public void addPlatformToList(Platform platform) {
        platformList.add ( platform );
    }

    public ArrayList<Platform> getPlatformList() {
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

    public String getDescriptionOfPlot() {
        return descriptionOfPlot;
    }
}