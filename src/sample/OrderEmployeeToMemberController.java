package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Nikolaj on 2017-04-30.
 */

public class OrderEmployeeToMemberController implements Initializable {
    @FXML
    public Button cancelButton;
    @FXML
    public Button finishSaleButton;
    @FXML
    private Label dateOfOrderLabel;
    @FXML
    private TextField customerTextField;

    private ArrayList<Item> itemList = new ArrayList<> ();
    private ArrayList<Integer> quantitylist = new ArrayList<> ();

    CartFile cartFile = new CartFile ();
    ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
    private double totalPrice = 0;

    private int gamesSold = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateOfOrderLabel.setText ( date.format ( dtf ) );
    }

    LocalDateTime date = LocalDateTime.now ();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern ( "yyyyMMdd " );

    @FXML

    private void addOrder(ActionEvent ae) {
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

        for(int i = 0; i < quantitylist.size(); i++){
        this.gamesSold = gamesSold + quantitylist.get(i);
    }
    double theTotalPrice = calculateTotalPrice ( totalItemPrice );
        ArrayList<Integer> itemIdList = dbConnection.getItemIDList ( itemList, quantitylist );
        for (int i = 0; i < itemList.size(); i++) {
        dbConnection.addRegularOrderToList ( customerTextField.getText (), account.getUserName (),
                itemList.get ( i ).getArticleNumber (), itemList.get ( i ).getAmountOfItems (), itemList.get ( i ).getPrice (), itemIdList );

    }
        dbConnection.increaseEmployeeIncome ( theTotalPrice, account.getUserName () );
        dbConnection.increaseGameSoldEmployee (gamesSold, account.getUserName ());
        cartFile.cleanArticleNo ();
        cartFile.cleanQuantity ();
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
    /*
        CustomerAccount customer = new CustomerAccount ( customerTextField.getText () );

        RegularCustomerOrder order = new RegularCustomerOrder ( account, itemList, customer, quantity );

        for (int i = 0; i < itemList.size (); i++) {
            connection1.addRegularOrderToList ( customerTextField.getText (), account.getUserName (),
                    itemList.get ( i ).getArticleNumber (), itemList.get ( i ).getAmountOfItems (), itemList.get ( i ).getPrice () );
            connection1.increaseEmployeeIncome ( itemList.get ( i ).getPrice (), account.getUserName () );
            connection1.increaseGameSoldEmployee ( 1, account.getUserName () );
         }

        sCart.cleanQuantity ();
        sCart.cleanArticleNo ();

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
        */
    }


    @FXML

    public void cancel(ActionEvent ae) {
        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneOrderOptions.fxml" ) );
        Parent root = null;

        try {
            root = loader.load ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        Scene scene = new Scene ( root );
        stage.setScene ( scene );
    }
    private double calculateTotalPrice(ArrayList<Double> totalItemPriceList) {
        for (int i = 0; i < totalItemPriceList.size (); i++) {
            double itemPrice = totalItemPriceList.get ( i );
            this.totalPrice = totalPrice + itemPrice;
        }
        return totalPrice;
    }

    @FXML
    public void logout(ActionEvent ae){

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

}