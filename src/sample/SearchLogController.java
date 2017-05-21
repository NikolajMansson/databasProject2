package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class SearchLogController implements Initializable {

    @FXML
    TableView<TableLogSearch> tableID;
    @FXML
    TableColumn<TableGameSearch, Integer> iLogID;
    @FXML
    TableColumn<TableGameSearch, String> iResponsibleBoss;
    @FXML
    TableColumn<TableGameSearch, String> iNewUserName;
    @FXML
    TableColumn<TableGameSearch, Integer> iTime;

    @FXML
    Button search;
    @FXML
    Button cancelButton;


    private ArrayList<TableLogSearch> data = new ArrayList<> ();
    ArrayList<Log> searchLogList = null;

    ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        EmployeeSetAccountQueries connection = new EmployeeSetAccountQueries ();
        connection.setDBURL ( account.getUserName (), account.getPassword () );
        this.searchLogList = connection.getLog();

        int year = Calendar.getInstance().get( Calendar.YEAR);
        int month = Calendar.getInstance().get( Calendar.MONTH);
        int day = Calendar.getInstance().get( Calendar.DAY_OF_WEEK_IN_MONTH);

        String yearLine = String.format("%d%d%d", year, month, day);
        int yearCompare = Integer.parseInt ( yearLine );

        for (int i = 0; i < searchLogList.size (); i++) {
            TableLogSearch table = new TableLogSearch ( searchLogList.get ( i ).getLogID (), searchLogList.get ( i ).getResponsibleBoss (), searchLogList.get ( i ).getNewUserName (), searchLogList.get ( i ).getTime ());
            int time = Integer.parseInt ( searchLogList.get(i).getTime () );
            //Om ett år har gått så förstörs logId
            if((time+10000)<yearCompare){
                connection.removeLogID ( searchLogList.get(i).getLogID () );
            }
            data.add ( table );
        }

        ObservableList<TableLogSearch> data2 = FXCollections.observableArrayList ( data );
        tableID.setItems ( data2 );

        iLogID.setCellValueFactory ( new PropertyValueFactory<> ( "searchLogID" ) );
        iResponsibleBoss.setCellValueFactory ( new PropertyValueFactory<> ( "searchResponisbleBoss" ) );
        iNewUserName.setCellValueFactory ( new PropertyValueFactory<> ( "searchNewUsername" ) );
        iTime.setCellValueFactory ( new PropertyValueFactory<> ( "searchTime" ) );

    }


    @FXML
    public void cancel(ActionEvent ae) {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneBossWelcomeMenu.fxml" ) );
        Parent root = null;
        try {
            root = loader.load ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        Scene scene = new Scene ( root );
        stage.setScene ( scene );
    }


}