package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Nikolaj on 2017-04-19.
 */
public class AddItemToStockController implements Initializable{
    @FXML
    public Button cancel;
    @FXML
    public Button addItemButton;
    @FXML
    private TextField gameTitle;
    @FXML
    private TextField fullNameOfPlatform;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField abbreviationTextField;
    @FXML
    private TextField makerOfPlatform;
    @FXML
    private TextField amountOfItemsTextField;
    @FXML
    private TextField releaseDateTextLabel;

    private int releaseDate = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void add(ActionEvent ae) {
        try {
            if (releaseDateTextLabel.getText () != null) {
                this.releaseDate = Integer.parseInt ( releaseDateTextLabel.getText () );
            }
        }
        catch(NumberFormatException e){
            this.releaseDate = 0;
        }

        SetGameInfoQueries connection1 = new SetGameInfoQueries ();
        ItemSearchQueries connection2 = new ItemSearchQueries ();
        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();

        connection2.setDBURL ( account.getUserName (),account.getPassword () );
        ArrayList<SearchResultItem> itemList = connection2.getItemDefaultSearch ();

        for(int i = 0; i < itemList.size (); i++){
          if((itemList.get ( i ).getTitle ().equals ( gameTitle.getText () )&&(itemList.get(i).getAbbreviation ().equals(abbreviationTextField.getText ()))) ){
        connection1.pushCopies ( itemList.get(i).getAmount () ,itemList.get(i).getArticleNo () );
              Node node = (Node) ae.getSource ();

              Stage stage = (Stage) node.getScene ().getWindow ();
              if(account.getPrivelegelevel ()==0) {
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
              else{
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
        }

        connection1.setDBURL ( account.getUserName (), account.getPassword () );
            connection1.addPlatformToList ( abbreviationTextField.getText (), fullNameOfPlatform.getText (), makerOfPlatform.getText () );
            connection1.addItemToList ( abbreviationTextField.getText (), priceTextField.getText (), amountOfItemsTextField.getText (), gameTitle.getText (), releaseDate );
            connection1.close ();
        Node node = (Node) ae.getSource ();

        Stage stage = (Stage) node.getScene ().getWindow ();
        if(account.getPrivelegelevel ()==0) {
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
        else{
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
    private void cancel(ActionEvent ae) {
        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        if (account.getPrivelegelevel () == 0) {
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
        } else {
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
    public void help(){
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION, "");
        helpAlert.setTitle("Help Menu");
        helpAlert.getDialogPane().setPrefWidth(400);
        helpAlert.setHeaderText("This is the New Copy Menu");
        helpAlert.setContentText("Fill in all the fields with appropriate information." + System.getProperty("line.separator")
                + "Then press the 'add item' button to add the new copy to the server." + System.getProperty("line.separator")
                + "Press the 'Cancel' button to go back." + System.getProperty("line.separator")
                + "Press OK to close this window.");
        helpAlert.showAndWait();
    }

}
