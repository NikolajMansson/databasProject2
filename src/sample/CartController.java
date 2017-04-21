package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by L J on 4/18/2017.
 */
public class CartController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        calculatetotalprice(Singleton.getInstance().getTotalpriceperitem());

    }


    @FXML private Label totalPrice;



    @FXML
    public void back(ActionEvent ae) throws IOException {

        Node node = (Node) ae.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneSearchFromGameLibrary.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));

    }

    @FXML
    public void cancel(ActionEvent ae) throws IOException {

        Node node = (Node) ae.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneSearchFromGameLibrary.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));



    }

    public void removeItem()
    {



    }

    public void buy()
    {

    }

    public void calculatetotalprice(ArrayList<Double> price)
    {
        double total = 0;

        for (int i = 0; i < price.size(); i++){
            total = total + price.get(i);}

        totalPrice.setText(Double.toString(total));
    }




}
