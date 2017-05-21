package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Nikolaj on 2017-05-12.
 */
public class TableLogSearch {
    private final SimpleIntegerProperty searchLogID;
    private final SimpleStringProperty searchResponisbleBoss;
    private final SimpleStringProperty searchNewUsername;
    private final SimpleStringProperty searchTime;

    public TableLogSearch(int logID, String responisibleBoss, String newUsername, String time){
        this.searchLogID = new SimpleIntegerProperty ( logID );
        this.searchResponisbleBoss = new SimpleStringProperty ( responisibleBoss );
        this.searchNewUsername = new SimpleStringProperty ( newUsername );
        this.searchTime = new SimpleStringProperty (time);
    }

    public int getSearchLogID() {
        return searchLogID.get ();
    }

    public SimpleIntegerProperty searchLogIDProperty() {
        return searchLogID;
    }

    public void setSearchLogID(int searchLogID) {
        this.searchLogID.set ( searchLogID );
    }

    public String getSearchTime() {
        return searchTime.get ();
    }

    public SimpleStringProperty searchTimeProperty() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime.set ( searchTime );
    }

    public String getSearchResponisbleBoss() {
        return searchResponisbleBoss.get ();
    }

    public SimpleStringProperty searchResponisbleBossProperty() {
        return searchResponisbleBoss;
    }

    public void setSearchResponisbleBoss(String searchResponisbleBoss) {
        this.searchResponisbleBoss.set ( searchResponisbleBoss );
    }

    public String getSearchNewUsername() {
        return searchNewUsername.get ();
    }

    public SimpleStringProperty searchNewUsernameProperty() {
        return searchNewUsername;
    }

    public void setSearchNewUsername(String searchNewUsername) {
        this.searchNewUsername.set ( searchNewUsername );
    }


}
