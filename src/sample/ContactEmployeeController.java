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

public class ContactEmployeeController implements Initializable {

    @FXML
    TableView<TableContactEmployeeSearch> tableID;

    @FXML
    TableColumn<TableGameSearch, String> iFirstName;
    @FXML
    TableColumn<TableGameSearch, String> iSurname;
    @FXML
    TableColumn<TableGameSearch, String> iEmail;
    @FXML
    TableColumn<TableGameSearch, Integer> iSSN;
    @FXML
    TableColumn<TableGameSearch, Double> iUsername;
    @FXML
    TableColumn<TableGameSearch, String> iIsEmployed;



    private ArrayList<TableContactEmployeeSearch> data = new ArrayList<> ();
    ArrayList<Employee> searchEmployeeList = null;
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
    RadioButton ascendingOrderRadioButton;
    @FXML
    RadioButton descendingOrderRadioButton;
    @FXML
    TextField removeEmployeeTextField;
    @FXML
    Button removeEmployeeButton;



    @FXML
    private TextField searchfield;



    public void setDescendingOrderRadioButton(ActionEvent actionEvent) {
        this.ascending = false;
    }

    private enum TypeOfIntrestValue {SSN, SURNAME, USERNAME}


    private TypeOfIntrestValue typeOfIntrestControll = TypeOfIntrestValue.SSN;

    private boolean ascending = true;

    @FXML
    public void setSSNRadioButton(ActionEvent ae) {
        this.typeOfIntrestControll = typeOfIntrestControll.SSN;
    }

    @FXML
    public void setSurnameRadioButton(ActionEvent ae) {
        this.typeOfIntrestControll = typeOfIntrestControll.SURNAME;
    }

    @FXML
    public void setUsernameRadioButton(ActionEvent ae) {
        this.typeOfIntrestControll = typeOfIntrestControll.USERNAME;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void setAscendingOrderRadioButton(ActionEvent ae) {
        this.ascending = true;
    }


    @FXML
    public void search(ActionEvent ae) {
        ContactSearchQueries connection = new ContactSearchQueries ();



        if (searchfield.getText ().equals ( "" )) {
            this.searchEmployeeList = connection.getEmployeeDefaultContactSearch ( ascending );


        } else if (typeOfIntrestControll == TypeOfIntrestValue.SSN) {
            this.searchEmployeeList = connection.getEmployeeContactSSNSearch ( searchfield.getText (), ascending );


        } else if (typeOfIntrestControll == TypeOfIntrestValue.USERNAME) {
            this.searchEmployeeList = connection.getEmployeeContactUserNameSearch ( searchfield.getText (), ascending );


        } else if (typeOfIntrestControll == TypeOfIntrestValue.SURNAME) {
            this.searchEmployeeList = connection.getEmployeeContactSurnameSearch ( searchfield.getText (), ascending );

        }
        else{
            return;
        }
        for (int i = 0; i < searchEmployeeList.size (); i++) {
            TableContactEmployeeSearch table = new TableContactEmployeeSearch ( searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurname (), searchEmployeeList.get ( i ).getSSN (), searchEmployeeList.get ( i ).getEmail (), searchEmployeeList.get(i).getEmployeeAccount ().getUserName (), searchEmployeeList.get(i).getIsEmployed () );
            data.add ( table );
        }

        ObservableList<TableContactEmployeeSearch> data2 = FXCollections.observableArrayList ( data );
        tableID.setItems ( data2 );

        iSSN.setCellValueFactory ( new PropertyValueFactory<> ( "searchSSN" ) );
        iFirstName.setCellValueFactory ( new PropertyValueFactory<> ( "searchFirstName" ) );
        iSurname.setCellValueFactory ( new PropertyValueFactory<> ( "searchSurname" ) );
        iEmail.setCellValueFactory ( new PropertyValueFactory<> ( "searchEmail" ) );
        iUsername.setCellValueFactory ( new PropertyValueFactory<> ( "searchUsername" ) );
        iIsEmployed.setCellValueFactory ( new PropertyValueFactory<> ( "searchEmployed" ) );




    }

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
    public void removeEmployee(ActionEvent ae) {
        EmployeeSetAccountQueries connection = new EmployeeSetAccountQueries ();
        String userName = removeEmployeeTextField.getText ();
        connection.removeEmployee ( userName );
    }
    @FXML
    private void help(){
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION, "");
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