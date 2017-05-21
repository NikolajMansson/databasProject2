package sample;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nikolaj on 2017-05-14.
 */
public class Log {
    private int logID = 0;
    private String responsibleBoss = null;
    private String newUserName;
    private String time = null;
    Date date = new Date ();

    Log(int logID, String event, String bossUserName, Timestamp timestamp) {
        setLogID ( logID );
        setResponsibleBoss ( event );
        setNewUserName ( bossUserName );
        date.setTime ( timestamp.getTime () );
        this.time = new SimpleDateFormat ( "yyyyMMdd" ).format ( date );
        setTime ( time );
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getResponsibleBoss() {
        return responsibleBoss;
    }

    public void setResponsibleBoss(String responsibleBoss) {
        this.responsibleBoss = responsibleBoss;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String employeeUserName) {
        this.newUserName = employeeUserName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
