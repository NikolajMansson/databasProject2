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

public class GameSearchEmployeeController implements Initializable {

@FXML
   TextField indexTextField;
    @FXML
    TextField quantityTextField;
    @FXML
    TextField gameTitleTextField;




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

    private CartFile cartFile = new CartFile ();

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
    public void addToCart(ActionEvent ae) {
        int articleNumber = Integer.parseInt ( indexTextField.getText () );
        int quantity = Integer.parseInt ( quantityTextField.getText () );

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

        } else {
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
    public void cancel(ActionEvent ae){
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneBossWelcomeMenu.fxml" ) );
        Parent root = null;
        try {
            root = loader.load ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        Scene scene = new Scene ( root);
        stage.setScene ( scene );
    }

    @FXML
    private void help(){
        Alert helpAlert = new Alert (Alert.AlertType.INFORMATION, "");
        // Ställer in övre texten
        helpAlert.setTitle("Help Menu");
        // Ställer in bredden
        helpAlert.getDialogPane().setPrefWidth(450);
        // Ställer in mitten texten
        helpAlert.setHeaderText("This is the Employee Search Engine");
        // Ställer in brödtexten, system.getProperty("line.separator) är radbrytare"
        helpAlert.setContentText("Select which category you want to use (SSN, Username or Surname)." + System.getProperty("line.separator")
                + "Select which order you want (Ascending/Descending)." + System.getProperty("line.separator")
                + "Select which type of employee you are looking for (Boss or Regular Employee)" + System.getProperty("line.separator")
                + "Type your keywords in the field, then press the Search button." + System.getProperty("line.separator")
                + "Press OK to close this window.");
        helpAlert.showAndWait();
    }
}