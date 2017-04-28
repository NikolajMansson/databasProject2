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
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/21/2017.
 */
public class SearchController implements Initializable{
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextArea view;
    @FXML
    private TextField searchfield;
    @FXML
    private TextField indexTextField;

    private enum SearchStatus {TITLE, DEVELOPER, PLATFORM}

    ;
    SearchStatus searchControll;
    private boolean ascending = true;




    public void addToCart() throws IOException {
        int articleNumber = Integer.parseInt ( indexTextField.getText() );
        DBConnection dbconnection = new DBConnection ();

        SingletonCart singletonCart = new SingletonCart ();

        try {
            singletonCart.writer (articleNumber );

           // singletonCart.openFileInput ();
        } catch (IOException e) {
            e.printStackTrace ();
        }


    }

    public void setGameTitleRadioButton() {
        this.searchControll = SearchStatus.TITLE;
    }

    public void setDeveloperRadioButton() {
        this.searchControll = SearchStatus.DEVELOPER;
    }

    public void setPlatformRadioButton() {
        this.searchControll = SearchStatus.PLATFORM;
    }

    public void setAscendingOrderRadioButton() {
        this.ascending = true;
    }

    public void setDescendingOrderRadioButton() {
        this.ascending = false;
    }


    public void search() {

        DBConnection dbConnection = new DBConnection ();
        ArrayList<SearchResultItem> searchItemList;
        if (searchfield.getText ().equals ( "" )) {
            searchItemList = dbConnection.getItemDefaultSearch ( ascending );
            for (int i = 0; i < searchItemList.size (); i++) {
                view.appendText ( String.format ( "%d %-5s %-15s %-15s %.2f %n", searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getDeveloper (), searchItemList.get ( i ).getPrice () ) );
            }

            // view.appendText ();
        } else if (searchControll == SearchStatus.TITLE) {
            searchItemList = dbConnection.getItemTitleSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchItemList.size (); i++) {
                view.appendText ( String.format ( "%d %-5s %-15s %-15s %.2f %n", searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getDeveloper (), searchItemList.get ( i ).getPrice () ) );
            }

        } else if (searchControll == SearchStatus.PLATFORM) {
            searchItemList = dbConnection.getItemPlatformSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchItemList.size (); i++) {
                view.appendText ( String.format ( "%d %-5s %-15s %-15s %.2f %n", searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getDeveloper (), searchItemList.get ( i ).getPrice () ) );
            }

        } else if (searchControll == SearchStatus.DEVELOPER) {
            searchItemList = dbConnection.getItemDeveloperSearch ( searchfield.getText (), ascending );
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
