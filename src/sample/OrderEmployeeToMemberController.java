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
public class OrderEmployeeToMemberController implements Initializable {
    @FXML
    Button cancelButton;
    @FXML
    Button finishSaleButton;
    @FXML
    TextField dateOfOrderTextField;
    @FXML
    TextField sellerTextField;
    @FXML
    TextField bossTextField;
    @FXML
    TextField customerTextField;
    ArrayList<Item> itemList = new ArrayList<> ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void addOrder(ActionEvent ae) {
        DBConnection dbConnection = new DBConnection ();
        CartFile sCart = new CartFile();
        byte[] bytesArray = sCart.readerArticleNoFile ();
        for (int i = 0; i < bytesArray.length; i++) {
            Item item = dbConnection.getSalesItem ( (int) bytesArray[i] );
            this.itemList.add ( item );
        }

        //Som funktionaliteten ser ut just nu kan man endast handla ett item i taget
        for (int i = 0; i < itemList.size (); i++) {
            dbConnection.addRegularOrderToList ( Integer.parseInt ( dateOfOrderTextField.getText ()), customerTextField.getText (), sellerTextField.getText (),
            itemList.get(i).getArticleNumber (), bossTextField.getText (), 1, itemList.get(i).getPrice ());
            dbConnection.increaseEmployeeIncome ( itemList.get(i).getPrice (), sellerTextField.getText () );
            dbConnection.increaseGameSoldEmployee(1, sellerTextField.getText());
            dbConnection.decreaseItemAmount(1, itemList.get(i).getArticleNumber ());
        }

        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneEmployee.fxml" ) );
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
