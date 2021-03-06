package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/18/2017.
 */
public class CartController implements Initializable {

    @FXML
    private Label datetime;
    @FXML
    private TextArea cartArea;
    @FXML
    private Label totalPriceLabel;
    @FXML
    TextField removeIndexTextLabel;
    private double totalPrice = 0;
    private ArrayList<Item> itemList = new ArrayList<> ();
    private ArrayList<Integer> quantitylist = new ArrayList<> ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Timeline tl = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        actionEvent -> {
                            Calendar time = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            datetime.setText(sdf.format(time.getTime()));
                        }
                        ),
                new KeyFrame(Duration.seconds(1))
        );

        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();


        cartArea.appendText ( String.format ( "%s%10s%10s%10s%10s%10s%n", "Article No.", "Game Title", "Platform", "Quantity", "Price per Item", "Price" ) );

        CartFile cartFile = new CartFile ();

        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();

        PerformOrderQueries dbConnection = new PerformOrderQueries ();
        dbConnection.setDBURL ( account.getUserName (), account.getPassword ());
        byte[] bytesArray = cartFile.readerArticleNumberFile ();
        byte[] bytesArray1 = cartFile.readerQuantityFile ();
        ArrayList<Double> totalItemPrice = new ArrayList<> ();
        for (int i = 0; i < bytesArray.length; i++) {
            Item item = dbConnection.getSalesItem ( (int) bytesArray[i] );
            item.setAmountOfItems ( bytesArray1[i] );
            this.itemList.add ( item );
            int quantity = bytesArray1[i];
            quantitylist.add ( quantity );
            totalItemPrice.add ( item.getPrice () * quantity );
        }
        for (int i = 0; i < itemList.size (); i++) {
            cartArea.appendText ( String.format ( "%d %-5s %d %.2f %.2f%n", itemList.get ( i ).getArticleNumber (), itemList.get ( i ).getGameTitle (), quantitylist.get ( i ), itemList.get ( i ).getPrice (), totalItemPrice.get ( i ) ) );
        }
        double theTotalPrice = calculateTotalPrice ( totalItemPrice );
        totalPriceLabel.setFont ( new Font ( "Arial Black", 33 ) );
        totalPriceLabel.setText ( String.format ( "%.2f", theTotalPrice ) );
        dbConnection.close ();

    }


    @FXML
    private void back(ActionEvent ae) {

        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneSearchForGameEmployee.fxml" ) );
        Parent root = null;
        try {
            root = loader.load ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        stage.setScene ( new Scene ( root ) );
    }

    @FXML
    public void cancel(ActionEvent ae) {

        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneSearchForGameEmployee.fxml" ) );
        Parent root = null;
        try {
            root = loader.load ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        stage.setScene ( new Scene ( root ) );
    }

    @FXML
    private void removeItem(ActionEvent ae) {
        int index = Integer.parseInt ( removeIndexTextLabel.getText () );

        for(int i = 0; i < itemList.size (); i++){
            if(itemList.get(i).getArticleNumber ()==index){
                this.itemList.remove ( i );
                this.quantitylist.remove(i);

            }
        }
        CartFile cartFile = new CartFile ();
        cartFile.cleanArticleNo ();
        cartFile.cleanQuantity ();

        for(int i = 0; i < itemList.size(); i++){
            cartFile.writerArticleNumberFile ( itemList.get(i).getArticleNumber () );
        }
        for(int i = 0; i < quantitylist.size(); i++){
            cartFile.writerQuantityFile ( quantitylist.get(i) );
        }

    }


    @FXML
    private void buy(ActionEvent ae) {
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

    private double calculateTotalPrice(ArrayList<Double> totalItemPriceList) {
        for (int i = 0; i < totalItemPriceList.size (); i++) {
            double itemPrice = totalItemPriceList.get ( i );
            this.totalPrice = totalPrice + itemPrice;
        }
        return totalPrice;
    }
}
