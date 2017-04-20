package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/18/2017.
 */
public class AddEmployeeToList implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField ssn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField employementDate;
    @FXML
    private PasswordField password;

    DBConnection dbc = new DBConnection ();

    @FXML
    public void add() {
        DBConnection connection = new DBConnection ();
        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        connection.setDBURL ( account.getUserName (), account.getPassword () );
        connection.addEmployeeToList ( ssn.getText (), firstName.getText (), surname.getText (), employementDate.getText (),
                email.getText (), username.getText (), password.getText () );
    }

    @FXML
    public void cancel() {

    }


}
