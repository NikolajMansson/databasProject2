package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Nikolaj on 2017-04-19.
 */
public class AddItemToList {
    @FXML
    Button cancel;
    @FXML
    Button addItemButton;
    @FXML
    TextField gameTitle;
    @FXML
    TextField fullNameOfPlatform;
    @FXML
    TextField priceTextField;
    @FXML
    TextField abbreviationTextField;
    @FXML
    TextField makerOfPlatform;



    @FXML
    public void add() {
        DBConnection connection = new DBConnection ();
        Platform platform = new Platform(abbreviationTextField.getText (), fullNameOfPlatform.getText (), makerOfPlatform.getText ());
        int price = Integer.parseInt ( priceTextField.getText () );
        Item item = new Item(gameTitle.getText (), price, platform);
        connection.addItemToList (gameTitle.getText (), platform.getAbbreviation (), item.getArticleNumber (), item.getPrice ());
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
