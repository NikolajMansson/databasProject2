package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class SearchGameEmployeeController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    Button removeGameButton;
    @FXML
    private TextArea view;
    @FXML
    private TextField searchfield;
    @FXML
    private TextField indexTextField;
    @FXML
    private TextField quantityfield;
    @FXML
    private TextField gameTitleTextField;

    private enum SearchStatus {TITLE, DEVELOPER, PLATFORM}

    private SearchStatus searchControll = SearchStatus.TITLE;
    private boolean ascending = true;

    @FXML
    public void cancel(ActionEvent ae) {
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

    @FXML
    public void addToCart() {
        int articleNumber = Integer.parseInt ( indexTextField.getText () );
        int quantity = Integer.parseInt ( quantityfield.getText () );
        CartFile cartFile = new CartFile ();
        cartFile.writerArticleNumberFile ( articleNumber );
       cartFile.writerQuantityFile ( quantity );

    }

    @FXML
    public void removeGame(ActionEvent ae) {
        SetGameInfoQueries dbConnection = new SetGameInfoQueries ();
        String title = gameTitleTextField.getText ();
        dbConnection.removeGame ( title );
    }

    @FXML
    public void setGameTitleRadioButton(ActionEvent ae) {
        this.searchControll = SearchStatus.TITLE;
    }

    @FXML
    public void setDeveloperRadioButton(ActionEvent ae) {
        this.searchControll = SearchStatus.DEVELOPER;
    }

    @FXML
    public void setPlatformRadioButton(ActionEvent ae) {
        this.searchControll = SearchStatus.PLATFORM;
    }

    @FXML
    public void setAscendingOrderRadioButton(ActionEvent ae) {
        this.ascending = true;
    }

    @FXML
    public void setDescendingOrderRadioButton(ActionEvent ae) {
        this.ascending = false;
    }

    @FXML
    public void search() {
        ItemSearchQueries connection = new ItemSearchQueries ();
        ArrayList<SearchResultItem> searchItemList = null;

        view.appendText ( String.format ( "%s %-15s %-15s %-15s %-15s %n", "Article No.     ", "Game Title", "Platform", "Developer", "Price" ) );

        if (searchfield.getText ().equals ( "" )) {
            searchItemList = connection.getItemDefaultSearch ( ascending );

            for (int i = 0; i < searchItemList.size (); i++) {
                view.appendText ( String.format ( "%d %-5s %-15s %-15s %.2f %n", searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getDeveloper (), searchItemList.get ( i ).getPrice () ) );
            }

        } else if (searchControll == SearchStatus.TITLE) {
            searchItemList = connection.getItemTitleSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchItemList.size (); i++) {
                view.appendText ( String.format ( "%d %-5s %-15s %-15s %.2f %n", searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getDeveloper (), searchItemList.get ( i ).getPrice () ) );
            }

        } else if (searchControll == SearchStatus.PLATFORM) {
            searchItemList = connection.getItemPlatformSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchItemList.size (); i++) {
                view.appendText ( String.format ( "%d %-5s %-15s %-15s %.2f %n", searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getDeveloper (), searchItemList.get ( i ).getPrice () ) );
            }

        } else if (searchControll == SearchStatus.DEVELOPER) {
            searchItemList = connection.getItemDeveloperSearch ( searchfield.getText (), ascending );
            for (int i = 0; i < searchItemList.size (); i++) {
                view.appendText ( String.format ( "%d %-5s %-15s %-15s %.2f %n", searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getDeveloper (), searchItemList.get ( i ).getPrice () ) );
            }
        }
    }

    public void viewCart(ActionEvent ae){
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneCart.fxml" ) );
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
