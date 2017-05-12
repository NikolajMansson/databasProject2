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
    private TextField sellerTextField;
    @FXML
    private TextField bossTextField;
    @FXML
    private TextField customerTextField;

    private ArrayList<Item> itemList = new ArrayList<> ();
    int quantity = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        dateOfOrderLabel.setText(date.format(dtf));
    }

    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd ");

    @FXML
    public void addOrder(ActionEvent ae) {
        PerformOrderQueries connection1 = new PerformOrderQueries ();
        SetGameInfoQueries connection2 = new SetGameInfoQueries ();
        CartFile sCart = new CartFile();

        byte[] bytesArray1 = sCart.readerArticleNumberFile ();
        byte[] bytesArray2 = sCart.readerArticleNumberFile ();
        for (int i = 0; i < bytesArray1.length; i++) {

            Item item = connection1.getSalesItem ( (int) bytesArray1[i] );
            item.setAmountOfItems ( (int)bytesArray2[i] );
            this.itemList.add ( item);
        }

        ReadActiveUserFile readActiveUserFile = new ReadActiveUserFile ();
        readActiveUserFile.openFile ();
        Account account = (EmployeeAccount) readActiveUserFile.readRecords ();
        readActiveUserFile.closeFile ();


        CustomerAccount customer = new CustomerAccount ( customerTextField.getText() );

        RegularCustomerOrder order = new RegularCustomerOrder ( account, itemList, customer, quantity );
        //I nedan text ska objektet läggas in istället för enskilda variabler

        for (int i = 0; i < itemList.size (); i++) {
            connection1.addRegularOrderToList ( customerTextField.getText (), account.getUserName (),
            itemList.get(i).getArticleNumber (), itemList.get(i).getAmountOfItems (  ), itemList.get(i).getPrice ());
            connection1.increaseEmployeeIncome ( itemList.get(i).getPrice (), account.getUserName () );
            connection1.increaseGameSoldEmployee (1, account.getUserName ());
            connection2.decreaseItemAmount(1, itemList.get(i).getArticleNumber ());
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
