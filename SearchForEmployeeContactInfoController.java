package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Nikolaj on 2017-04-24.
 */
public class SearchForEmployeeContactInfoController implements Initializable {
    @FXML
    public Button search;
    @FXML
    public Button cancelButton;
    @FXML
    public RadioButton ssnRadioButton;
    @FXML
    public RadioButton surnameRadioButton;
    @FXML
    public RadioButton usernameRadioButton;
    @FXML
    public RadioButton ascendingOrderRadioButton;
    @FXML
    public RadioButton descendingOrderRadioButton;
    @FXML
    public TextArea viewTextArea;
    @FXML
    public Button removeEmployeeButton;
    @FXML
    private TextField searchfield;
    @FXML
    public TextField removeEmployeeTextField;
    @FXML
    public RadioButton bossRadioButton;
    @FXML
    public RadioButton regularEmployeeRadioButton;

    private enum TypeOfIntrestValue {SSN, SURNAME, USERNAME}
    private enum SearchStatus {BOSS, EMPLOYEE}
    private TypeOfIntrestValue typeOfIntrestControll = TypeOfIntrestValue.SSN;
    private SearchStatus searchStatusControll = SearchStatus.BOSS;
    private boolean ascending = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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

    @FXML
    public void setBossRadioButton(ActionEvent ae) {
        this.searchStatusControll = searchStatusControll.BOSS;
    }

    @FXML
    public void setEmployeeRadioButton(ActionEvent ae) {
        this.searchStatusControll = searchStatusControll.EMPLOYEE;
    }

    @FXML
    public void setAscendingOrderRadioButton(ActionEvent ae) {
        this.ascending = true;
    }

    @FXML
    public void setDescendingOrderRadioButton(ActionEvent ae) {
        this.ascending = false;
    }

    @FXML
    public void removeEmployee(ActionEvent ae) {
        EmployeeSetAccountQueries connection = new EmployeeSetAccountQueries ();
        String userName = removeEmployeeTextField.getText ();
        connection.removeEmployee ( userName );
    }

    @FXML
    public void search() {
        ContactSearchQueries connection = new ContactSearchQueries ();
        ArrayList<Employee> searchEmployeeList = null;


        if (searchfield.getText ().equals ( "" )) {
            searchEmployeeList = connection.getBossDefaultContactSearch ( ascending );
            for (int i = 0; i < searchEmployeeList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%d %-5s %-15s %-15s %s%n", searchEmployeeList.get ( i ).getSSN (), searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurname (), searchEmployeeList.get ( i ).getEmail (), searchEmployeeList.get(i).getEmployeeAccount ().getUserName () ) );
            }

        } else if (typeOfIntrestControll == TypeOfIntrestValue.SSN  && searchStatusControll == SearchStatus.EMPLOYEE) {
            searchEmployeeList = connection.getEmployeeContactSSNSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchEmployeeList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%d %-5s %-15s %-15s %s%n", searchEmployeeList.get ( i ).getSSN (), searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurname (), searchEmployeeList.get ( i ).getEmail (), searchEmployeeList.get(i).getEmployeeAccount ().getUserName () ) );
            }

        } else if (typeOfIntrestControll == TypeOfIntrestValue.USERNAME && searchStatusControll == SearchStatus.EMPLOYEE) {
            searchEmployeeList = connection.getEmployeeContactUserNameSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchEmployeeList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%d %-5s %-15s %-15s %s%n", searchEmployeeList.get ( i ).getSSN (), searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurname (), searchEmployeeList.get ( i ).getEmail (), searchEmployeeList.get(i).getEmployeeAccount ().getUserName () ) );
            }

        } else if (typeOfIntrestControll == TypeOfIntrestValue.SURNAME && searchStatusControll == SearchStatus.EMPLOYEE) {
            searchEmployeeList = connection.getEmployeeContactSurnameSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchEmployeeList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%d %-5s %-15s %-15s %s%n", searchEmployeeList.get ( i ).getSSN (), searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurname (), searchEmployeeList.get ( i ).getEmail (), searchEmployeeList.get ( i ).getEmployeeAccount ().getUserName () ) );
            }
        }
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
    private void help(){
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION, "");
        // Ställer in övre texten
        helpAlert.setTitle("Help Menu");
        // Ställer in bredden
        helpAlert.getDialogPane().setPrefWidth(450);
        // Ställer in mitten texten
        helpAlert.setHeaderText("This is the Employee Search Engine");
        // Ställer in brödtexten, system.getProperty("line.separator) är radbrytare"
        helpAlert.setContentText("Select which category you want to use (SSN, Username or Surname)." + System.getProperty("line.separator")
                + "Select which order you want (Ascending/Descending)." + System.getProperty("line.separator")
                + "Select which type of employee you are looking for (Boss or Regular Employee)" + System.getProperty("line.separator")
                + "Type your keywords in the field, then press the Search button." + System.getProperty("line.separator")
                + "Press OK to close this window.");
        helpAlert.showAndWait();
    }
}
