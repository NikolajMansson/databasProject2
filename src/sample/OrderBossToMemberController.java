package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Nikolaj on 2017-04-30.
 */
public class OrderBossToMemberController implements Initializable{
    @FXML
    public Button cancelButton;
    @FXML
    public Button finishSaleButton;
    @FXML
    private TextField dateOfOrderTextField;
    @FXML
    private TextField sellerTextField;
    @FXML
    private TextField customerTextField;

    private ArrayList<Item> itemList = new ArrayList<> ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void addOrder(ActionEvent ae) {
        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        PerformOrderQueries connection1 = new PerformOrderQueries ();
        SetGameInfoQueries connection2 = new SetGameInfoQueries ();
        CartFile sCart = new CartFile();
        byte[] bytesArray = sCart.readerArticleNumberFile ();
        for (int i = 0; i < bytesArray.length; i++) {
            Item item = connection1.getSalesItem ( (int) bytesArray[i] );
            this.itemList.add ( item );
        }

        //Som funktionaliteten ser ut just nu kan man endast handla ett item i taget
        for (int i = 0; i < itemList.size (); i++) {
            connection1.addRegularOrderToList ( Integer.parseInt ( dateOfOrderTextField.getText ()), customerTextField.getText (), sellerTextField.getText (),
                    itemList.get(i).getArticleNumber (), account.getUserName (), 1, itemList.get(i).getPrice ());
            connection1.increaseBossIncome ( itemList.get(i).getPrice (), account.getUserName () );
            connection1.increaseGameSoldBoss(1, account.getUserName ());
            connection2.decreaseItemAmount(1, itemList.get(i).getArticleNumber ());
        }
        sCart.cleanArticleNo ();
        sCart.cleanQuantity ();

        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneBossWelcomeMenu.fxml" ) );
        Parent root = null;
        try {
            root = loader.load ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        Scene scene = new Scene ( root, 500, 300 );
        stage.setScene ( scene );
    }

    @FXML
    public void cancel(ActionEvent ae){
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneOrderOptions.fxml" ) );
        Parent root = null;
        try {
            root = loader.load ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        Scene scene = new Scene ( root, 500, 300 );
        stage.setScene ( scene );
    }
}
