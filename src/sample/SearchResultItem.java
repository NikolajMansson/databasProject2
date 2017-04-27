package sample;

/**
 * Created by Nikolaj on 2017-04-27.
 */
public class SearchResultItem {
    int articleNo;
    String title;
    String abbreviation;
    String developer;
    String descriptionOfPlot;
    double price;

    public SearchResultItem(int articleNo, String title, String abbreviation, String developer, String descriptionOfPlot, double price) {
        setArticleNo ( articleNo );
        setTitle ( title );
        setAbbreviation ( abbreviation );
        setDeveloper ( developer );
        setDescriptionOfPlot ( descriptionOfPlot );
        setPrice ( price );
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
}
