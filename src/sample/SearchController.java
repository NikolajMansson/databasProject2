package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/21/2017.
 */
public class SearchController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addToCart()
    {

    }

    public void search()
    {

    }

    public void viewCart(ActionEvent ae) throws IOException
    {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneCart.fxml" ) );
        Parent root = loader.load ();

        Scene scene = new Scene (root);
        stage.setScene ( scene );
    }




}
