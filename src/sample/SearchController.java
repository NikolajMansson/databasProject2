package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/21/2017.
 */
public class SearchController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextArea view;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb4;
    @FXML
    private RadioButton rb5;
    @FXML
    private RadioButton rb6;
    @FXML
    private TextField searchfield;

    public void searchbyoption() {
        // if r1 is turned on, then r2 and r3 are turned off
        // if r2 is turned on, then r1 and r3 are turned off
        // if r3 is turned on, then r1 and r2 are turned off
    }

    public void sortbyoption() {
        // if r4 is turned on, then r5 and r6 are turned off
        // if r5 is turned on, then r4 and r6 are turned off
        // if r6 is turned on, then r4 and r5 are turned off
    }


    public void addToCart() {

    }

    public void search() {

        DBConnection dbConnection = new DBConnection ();
        ArrayList<SearchResultItem> searchItemList;
        if (searchfield.getText().equals("")) {
            searchItemList = dbConnection.getItemDefaultSearch ();
            for (int i = 0; i < searchItemList.size (); i++) {
                view.appendText ( String.format ( "%d %-5s %-15s %-15s %.2f %n", searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getDeveloper (), searchItemList.get ( i ).getPrice () ) );
            }

            // view.appendText ();
        }
        else{
            searchItemList = dbConnection.getItemTextSearch (searchfield.getText ());
            for (int i = 0; i < searchItemList.size (); i++) {
                view.appendText ( String.format ( "%d %-5s %-15s %-15s %.2f %n", searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getDeveloper (), searchItemList.get ( i ).getPrice () ) );
            }

        }


    }

    public void viewCart(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneCart.fxml" ) );
        Parent root = loader.load ();

        Scene scene = new Scene ( root );
        stage.setScene ( scene );
    }


}
