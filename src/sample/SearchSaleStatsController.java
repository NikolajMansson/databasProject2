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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchSaleStatsController implements Initializable {

    @FXML
    TableView<TableEmployeeStatsSearch> tableID;
    @FXML
    TableColumn<TableGameSearch, String> iFirstName;
    @FXML
    TableColumn<TableGameSearch, String> iSurname;
    @FXML
    TableColumn<TableGameSearch, Integer> iDate;
    @FXML
    TableColumn<TableGameSearch, Integer> iGamesSold;
    @FXML
    TableColumn<TableGameSearch, Double> iIncome;


    @FXML
    Button search;
    @FXML
    Button cancelButton;
    @FXML
    RadioButton ssnRadioButton;
    @FXML
    RadioButton surnameRadioButton;
    @FXML
    RadioButton usernameRadioButton;

    @FXML
    private TextField searchfield;

    private ArrayList<TableEmployeeStatsSearch> data = new ArrayList<> ();
    ArrayList<Employee> searchEmployeeList = null;

    private enum TypeOfIntrestValue {SSN, SURNAME, USERNAME}
    private TypeOfIntrestValue typeOfIntrestControll = TypeOfIntrestValue.SSN;
    ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();

    @FXML
    private void setSSNRadioButton(ActionEvent ae) {
        this.typeOfIntrestControll = typeOfIntrestControll.SSN;
    }

    @FXML
    private void setSurnameRadioButton(ActionEvent ae) {
        this.typeOfIntrestControll = typeOfIntrestControll.SURNAME;
    }

    @FXML
    private void setUsernameRadioButton(ActionEvent ae) {
        this.typeOfIntrestControll = typeOfIntrestControll.USERNAME;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private void search(ActionEvent ae) {
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        SalesSearchQueries connection = new SalesSearchQueries ();
        connection.setDBURL ( account.getUserName (), account.getPassword () );



        if (searchfield.getText ().equals ( "" )) {
            this.searchEmployeeList = connection.getEmployeeDefaultSalesSearch ();


        } else if ((typeOfIntrestControll == TypeOfIntrestValue.SSN)) {
            this.searchEmployeeList = connection.getEmployeeSalesSSNSearch ( searchfield.getText ());


        } else if ((typeOfIntrestControll == TypeOfIntrestValue.USERNAME)) {
            this.searchEmployeeList = connection.getEmployeeSalesUserNameSearch ( searchfield.getText ());


        } else if ((typeOfIntrestControll == TypeOfIntrestValue.SURNAME)) {
            this.searchEmployeeList = connection.getEmployeeSalesSurnameSearch ( searchfield.getText ());


        }

        else{
            return;
        }
        for (int i = 0; i < searchEmployeeList.size (); i++) {
            TableEmployeeStatsSearch table = new TableEmployeeStatsSearch ( searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurname (), searchEmployeeList.get ( i ).getEmploymentDate (), searchEmployeeList.get ( i ).getGamesSold (), searchEmployeeList.get(i).getIncome () );
            data.add ( table );
        }

        ObservableList<TableEmployeeStatsSearch> data2 = FXCollections.observableArrayList ( data );
        tableID.setItems ( data2 );


        iFirstName.setCellValueFactory ( new PropertyValueFactory<> ( "searchFirstName" ) );
        iSurname.setCellValueFactory ( new PropertyValueFactory<> ( "searchSurname" ) );
        iDate.setCellValueFactory ( new PropertyValueFactory<> ( "searchDate" ) );
        iGamesSold.setCellValueFactory ( new PropertyValueFactory<> ( "searchGamesSold" ) );
        iIncome.setCellValueFactory ( new PropertyValueFactory<> ( "searchIncome" ) );
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