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

    private int articleNumber=0;

    public Item(String gameTitle, String genre, String developer, Platform platform, double price, String descriptionOfPlot) {
        super (gameTitle, genre, developer, price, descriptionOfPlot, platform);

        this.articleNumber++;

    }

    public int getArticleNumber(){
        return articleNumber;
    }

    public double getPrice(){
        //Hämta och returnera från databas
        return price;
    }


}