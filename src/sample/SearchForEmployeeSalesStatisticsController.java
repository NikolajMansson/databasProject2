package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private TextArea view;
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

    TypeOfIntrestValue typeOfIntrestControll = TypeOfIntrestValue.SSN;
    SearchStatus searchStatusControll = SearchStatus.BOSS;
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

        DBConnection connection = new DBConnection ();
        ArrayList<Employee> searchEmployeeList = null;
        ArrayList<Boss> searchBossList = null;

        if (searchfield.getText ().equals ( "" )) {
            searchBossList = connection.getBossDefaultSalesSearch ( ascending );
            for (int i = 0; i < searchBossList.size (); i++) {
                view.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchBossList.get ( i ).getFirstName (), searchBossList.get ( i ).getSurName (), searchBossList.get ( i ).getEmploymentDate (), searchBossList.get ( i ).getGamesSold (), searchBossList.get ( i ).getIncome () ) );
            }

        } else if (typeOfIntrestControll == TypeOfIntrestValue.SSN) {
            searchEmployeeList = connection.getEmployeeSalesSSNSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchEmployeeList.size (); i++) {
                view.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurName (), searchEmployeeList.get ( i ).getEmploymentDate (), searchEmployeeList.get ( i ).getGamesSold (), searchEmployeeList.get ( i ).getIncome () ) );
            }

        } else if (typeOfIntrestControll == TypeOfIntrestValue.USERNAME) {
            searchEmployeeList = connection.getEmployeeSalesUserNameSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchEmployeeList.size (); i++) {
                view.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurName (), searchEmployeeList.get ( i ).getEmploymentDate (), searchEmployeeList.get ( i ).getGamesSold (), searchEmployeeList.get ( i ).getIncome () ) );
            }

        } else if (typeOfIntrestControll == TypeOfIntrestValue.SURNAME) {
            searchEmployeeList = connection.getEmployeeSalesSurnameSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchEmployeeList.size (); i++) {
                view.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchEmployeeList.get ( i ).getFirstName (), searchEmployeeList.get ( i ).getSurName (), searchEmployeeList.get ( i ).getEmploymentDate (), searchEmployeeList.get ( i ).getGamesSold (), searchEmployeeList.get ( i ).getIncome () ) );
            }

        } else if (typeOfIntrestControll == TypeOfIntrestValue.SSN) {
            searchBossList = connection.getBossSalesSSNSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchBossList.size (); i++) {
                view.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchBossList.get ( i ).getFirstName (), searchBossList.get ( i ).getSurName (), searchBossList.get ( i ).getEmploymentDate (), searchBossList.get ( i ).getGamesSold (), searchBossList.get ( i ).getIncome () ) );
            }

        } else if (typeOfIntrestControll == TypeOfIntrestValue.USERNAME) {
            searchBossList = connection.getBossSalesUserNameSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchBossList.size (); i++) {
                view.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchBossList.get ( i ).getFirstName (), searchBossList.get ( i ).getSurName (), searchBossList.get ( i ).getEmploymentDate (), searchBossList.get ( i ).getGamesSold (), searchBossList.get ( i ).getIncome () ) );
            }

        } else if (typeOfIntrestControll == TypeOfIntrestValue.SURNAME) {
            searchBossList = connection.getBossSalesSurnameSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchBossList.size (); i++) {
                view.appendText ( String.format ( "%-5s %-15s %d %d %.2f %n", searchBossList.get ( i ).getFirstName (), searchBossList.get ( i ).getSurName (), searchBossList.get ( i ).getEmploymentDate (), searchBossList.get ( i ).getGamesSold (), searchBossList.get ( i ).getIncome () ) );
            }
        }
    }
}
