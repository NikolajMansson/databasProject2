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
    TextField articleNoTextField;
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
    TableColumn<TableGameSearch, Integer> iAmount;



    @FXML
    private TextField searchfield;

    ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();

    private CartFile cartFile = new CartFile ();

    @FXML
    private void viewCart(ActionEvent ae) {
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

    private enum SearchStatus {TITLE, DEVELOPER, PLATFORM}

    private SearchStatus searchControll = SearchStatus.TITLE;
    ArrayList<SearchResultItem> searchItemList = null;


    ItemSearchQueries connection = new ItemSearchQueries ();

    private ArrayList<TableGameSearch> data = new ArrayList<> ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void addToCart(ActionEvent ae) {
        int articleNumber = Integer.parseInt ( indexTextField.getText () );
        int quantity = Integer.parseInt ( quantityTextField.getText () );

        cartFile.writerArticleNumberFile ( articleNumber );
        cartFile.writerQuantityFile ( quantity );

    }

    @FXML
    private void removeGame(ActionEvent ae) {
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        SetGameInfoQueries dbConnection = new SetGameInfoQueries ();
        dbConnection.setDBURL ( account.getUserName (), account.getPassword () );
        int articleNo = Integer.parseInt ( articleNoTextField.getText () );
        dbConnection.removeGame ( articleNo );
    }

    @FXML
    private void setGameTitleRadioButton(ActionEvent ae) {
        this.searchControll = SearchStatus.TITLE;
    }

    @FXML
    private void setDeveloperRadioButton(ActionEvent ae) {
        this.searchControll = SearchStatus.DEVELOPER;
    }

    @FXML
    private void setPlatformRadioButton(ActionEvent ae) {
        this.searchControll = SearchStatus.PLATFORM;
    }


    @FXML
    private void search(ActionEvent ae) {
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();

        ItemSearchQueries connection = new ItemSearchQueries ();
        connection.setDBURL ( account.getUserName (), account.getPassword () );

        if (searchfield.getText ().equals ( "" )) {
            this.searchItemList = connection.getItemDefaultSearch ();

        } else if (searchControll == SearchStatus.TITLE) {
            this.searchItemList = connection.getItemTitleSearch ( searchfield.getText () );

        } else if (searchControll == SearchStatus.PLATFORM) {
            this.searchItemList = connection.getItemPlatformSearch ( searchfield.getText () );

        } else if (searchControll == SearchStatus.DEVELOPER) {
            this.searchItemList = connection.getItemDeveloperSearch ( searchfield.getText () );

        } else {
            return;
        }
        for (int i = 0; i < searchItemList.size (); i++) {
            TableGameSearch table = new TableGameSearch ( searchItemList.get ( i ).getArticleNo (), searchItemList.get ( i ).getTitle (), searchItemList.get ( i ).getAbbreviation (), searchItemList.get ( i ).getPrice (), searchItemList.get(i).getAmount () );
            data.add ( table );
        }

        ObservableList<TableGameSearch> data2 = FXCollections.observableArrayList ( data );
        tableID.setItems ( data2 );

        iID.setCellValueFactory ( new PropertyValueFactory<TableGameSearch, Integer> ( "searchAID" ) );
        iName.setCellValueFactory ( new PropertyValueFactory<TableGameSearch, String> ( "searchTitle" ) );
        iDate.setCellValueFactory ( new PropertyValueFactory<TableGameSearch, String> ( "searchAbbreviation" ) );
        iPrice.setCellValueFactory ( new PropertyValueFactory<TableGameSearch, Double> ( "searchPrice" ) );
        iAmount.setCellValueFactory ( new PropertyValueFactory<TableGameSearch, Integer> ( "searchAmount" ) );
    }


    @FXML
    public void cancel(ActionEvent ae) {
        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        if(account.getPrivelegelevel ()==0) {
            Node node = (Node) ae.getSource ();
            Stage stage = (Stage) node.getScene ().getWindow ();

            FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneBossWelcomeMenu.fxml" ) );
            Parent root = null;
            try {
                root = loader.load ();
            } catch (IOException e) {
                e.printStackTrace ();
            }

            Scene scene = new Scene ( root );
            stage.setScene ( scene );
        }
        if(account.getPrivelegelevel ()==1) {
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
    }

    @FXML
    public void help() {
        Alert helpAlert = new Alert ( Alert.AlertType.INFORMATION, "" );
        // Ställer in övre texten
        helpAlert.setTitle ( "Help Menu" );
        // Ställer in bredden
        helpAlert.getDialogPane ().setPrefWidth ( 450 );
        // Ställer in mitten texten
        helpAlert.setHeaderText ( "This is the Employee Search Engine" );
        // Ställer in brödtexten, system.getProperty("line.separator) är radbrytare"
        helpAlert.setContentText ( "Select which category you want to use (SSN, Username or Surname)." + System.getProperty ( "line.separator" )
                + "Select which order you want (Ascending/Descending)." + System.getProperty ( "line.separator" )
                + "Select which type of employee you are looking for (Boss or Regular Employee)" + System.getProperty ( "line.separator" )
                + "Type your keywords in the field, then press the Search button." + System.getProperty ( "line.separator" )
                + "Press OK to close this window." );
        helpAlert.showAndWait ();
    }
}