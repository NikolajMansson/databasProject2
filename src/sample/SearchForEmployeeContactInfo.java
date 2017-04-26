package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Nikolaj on 2017-04-24.
 */
public class SearchForEmployeeContactInfo implements Initializable{
    @FXML
    Button search;
    @FXML
    Button cancelButton;
    @FXML
    Button logOutButton;
    @FXML
    TextField searchfield;
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



}
