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
public class OrderEmployeeToGuestController implements Initializable {
    @FXML
    public Button cancelButton;
    @FXML
    public Button finishSaleButton;
    @FXML
    private Label dateOfOrderLabel;

    @FXML
    private TextField bossTextField;
    private ArrayList<Item> itemList = new ArrayList<> ();

    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateOfOrderLabel.setText(date.format(dtf));
    }
    @FXML
    public void addOrder(ActionEvent ae) {
        PerformOrderQueries connection1 = new PerformOrderQueries ();
        SetGameInfoQueries connection2 = new SetGameInfoQueries ();
        CartFile sCart = new CartFile();
        byte[] bytesArray = sCart.readerArticleNumberFile ();
        for (int i = 0; i < bytesArray.length; i++) {
            Item item = connection1.getSalesItem ( (int) bytesArray[i] );
            this.itemList.add ( item );
        }

        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        EmployeeAccount account= (EmployeeAccount) readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();
        BossAccount boss = new BossAccount ( bossTextField.getText () );

        GuestOrder guestOrder = new GuestOrder (account , boss, itemList);

        //I nedan text ska objektet läggas in istället för enskilda variabler
        for (int i = 0; i < itemList.size (); i++) {
            connection1.addGuestOrderToList ( Integer.parseInt ( dateOfOrderLabel.getText ()), account.getUserName (),
                    itemList.get(i).getArticleNumber (), bossTextField.getText (), itemList.get(i).getPrice ());
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
