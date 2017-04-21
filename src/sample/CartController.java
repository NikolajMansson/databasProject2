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
public class CartController implements Initializable {

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
    private PasswordField password;

    DBConnection dbc = new DBConnection ();

    @FXML
    public void back(ActionEvent ae) throws IOException {

        Node node = (Node) ae.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneSearchFromGameLibrary.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));

    }

    @FXML
    public void cancel(ActionEvent ae) throws IOException {

        Node node = (Node) ae.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneSearchFromGameLibrary.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));

    }

    public void removeItem()
    {

    }


}
