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
        setGameTitle (gameTitle);
        setPlatform(platform);
        setPrice(price);
    }

    @Override
    public String getGameTitle() {
        return gameTitle;
    }

    @Override
    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
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