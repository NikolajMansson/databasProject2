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
public class SearchForEmployeeSalesStatisticsController implements Initializable {
    @FXML
    Button search;
    @FXML
    Button cancelButton;
    @FXML
    Button logOutButton;
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
    TextArea viewTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField searchfield;
    @FXML
    private TextField indexTextField;
    @FXML
    private RadioButton bossRadioButton;
    @FXML
    private RadioButton regularEmployeeRadioButton;

    private enum TypeOfIntrestValue {SSN, SURNAME, USERNAME}

    private enum SearchStatus {BOSS, EMPLOYEE}

    private TypeOfIntrestValue typeOfIntrestControll = TypeOfIntrestValue.SSN;
    private SearchStatus searchStatusControll = SearchStatus.BOSS;
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
    public void search(ActionEvent ae) {
        SalesSearchQueries connection = new SalesSearchQueries ();
        ArrayList<Employee> searchEmployeeList = null;
        ArrayList<Boss> searchBossList = null;

        if (searchfield.getText ().equals ( "" )) {
            searchBossList = connection.getBossDefaultSalesSearch ( ascending );
            for (int i = 0; i < searchBossList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchBossList.get ( i ).getFirstName (), searchBossList.get ( i ).getSurName (), searchBossList.get ( i ).getEmploymentDate (), searchBossList.get ( i ).getGamesSold (), searchBossList.get ( i ).getIncome () ) );
            }

        } else if ((typeOfIntrestControll == TypeOfIntrestValue.SSN)&&(searchStatusControll == SearchStatus.EMPLOYEE)) {
            searchEmployeeList = connection.getEmployeeSalesSSNSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchEmployeeList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurname (), searchEmployeeList.get ( i ).getEmploymentDate (), searchEmployeeList.get ( i ).getGamesSold (), searchEmployeeList.get ( i ).getIncome () ) );
            }

        } else if ((typeOfIntrestControll == TypeOfIntrestValue.USERNAME)&&(searchStatusControll == SearchStatus.EMPLOYEE)) {
            searchEmployeeList = connection.getEmployeeSalesUserNameSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchEmployeeList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurname (), searchEmployeeList.get ( i ).getEmploymentDate (), searchEmployeeList.get ( i ).getGamesSold (), searchEmployeeList.get ( i ).getIncome () ) );
            }

        } else if ((typeOfIntrestControll == TypeOfIntrestValue.SURNAME)&&(searchStatusControll == SearchStatus.EMPLOYEE)) {
            searchEmployeeList = connection.getEmployeeSalesSurnameSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchEmployeeList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurname (), searchEmployeeList.get ( i ).getEmploymentDate (), searchEmployeeList.get ( i ).getGamesSold (), searchEmployeeList.get ( i ).getIncome () ) );
            }

        } else if ((typeOfIntrestControll == TypeOfIntrestValue.SSN) && (searchStatusControll == SearchStatus.BOSS)) {
            searchBossList = connection.getBossSalesSSNSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchBossList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchBossList.get ( i ).getFirstName (), searchBossList.get ( i ).getSurName (), searchBossList.get ( i ).getEmploymentDate (), searchBossList.get ( i ).getGamesSold (), searchBossList.get ( i ).getIncome () ) );
            }

        } else if ((typeOfIntrestControll == TypeOfIntrestValue.USERNAME) &&(searchStatusControll == SearchStatus.BOSS)) {
            searchBossList = connection.getBossSalesUserNameSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchBossList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchBossList.get ( i ).getFirstName (), searchBossList.get ( i ).getSurName (), searchBossList.get ( i ).getEmploymentDate (), searchBossList.get ( i ).getGamesSold (), searchBossList.get ( i ).getIncome () ) );
            }

        } else if ((typeOfIntrestControll == TypeOfIntrestValue.SURNAME)&&(searchStatusControll == SearchStatus.BOSS)) {
            searchBossList = connection.getBossSalesSurnameSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchBossList.size (); i++) {
                viewTextArea.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchBossList.get ( i ).getFirstName (), searchBossList.get ( i ).getSurName (), searchBossList.get ( i ).getEmploymentDate (), searchBossList.get ( i ).getGamesSold (), searchBossList.get ( i ).getIncome () ) );
            }
        }
    }

    @FXML
    public void cancel(ActionEvent ae){
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
