package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Nikolaj on 2017-05-12.
 */
public class TableLogSearch {
    private int logID = 0;
    private String event = null;
    private String bossUserName;
    private String time = null;

    private final SimpleIntegerProperty searchLogID;
    private final SimpleStringProperty searchEvent;
    private final SimpleStringProperty searchBossUsername;
    private final SimpleStringProperty searchTime;

    public TableLogSearch(int logID, String event, String bossUsername, String time){
        this.searchLogID = new SimpleIntegerProperty ( logID );
        this.searchEvent = new SimpleStringProperty ( event );
        this.searchBossUsername = new SimpleStringProperty ( bossUsername );
        this.searchTime = new SimpleStringProperty (time);
    }

    public int getSearchLogID() {
        return searchLogID.get ();
    }

    public void setSearchLogID(int searchAID) {
        this.searchLogID.set ( searchAID );
    }

    public String getSearchEvent() {
        return searchEvent.get ();
    }

    public SimpleStringProperty searchEventProperty() {
        return searchEvent;
    }

    public void setSearchEvent(String searchEvent) {
        this.searchEvent.set ( searchEvent );
    }

    public String getSearchBossUsername() {
        return searchBossUsername.get ();
    }

    public SimpleStringProperty searchBossUsernameProperty() {
        return searchBossUsername;
    }

    public void setSearchBossUsername(String searchBossUsername) {
        this.searchBossUsername.set ( searchBossUsername);
    }

    public String getSearchTime() {
        return searchTime.get ();
    }

    public void setSearchTime(String searchTime) {
        this.searchTime.set ( searchTime );
    }

    public SimpleIntegerProperty searchLogIDProperty() {
        return searchLogID;
    }

    public SimpleStringProperty rEventProperty() {
        return searchEvent;
    }

    public SimpleStringProperty rBossUsernameProperty() {
        return searchBossUsername;
    }

    public SimpleStringProperty searchTimeProperty() {
        return searchTime;
    }

}
