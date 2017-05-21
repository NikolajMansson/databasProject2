package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Nikolaj on 2017-05-12.
 */
public class TableGameSearch {
    private final SimpleIntegerProperty searchAID;
    private final SimpleStringProperty searchTitle;
    private final SimpleStringProperty searchAbbreviation;
    private final SimpleDoubleProperty searchPrice;
    private final SimpleIntegerProperty searchAmount;


    public TableGameSearch(int aID, String title, String abbreviation, double price, int searchAmount){
        this.searchAID = new SimpleIntegerProperty ( aID );
        this.searchTitle = new SimpleStringProperty ( title );
        this.searchAbbreviation = new SimpleStringProperty ( abbreviation );
        this.searchPrice = new SimpleDoubleProperty (price);
        this.searchAmount = new SimpleIntegerProperty (searchAmount);
    }

    public int getSearchAID() {
        return searchAID.get ();
    }

    public void setSearchAID(int searchAID) {
        this.searchAID.set ( searchAID );
    }

    public String getSearchTitle() {
        return searchTitle.get ();
    }

    public SimpleStringProperty searchTitleProperty() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle.set ( searchTitle );
    }

    public String getSearchAbbreviation() {
        return searchAbbreviation.get ();
    }

    public SimpleStringProperty searchAbbreviationProperty() {
        return searchAbbreviation;
    }

    public void setSearchAbbreviation(String searchAbbreviation) {
        this.searchAbbreviation.set ( searchAbbreviation );
    }

    public double getSearchPrice() {
        return searchPrice.get ();
    }

    public void setSearchPrice(double searchPrice) {
        this.searchPrice.set ( searchPrice );
    }

    public SimpleIntegerProperty searchAIDProperty() {
        return searchAID;
    }

    public SimpleStringProperty rNameProperty() {
        return searchTitle;
    }

    public SimpleStringProperty rDateProperty() {
        return searchAbbreviation;
    }

    public SimpleDoubleProperty searchPriceProperty() {
        return searchPrice;
    }

    public int getSearchAmount() {
        return searchAmount.get ();
    }

    public SimpleIntegerProperty searchAmountProperty() {
        return searchAmount;
    }

    public void setSearchAmount(int searchAmount) {
        this.searchAmount.set ( searchAmount );
    }
}
