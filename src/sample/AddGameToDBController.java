package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/18/2017.
 */
public class AddGameToDBController implements Initializable {

    @FXML
    private TextField gameTitle;
    @FXML
    private TextField genre;
    @FXML
    private TextField developer;
    @FXML
    private TextArea description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void addGame(ActionEvent ae) {
        SetGameInfoQueries connection = new SetGameInfoQueries ();
        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        connection.setDBURL ( account.getUserName (), account.getPassword () );
        readActiveUserFile.closeFile ();
        connection.setDBURL ( account.getUserName (), account.getPassword () );
        connection.addGameToList ( gameTitle.getText (), genre.getText (), developer.getText (), description.getText () );
        connection.close ();
        Node node = (Node) ae.getSource ();

        Stage stage = (Stage) node.getScene ().getWindow ();
        if(account.getPrivelegelevel ()==0) {
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
        else{
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
    private void addItemToListScene(ActionEvent ae) {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneAddItemToStock.fxml" ) );
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
    public void cancel(ActionEvent ae) {
        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        if (account.getPrivelegelevel () == 0) {
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
        } else {
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
        public void help() {

            Alert helpAlert = new Alert ( Alert.AlertType.INFORMATION, "" );

            helpAlert.setTitle ( "Help Menu" );

            helpAlert.getDialogPane ().setPrefWidth ( 400 );

            helpAlert.setHeaderText ( "This is the New Game Menu" );

            helpAlert.setContentText ( "Fill in all the fields with appropriate information." + System.getProperty ( "line.separator" )

                    + "Then press the 'add games' button to add the new game to the server." + System.getProperty ( "line.separator" )

                    + "Press the 'Cancel' button to go back." + System.getProperty ( "line.separator" )

                    + "Press OK to close this window." );

            helpAlert.showAndWait ();
        }
    }

