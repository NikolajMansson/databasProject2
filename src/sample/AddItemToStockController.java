package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Nikolaj on 2017-04-19.
 */
public class AddItemToStockController {
    @FXML
    public Button cancel;
    @FXML
    public Button addItemButton;
    @FXML
    private TextField gameTitle;
    @FXML
    private TextField fullNameOfPlatform;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField abbreviationTextField;
    @FXML
    private TextField makerOfPlatform;
    @FXML
    private TextField amountOfItemsTextField;

    @FXML
    private void add(ActionEvent ae) {
        SetGameInfoQueries connection = new SetGameInfoQueries ();
        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        connection.setDBURL ( account.getUserName (), account.getPassword () );
        connection.addPlatformToList ( abbreviationTextField.getText (), fullNameOfPlatform.getText (), makerOfPlatform.getText () );
        connection.addItemToList ( abbreviationTextField.getText (), priceTextField.getText (), amountOfItemsTextField.getText (), gameTitle.getText () );
        connection.close ();
    }

    @FXML
    public void cancel(ActionEvent ae){
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
    public void help(){
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION, "");
        // Ställer in övre texten
        helpAlert.setTitle("Help Menu");
        // Ställer in bredden
        helpAlert.getDialogPane().setPrefWidth(400);
        // Ställer in mitten texten
        helpAlert.setHeaderText("This is the New Copy Menu");
        // Ställer in brödtexten, system.getProperty("line.separator) är radbrytare"
        helpAlert.setContentText("Fill in all the fields with appropriate information." + System.getProperty("line.separator")
                + "Then press the 'add item' button to add the new copy to the server." + System.getProperty("line.separator")
                + "Press the 'Cancel' button to go back." + System.getProperty("line.separator")
                + "Press OK to close this window.");
        helpAlert.showAndWait();
    }
}
