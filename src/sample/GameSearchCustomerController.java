package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameSearchCustomerController implements Initializable {

    @FXML
    TableView<TableGameSearch> tableID;
    @FXML
    TableColumn<TableGameSearch, Integer> iID;
    @FXML
    TableColumn<TableGameSearch, String> iName;
    @FXML
    TableColumn<TableGameSearch, String> iDate;
    @FXML
    TableColumn<TableGameSearch, Double> iPrice;

    @FXML
    private TextField searchfield;

    private enum SearchStatus {TITLE, DEVELOPER, PLATFORM}

    private SearchStatus searchControll = SearchStatus.TITLE;
    ArrayList<SearchResultItem> searchItemList = null;


    ItemSearchQueries connection = new ItemSearchQueries ();
    private boolean ascending = true;
    private ArrayList<TableGameSearch> data = new ArrayList<> ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    public void search(ActionEvent ae) {
        ItemSearchQueries connection = new ItemSearchQueries ();

        if (searchfield.getText ().equals ( "" )) {
            this.searchItemList = connection.getItemDefaultSearch ( ascending );

        } else if (searchControll == SearchStatus.TITLE) {
            this.searchItemList = connection.getItemTitleSearch ( searchfield.getText (), ascending );

        } else if (searchControll == SearchStatus.PLATFORM) {
            this.searchItemList = connection.getItemPlatformSearch ( searchfield.getText (), ascending );

        } else if (searchControll == SearchStatus.DEVELOPER) {
            this.searchItemList = connection.getItemDeveloperSearch ( searchfield.getText (), ascending );

        }
        else{
            return;
        }
        for (int i = 0; i < searchItemList.size (); i++) {
            TableGameSearch table = new TableGameSearch ( searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getPrice () );
            data.add ( table );
        }

        ObservableList<TableGameSearch> data2 = FXCollections.observableArrayList ( data );
        tableID.setItems ( data2 );

        iID.setCellValueFactory ( new PropertyValueFactory<TableGameSearch, Integer> ( "searchAID" ) );
        iName.setCellValueFactory ( new PropertyValueFactory<TableGameSearch, String> ( "searchTitle" ) );
        iDate.setCellValueFactory ( new PropertyValueFactory<TableGameSearch, String> ( "searchAbbreviation" ) );
        iPrice.setCellValueFactory ( new PropertyValueFactory<TableGameSearch, Double> ( "searchPrice" ) );
    }

    @FXML
    public void cancel(ActionEvent ae) {
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
    public void help() {
        Alert helpAlert = new Alert ( Alert.AlertType.INFORMATION, "" );
        helpAlert.setTitle ( "Help Menu" );
        helpAlert.getDialogPane ().setPrefWidth ( 420 );
        helpAlert.setHeaderText ( "This is the Game Search Engine" );
        helpAlert.setContentText ( "Select which category you want to use (Game Title, Developer, Platform)." + System.getProperty ( "line.separator" )
                + "Select which order you want (Ascending/Descending)." + System.getProperty ( "line.separator" )
                + "Type your keywords in the field, then press the Search button." + System.getProperty ( "line.separator" )
                + "Press OK to close this window." );
        helpAlert.showAndWait ();
    }
}