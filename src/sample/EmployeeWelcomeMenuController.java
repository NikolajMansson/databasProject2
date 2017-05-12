package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeWelcomeMenuController implements Initializable {
    @FXML
    private Label welcomeLabel;
    private ReadActiveUserFile readUser = new ReadActiveUserFile ();
    private EmployeeAccount account = new EmployeeAccount ( "", "" );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readUser.openFile ();
        this.account = (EmployeeAccount) readUser.readRecords ();
        readUser.closeFile ();
        welcomeLabel.setText ( "Welcome " + account.getUserName ()  + "!" );
    }

    @FXML
    private void returnToLogin(ActionEvent ae){
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneLogin.fxml" ) );
        Parent root = null;
        try {
            root = loader.load ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        Scene scene = new Scene ( root, 500, 300 );
        stage.setScene ( scene );
    }

    @FXML
    private void addGameToDB(ActionEvent ae) {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneAddGameToDB.fxml" ) );
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
    private void addGameToStock(ActionEvent ae) {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneAddItemToStock.fxml" ) );
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
    private void searchGames(ActionEvent ae) {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneSearchFromGameLibraryEmployee.fxml" ) );
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
        helpAlert.setHeaderText("This is the Employee Welcome Menu");
        // Ställer in brödtexten, system.getProperty("line.separator) är radbrytare"
        helpAlert.setContentText("Use the 'Register game to Catalog' button to add a new game." + System.getProperty("line.separator")
        + "Use the 'Register game to Stock' to add a new copy to the store." + System.getProperty("line.separator")
        + "Use the 'Game Search' to search for games." + System.getProperty("line.separator")
        + "Use the 'Customer Search' to search for members." + System.getProperty("line.separator")
        + "Press OK to close this window.");
        helpAlert.showAndWait();
    }
}
