package sample;

/**
 * Created by Nikolaj on 2017-04-27.
 */
public class SearchResultItem {
    private int articleNo = 0;
    private String title = null;
    private String abbreviation = null;
    private String developer = null;
    private String descriptionOfPlot = null;
    private double price = 0;
    private int amount = 0;
    private int releaseDate = 0;

    public SearchResultItem(int articleNo, String title, String abbreviation, String developer, String descriptionOfPlot, double price, int amount, int releaseDate) {
        setArticleNo ( articleNo );
        setTitle ( title );
        setAbbreviation ( abbreviation );
        setDeveloper ( developer );
        setDescriptionOfPlot ( descriptionOfPlot );
        setPrice ( price );
        setAmount(amount);
        setReleaseDate ( releaseDate );
    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDescriptionOfPlot() {
        return descriptionOfPlot;
    }

    public void setDescriptionOfPlot(String descriptionOfPlot) {
        this.descriptionOfPlot = descriptionOfPlot;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
}
