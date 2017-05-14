package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/18/2017.
 */
public class RegisterCustomerController implements Initializable {

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

    @FXML
    private void add(ActionEvent ae) {
        CustomerSetAccountQueries connection = new CustomerSetAccountQueries ();
        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        connection.setDBURL ( account.getUserName (), account.getPassword () );
        connection.addCustomerToList ( ssn.getText (), firstName.getText (), surname.getText (), registrationDate.getText (), email.getText (), username.getText (), password.getText () );
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

        Scene scene = new Scene ( root);
        stage.setScene ( scene );
    }

    @FXML
    public void help(){
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION, "");
        // Ställer in övre texten
        helpAlert.setTitle("Help Menu");
        // Ställer in bredden
        helpAlert.getDialogPane().setPrefWidth(400);
        // Ställer in mitten texten
        helpAlert.setHeaderText("This is the New Membership Menu");
        // Ställer in brödtexten, system.getProperty("line.separator) är radbrytare"
        helpAlert.setContentText("Fill in all the fields with appropriate information." + System.getProperty("line.separator")
                + "Then press the 'add' button to add the new membership to the server." + System.getProperty("line.separator")
                + "Press the 'Cancel' button to go back." + System.getProperty("line.separator")
                + "Press OK to close this window.");
        helpAlert.showAndWait();
    }
}
