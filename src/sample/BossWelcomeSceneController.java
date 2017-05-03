package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BossWelcomeSceneController implements Initializable {
    @FXML
    private Label welcomeLabel;
    ReadActiveUserFile readUser = new ReadActiveUserFile ();
    BossAccount account = new BossAccount ( "", "" );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readUser.openFile ();
        this.account = (BossAccount) readUser.readRecords ();
        readUser.closeFile ();
        DBConnection connection = new DBConnection ();
       // welcomeLabel.setText ( "Welcome " + connection.searchForName ( account.getUserName () ) + "!" );
    }

    @FXML
    private void returnToLogin(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneLogin.fxml" ) );
        Parent root = loader.load ();

        Scene scene = new Scene ( root, 500, 300 );
        stage.setScene ( scene );

    }

    @FXML
    private void addEmployee(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneAddEmployee.fxml" ) );
        Parent root = loader.load ();

        Scene scene = new Scene ( root, 500, 300 );
        stage.setScene ( scene );

    }

    @FXML
    private void addCustomer(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneAddCustomer.fxml" ) );
        Parent root = loader.load ();

        Scene scene = new Scene ( root, 500, 300 );
        stage.setScene ( scene );

    }

    @FXML
    private void addGameToDB(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneAddGameToDB.fxml" ) );
        Parent root = loader.load ();

        Scene scene = new Scene ( root, 500, 300 );
        stage.setScene ( scene );

    }

    @FXML
    private void addGameToStock(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneAddGameToStock.fxml" ) );
        Parent root = loader.load ();

        Scene scene = new Scene ( root, 500, 300 );
        stage.setScene ( scene );

    }

    @FXML
    private void openCart(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneCart.fxml" ) );
        Parent root = loader.load ();

        Scene scene = new Scene ( root, 500, 300 );
        stage.setScene ( scene );

    }
}
