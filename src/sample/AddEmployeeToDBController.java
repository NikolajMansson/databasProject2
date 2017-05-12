package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/18/2017.
 */
public class AddEmployeeToDBController implements Initializable {

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
    @FXML
    public RadioButton regularEmployeeRadioButton;
    @FXML
    public RadioButton bossRadioButton;


    private enum UserStatus {BOSS, REGULAREMPLOYEE}

    private UserStatus userControll = UserStatus.BOSS;

    @FXML
    public void setUserControllBoss(ActionEvent ae){
        this.userControll =  userControll.BOSS;

    }
    @FXML
    public void setUserControllRegularEmployee(ActionEvent ae){
        this.userControll =  userControll.REGULAREMPLOYEE;

    }

    @FXML
    public void add() {
        EmployeeSetAccountQueries connection = new EmployeeSetAccountQueries ();
        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        connection.setDBURL ( account.getUserName (), account.getPassword () );
        connection.addEmployeeToList ( ssn.getText (), firstName.getText (), surname.getText (), employementDate.getText (),
                email.getText (), username.getText (), password.getText () );
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
    private void help(){
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION, "");
        // Ställer in övre texten
        helpAlert.setTitle("Help Menu");
        // Ställer in bredden
        helpAlert.getDialogPane().setPrefWidth(400);
        // Ställer in mitten texten
        helpAlert.setHeaderText("This is the Employment Menu");
        // Ställer in brödtexten, system.getProperty("line.separator) är radbrytare"
        helpAlert.setContentText("Fill in all the fields with appropriate information." + System.getProperty("line.separator")
                + "Then press the 'add' button to add the new employee to the server." + System.getProperty("line.separator")
                + "Press the 'Cancel' button to go back." + System.getProperty("line.separator")
                + "Press OK to close this window.");
        helpAlert.showAndWait();
    }

}
