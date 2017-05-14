package sample;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nikolaj on 2017-05-14.
 */
public class Log {
    private int logID = 0;
    private String event = null;
    private String employeeUserName;
    private String time = null;
    Date date = new Date ();

    Log(int logID, String event, String employeeUserName, Timestamp timestamp) {
        setLogID ( logID );
        setEvent ( event );
        setEmployeeUserName ( employeeUserName );
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEmployeeUserName() {
        return employeeUserName;
    }

    public void setEmployeeUserName(String employeeUserName) {
        this.employeeUserName = employeeUserName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
