package sample;



/**
 *
 */
public class Item extends Game {

    private String gameTitle;

    private double price;

    private int articleNumber = 0;



    Platform platform;

    public Item(String gameTitle, double price, Platform platform) {
        super ( gameTitle);

        this.articleNumber++;
        this.gameTitle = gameTitle;
        this.platform = platform;
        this.price = price;


    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public double getPrice() {
        return price;
    }

    public Platform getPlatform(){
        return platform;
    }


}