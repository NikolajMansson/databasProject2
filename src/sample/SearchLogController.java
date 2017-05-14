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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchLogController implements Initializable {

    @FXML
    TableView<TableLogSearch> tableID;
    @FXML
    TableColumn<TableGameSearch, Integer> iLogID;
    @FXML
    TableColumn<TableGameSearch, String> iEvent;
    @FXML
    TableColumn<TableGameSearch, String> iBoss;
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
       // connection.setDBURL ( account.getUserName (), account.getPassword () );
        this.searchLogList = connection.getLog();

        for (int i = 0; i < searchLogList.size (); i++) {
            TableLogSearch table = new TableLogSearch ( searchLogList.get ( i ).getLogID (), searchLogList.get ( i ).getEvent (), searchLogList.get ( i ).getBossUserName (), searchLogList.get ( i ).getTime ());
            data.add ( table );
        }

        ObservableList<TableLogSearch> data2 = FXCollections.observableArrayList ( data );
        tableID.setItems ( data2 );

        iLogID.setCellValueFactory ( new PropertyValueFactory<> ( "searchLogID" ) );
        iEvent.setCellValueFactory ( new PropertyValueFactory<> ( "searchEvent" ) );
        iBoss.setCellValueFactory ( new PropertyValueFactory<> ( "searchBossUserName" ) );
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

    @FXML
    public void help(){
        Alert helpAlert = new Alert (Alert.AlertType.INFORMATION, "");
        helpAlert.setTitle("Help Menu");
        helpAlert.getDialogPane().setPrefWidth(450);
        helpAlert.setHeaderText("This is the Employee Search Engine");
        helpAlert.setContentText("Select which category you want to use (SSN, Username or Surname)." + System.getProperty("line.separator")
                + "Select which order you want (Ascending/Descending)." + System.getProperty("line.separator")
                + "Select which type of employee you are looking for (Boss or Regular Employee)" + System.getProperty("line.separator")
                + "Type your keywords in the field, then press the Search button." + System.getProperty("line.separator")
                + "Press OK to close this window.");
        helpAlert.showAndWait();
    }
}