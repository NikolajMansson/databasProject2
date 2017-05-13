package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Nikolaj on 2017-05-12.
 */
public class TableEmployeeStatsSearch {
    private final SimpleStringProperty searchFirstName;
    private final SimpleStringProperty searchSurname;
    private final SimpleIntegerProperty searchDate;
    private final SimpleIntegerProperty searchGamesSold;
    private final SimpleDoubleProperty searchIncome;

    public TableEmployeeStatsSearch(String firstName, String surname, int date, int gamesSold, double income){
        this.searchFirstName = new SimpleStringProperty ( firstName );
        this.searchSurname = new SimpleStringProperty ( surname );
        this.searchDate = new SimpleIntegerProperty ( date );
        this.searchGamesSold = new SimpleIntegerProperty ( gamesSold );
        this.searchIncome = new SimpleDoubleProperty (income);
    }

    public int getSearchGamesSold() {
        return searchGamesSold.get ();
    }

    public SimpleIntegerProperty searchGamesSoldProperty() {
        return searchGamesSold;
    }

    public void setSearchGamesSold(int searchGamesSold) {
        this.searchGamesSold.set ( searchGamesSold );
    }

    public double getSearchIncome() {
        return searchIncome.get ();
    }

    public SimpleDoubleProperty searchIncomeProperty() {
        return searchIncome;
    }

    public void setSearchIncome(double searchIncome) {
        this.searchIncome.set ( searchIncome );
    }

    public int getSearchDate() {
        return searchDate.get ();
    }

    public SimpleIntegerProperty searchDateProperty() {
        return searchDate;
    }

    public void setSearchDate(int searchDate) {
        this.searchDate.set ( searchDate );
    }



    public String getSearchFirstName() {
        return searchFirstName.get ();
    }

    public SimpleStringProperty searchFirstNameProperty() {
        return searchFirstName;
    }

    public void setSearchFirstName(String searchFirstName) {
        this.searchFirstName.set ( searchFirstName );
    }

    public String getSearchSurname() {
        return searchSurname.get ();
    }

    public SimpleStringProperty searchSurnameProperty() {
        return searchSurname;
    }

    public void setSearchSurname(String searchSurname) {
        this.searchSurname.set ( searchSurname );
    }





    public double getSearchPrice() {
        return searchIncome.get ();
    }

    public void setSearchPrice(double searchPrice) {
        this.searchIncome.set ( searchPrice );
    }




    public SimpleIntegerProperty rDateProperty() {
        return searchDate;
    }

    public SimpleDoubleProperty searchPriceProperty() {
        return searchIncome;
    }

}
