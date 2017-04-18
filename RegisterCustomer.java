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
public class RegisterCustomer implements Initializable {

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
    private TextField registrationDate;

    @FXML
    private PasswordField password;

    @FXML public void add()
    {   DBConnection connection = new DBConnection ();
        connection.addCustomerToList(ssn.getText(), firstName.getText(), surname.getText(), registrationDate.getText(), email.getText(), username.getText(), password.getText());
    }

    @FXML public void cancel()
    {

    }


}
