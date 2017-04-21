package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/18/2017.
 */
public class AddGameToList implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    DBConnection dbc = new DBConnection ();

    @FXML
    private TextField gameTitle;
    @FXML
    private TextField genre;
    @FXML
    private TextField developer;
    @FXML
    private TextArea description;


    @FXML
    pub DBConnection connection = new DBConnection ();
    ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
    Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        connection.setDBURL ( account.getUserName (), account.getPassword () );
        connection.addGameToList ( gameTitle.getText (), genre.getText (), developer.getText (), description.getText () );
    }

    @FXML
    public void cancel(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneLogin.fxml" ) );
        Parent root = loader.load ();

        Scene scene = new Scene ( root, 500, 300 );
        stage.setScene ( scene );
    }
}
