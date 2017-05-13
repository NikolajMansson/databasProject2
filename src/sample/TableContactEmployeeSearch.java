package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Nikolaj on 2017-05-12.
 */
public class TableContactEmployeeSearch {
    private final SimpleStringProperty searchFirstName;
    private final SimpleStringProperty searchSurname;
    private final SimpleIntegerProperty searchSSN;
    private final SimpleStringProperty searchEmail;
    private final SimpleStringProperty searchUsername;
    private final SimpleStringProperty searchEmployed;
    private String employeestatus = null;

    public TableContactEmployeeSearch(String firstName, String surname, int SSN, String email, String username, int isEmployed){
        this.searchFirstName = new SimpleStringProperty ( firstName );
        this.searchSurname = new SimpleStringProperty ( surname );
        this.searchSSN = new SimpleIntegerProperty ( SSN );
        this.searchEmail = new SimpleStringProperty ( email );
        this.searchUsername = new SimpleStringProperty (username);
        if(isEmployed==0){
            this.employeestatus=String.format("No longer employed");
        }
        else{
            this.employeestatus=String.format("Employed");
        }
        this.searchEmployed = new SimpleStringProperty (employeestatus);

    }

    public SimpleStringProperty searchEmailProperty() {
        return searchEmail;
    }

    public String getSearchEmployed() {
        return searchEmployed.get ();
    }

    public SimpleStringProperty searchEmployedProperty() {
        return searchEmployed;
    }

    public void setSearchEmployed(int isEmployed) {
        if(isEmployed==0){
            this.employeestatus=String.format("No longer employed");
        }
        else{
            this.employeestatus=String.format("Employed");
        }
        this.searchEmployed.set ( employeestatus);
    }

    public String getEmployeestatus() {
        return employeestatus;
    }

    public void setEmployeestatus(int isEmployed) {
        if(isEmployed==0){
            this.employeestatus=String.format("No longer employed");
        }
        else{
            this.employeestatus=String.format("Employed");
        }
    }

    public String getSearchEmail() {
        return searchEmail.get ();
    }

    public SimpleStringProperty searchGamesSoldProperty() {
        return searchEmail;
    }

    public void setSearchEmail(String searchEmail) {
        this.searchEmail.set ( searchEmail );
    }

    public String getSearchUsername() {
        return searchUsername.get ();
    }

    public SimpleStringProperty searchUsernameProperty() {
        return searchUsername;
    }

    public void setSearchUsername(String searchUsername) {
        this.searchUsername.set ( searchUsername );
    }

    public int getSearchSSN() {
        return searchSSN.get ();
    }

    public SimpleIntegerProperty searchSSNProperty() {
        return searchSSN;
    }

    public void setSearchSSN(int searchDate) {
        this.searchSSN.set ( searchDate );
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



}
