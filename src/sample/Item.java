package sample;

/**
 * 
 */
public class Item extends Game {

    private String gameTitle;

    private String genre;

    private String developer;

    private double price;

    private String descriptionOfPlot;

    public Item(String gameTitle, String genre, String developer, double price, String descriptionOfPlot, Game game) {
        super (gameTitle, genre, developer, price, descriptionOfPlot);

    }

    private Integer itemId;

    public void removeItem(Integer itemId) {
        // TODO implement here
    }

}