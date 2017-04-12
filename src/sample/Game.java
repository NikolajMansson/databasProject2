package sample;

public class Game {

    private String gameTitle;

    private String genre;

    private String developer;

    private double price;

    private String descriptionOfPlot;


    public Game(String gameTitle, String genre, String developer, double price, String descriptionOfPlot) {
        this.gameTitle=gameTitle;
        this.genre=genre;
        this.developer=developer;
        this.price = price;
        this.descriptionOfPlot = descriptionOfPlot;
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