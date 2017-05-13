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
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BossWelcomeMenuController implements Initializable {
    @FXML
    private Label welcomeLabel;
    @FXML
    public Button employeeSearchButton;

    private ReadActiveUserFile readUser = new ReadActiveUserFile ();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readUser.openFile ();
        Account account = (EmployeeAccount) readUser.readRecords ();
        readUser.closeFile ();
        welcomeLabel.setText ( "Welcome " +  account.getUserName ()  + "!" );
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

        Scene scene = new Scene ( root );
        stage.setScene ( scene );
    }

    @FXML
    private void addEmployee(ActionEvent ae) {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneAddEmployee.fxml" ) );
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
    private void addCustomer(ActionEvent ae) {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneRegisterCustomer.fxml" ) );
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
    private void addGameToDB(ActionEvent ae){
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
    private void addGameToStock(ActionEvent ae){
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
    private void searchEmployeeContactInfo(ActionEvent ae){
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneSearchForEmployeeContactInfo.fxml" ) );
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
    private void openCart(ActionEvent ae){
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneCart.fxml" ) );
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
    private void viewSalesStatistics(ActionEvent ae){
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneSaleStats.fxml" ) );
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
    private void searchGames(ActionEvent ae){
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ("sceneSearchFromGameLibraryEmployee.fxml") );
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
        helpAlert.getDialogPane().setPrefWidth(420);
        // Ställer in mitten texten
        helpAlert.setHeaderText("This is the Boss Welcome Menu");
        // Ställer in brödtexten, system.getProperty("line.separator) är radbrytare"
        helpAlert.setContentText("Use the 'Hire Employee' button to add a new employee." + System.getProperty("line.separator")
                + "Use the 'Register Customer' button to add a new membership." + System.getProperty("line.separator")
                + "Use the 'Register game to Catalog' button to add a new game." + System.getProperty("line.separator")
                + "Use the 'Register game to Stock' to add a new copy to the store." + System.getProperty("line.separator")
                + "Use the 'Customer Search' to search for members." + System.getProperty("line.separator")
                + "Use the 'Game Search' to search for games." + System.getProperty("line.separator")
                + "Use the 'Search for Employee Contact Info' to look up employees." + System.getProperty("line.separator")
                + "Use the 'Search for Employee Sales Statictics' to see how sales are going." + System.getProperty("line.separator")
                + "Press OK to close this window.");
        helpAlert.showAndWait();
    }
}
