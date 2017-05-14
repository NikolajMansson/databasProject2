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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Nikolaj on 2017-04-30.
 */
public class OrderEmployeeToGuestController implements Initializable {
    @FXML
    public Button cancelButton;
    @FXML
    public Button finishSaleButton;
    @FXML
    private Label dateOfOrderLabel;

    private ArrayList<Item> itemList = new ArrayList<> ();

    LocalDate date = LocalDate.now();
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

    ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateOfOrderLabel.setText(date.format(dtf));


    }
    @FXML
    private void addOrder(ActionEvent ae) {
        readActiveUserFile.openFile ();
        Account account = readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        PerformOrderQueries connection1 = new PerformOrderQueries ();
        connection1.setDBURL ( account.getUserName (), account.getPassword () );
        SetGameInfoQueries connection2 = new SetGameInfoQueries ();
        connection2.setDBURL ( account.getUserName (), account.getPassword () );
        CartFile sCart = new CartFile();

        byte[] bytesArray2 = sCart.readerArticleNumberFile ();

        byte[] bytesArray = sCart.readerArticleNumberFile ();
        for (int i = 0; i < bytesArray.length; i++) {

            Item item = connection1.getSalesItem ( (int) bytesArray[i] );
            item.setAmountOfItems ( (int)bytesArray2[i] );
            this.itemList.add ( item);
        }

        for (int i = 0; i < itemList.size (); i++) {
            connection1.addGuestOrderToList (account.getUserName (),
                    itemList.get(i).getArticleNumber (), itemList.get(i).getAmountOfItems (), itemList.get(i).getPrice ());
            connection1.increaseEmployeeIncome ( itemList.get(i).getPrice (), account.getUserName () );
            connection1.increaseGameSoldEmployee (1, account.getUserName ());
            connection2.decreaseItemAmount(1, itemList.get(i).getArticleNumber ());
        }
        sCart.cleanArticleNo ();
        sCart.cleanQuantity ();

        Node node = (Node) ae.getSource ();
        Stage stage = (Stage) node.getScene ().getWindow ();

        FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneEmployeeWelcomeMenu.fxml" ) );
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

        Scene scene = new Scene ( root);
        stage.setScene ( scene );
    }

}
