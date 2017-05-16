package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button quitButton;

    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    CartFile cartFile = new CartFile ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cartFile.cleanArticleNo ();
        cartFile.cleanQuantity ();
    }

    @FXML
    private void loginAsEmployee(ActionEvent ae) {
        Main main = new Main ();

        EmployeeSetAccountQueries employeeSetAccountQueries = new EmployeeSetAccountQueries ();
        boolean testUserIdPassword = employeeSetAccountQueries.searchForPasswordEmployee ( loginUsername.getText (), loginPassword.getText () );
        if (testUserIdPassword == true) {

            EmployeeAccount account = new EmployeeAccount ( loginUsername.getText (), loginPassword.getText (), 1 );

            main.setMyUser ( account );

            Node node = (Node) ae.getSource ();
            Stage stage = (Stage) node.getScene ().getWindow ();

            FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneEmployeeWelcomeMenu.fxml" ) );
            Parent root = null;
            try {
                root = loader.load ();
            } catch (IOException e) {
                e.printStackTrace ();
            }

            Scene scene = new Scene ( root );
            stage.setScene ( scene );
        }
    }

    @FXML
    private void loginAsCustomer(ActionEvent ae) {
        Main main = new Main ();

        CustomerSetAccountQueries customerSetAccountQueries = new CustomerSetAccountQueries ();
        boolean testUserIdPassword = customerSetAccountQueries.searchForPasswordCustomer ( loginUsername.getText (), loginPassword.getText () );
        if (testUserIdPassword == true) {

            CustomerAccount account = new CustomerAccount ( loginUsername.getText (), loginPassword.getText () );

            main.setMyUser ( account );

            Node node = (Node) ae.getSource ();
            Stage stage = (Stage) node.getScene ().getWindow ();

            FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneCustomerWelcomeMenu.fxml" ) );
            Parent root = null;
            try {
                root = loader.load ();
            } catch (IOException e) {
                e.printStackTrace ();
            }

            Scene scene = new Scene ( root );
            stage.setScene ( scene );
        }

    }

    @FXML
    private void loginAsBoss(ActionEvent ae) {
        Main main = new Main ();

        EmployeeSetAccountQueries bossSetAccountQueries = new EmployeeSetAccountQueries ();
        boolean testUserIdPassword = bossSetAccountQueries.searchForPasswordBoss ( loginUsername.getText (), loginPassword.getText () );
        if (testUserIdPassword == true) {

            EmployeeAccount bossAccount = new EmployeeAccount ( loginUsername.getText (), loginPassword.getText (), 0 );

            main.setMyUser ( bossAccount );


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

    }

    @FXML
    private void guestSearch(ActionEvent ae) {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneSearchFromGameCustomer.fxml" ) );
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
    public void help() {
        Alert helpAlert = new Alert ( Alert.AlertType.INFORMATION, "" );
        helpAlert.setTitle ( "Help Menu" );
        helpAlert.getDialogPane ().setPrefWidth ( 600 );
        helpAlert.setHeaderText ( "This is the login screen" );
        helpAlert.setContentText ( "If you are a customer, use the guest button." + System.getProperty ( "line.separator" )
                + "If you have a membership, enter your username and password, then use the customer button." + System.getProperty ( "line.separator" )
                + "If you are an employee/boss, enter your username and password, then use the employee button." + System.getProperty ( "line.separator" )
                + "Press OK to close this window." );
        helpAlert.showAndWait ();
    }
    @FXML
    private void quit(){
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}
