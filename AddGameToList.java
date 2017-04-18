package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/18/2017.
 */
public class AddGameToList implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    DBConnection dbc = new DBConnection();

    @FXML
    private TextField gameTitle;
    @FXML
    private TextField genre;
    @FXML
    private TextField developer;
    @FXML
    private TextArea description;


    @FXML public void add()
    {
        DBConnection connection = new DBConnection ();
        connection.addGameToList(gameTitle.getText (), genre.getText (), developer.getText(), description.getText ());
    }

    @FXML public void cancel()
    {

    }
}
