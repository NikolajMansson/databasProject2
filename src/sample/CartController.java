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
 * Created by L J on 4/18/2017.
 */
public class CartController implements Initializable {

    @FXML
    private TextArea cartArea;
    @FXML
    private TextField totalPriceTextField;
    private double totalPrice = 0;
    private ArrayList<Item> itemList = new ArrayList<> ();
    private ArrayList<Integer> quantitylist = new ArrayList<>();
    private ArrayList<Double> totalItemPriceList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cartArea.appendText(String.format("%s%10s%10s%10s%10s%10s%n", "Article No." , "Game Title", "Platform", "Quantity", "Price per Item", "Price"));

        SingletonCart singletonCart = new SingletonCart ();
        DBConnection dbConnection = new DBConnection ();
        byte[] bytesArray = singletonCart.readerArticleNoFile ();
        byte[] bytesArray1 = singletonCart.readerQuantityFile();
        for (int i = 0; i < bytesArray.length; i++) {
            Item item = dbConnection.getSalesItem ( (int) bytesArray[i] );
            this.itemList.add ( item );

        }

        for (int i = 0; i < bytesArray1.length; i++) {
            Item item = dbConnection.getSalesItem ( (int) bytesArray[i] );
            quantitylist.add( (int) bytesArray1[i]);

        }
        for (int i = 0; i < itemList.size (); i++) {
            cartArea.appendText ( String.format ( "%d %-5s %d %.2f%n", itemList.get ( i ).getArticleNumber (), itemList.get ( i ).getGameTitle (), quantitylist.get(i) , itemList.get ( i ).getPrice () ) );
        }
        double theTotalPrice = calculateTotalPrice ( itemList );
        totalPriceTextField.setText ( String.valueOf ( theTotalPrice ) );

    }


    @FXML
    public void back(ActionEvent ae) throws IOException {

        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneSearchFromGameLibrary.fxml" ) );
        Parent root = loader.load ();

        stage.setScene ( new Scene ( root ) );

    }

    @FXML
    public void cancel(ActionEvent ae) throws IOException {

        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneSearchFromGameLibrary.fxml" ) );
        Parent root = loader.load ();

        stage.setScene ( new Scene ( root ) );


    }

    public void removeItem() {


    }
@FXML
    public void buy(ActionEvent ae) {
    Node node = (Node) ae.getSource ();
    Stage stage = (Stage) node.getScene ().getWindow ();

    FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneOrderOptions.fxml" ) );
    Parent root = null;
    try {
        root = loader.load ();
    } catch (IOException e) {
        e.printStackTrace ();
    }

    stage.setScene ( new Scene ( root ) );


}

    public double calculateTotalPrice(ArrayList<Item> itemList) {


        for (int i = 0; i < itemList.size (); i++) {
            double itemPrice = itemList.get ( i ).getPrice ();
            this.totalPrice = totalPrice + itemPrice;
        }
        return totalPrice;
    }


}
