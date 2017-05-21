package sample;


/**
 *
 */
public class Item extends Game {

    private String gameTitle = null;

    private double price = 0;

    private int articleNumber = 0;

    private int amountOfItems = 0;

    private int releaseDate = 0;

    private String platformAbbreviation = null;

    public Item(int articleNumber, String platformAbbreviation, double price, String title, int releaseDate) {
        super ( title );
        setArticleNumber ( articleNumber );
        setPlatformAbbreviation ( platformAbbreviation );
        setPrice ( price );
        setAmountOfItems ( amountOfItems );
        setGameTitle ( title );
        setRealeaseDate ( releaseDate );

    }

    @Override
    public String getGameTitle() {
        return gameTitle;
    }

    @Override
    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public void setAmountOfItems(int amountOfItems) {
        this.amountOfItems = amountOfItems;
    }

    public int getAmountOfItems() {
        return amountOfItems;
    }

    public void setRealeaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setPlatformAbbreviation(String platformAbbreviation) {
        this.platformAbbreviation = platformAbbreviation;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public double getPrice() {
        return price;
    }

    public String getPlatformAbbreviation() {
        return platformAbbreviation;
    }
}