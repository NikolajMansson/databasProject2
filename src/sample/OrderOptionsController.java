package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class OrderOptionsController implements Initializable {
    @FXML
    public Button okButton;
    @FXML
    public Button cancelButton;
    @FXML
    public RadioButton memberRadioButton;
    @FXML
    public RadioButton guestRadioButton;
    @FXML
    public RadioButton bossRadioButton;
    @FXML
    public RadioButton regularEmployeeRadioButton;
    private enum EmployeeStatus {BOSS, EMPLOYEE}
    private enum CustomerStatus {MEMBER, GUEST}
    private EmployeeStatus employeeStatusControll = EmployeeStatus.BOSS;
    private CustomerStatus customerStatusControll = CustomerStatus.MEMBER;

    @FXML
    public void setMemberRadioButton(ActionEvent ae){
        this.customerStatusControll=CustomerStatus.MEMBER;
    };
    @FXML
    public void setGuestRadioButton(ActionEvent ae){
        this.customerStatusControll=CustomerStatus.GUEST;
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void changeScene(ActionEvent ae) {


        if(customerStatusControll==CustomerStatus.GUEST) {
            Node node = (Node) ae.getSource ();
            Stage stage = (Stage) node.getScene ().getWindow ();

            FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneOrderEmployeeToGuest.fxml" ) );
            Parent root = null;
            try {
                root = loader.load ();
            } catch (IOException e) {
                e.printStackTrace ();
            }

            Scene scene = new Scene ( root, 500, 300 );
            stage.setScene ( scene );
        }

        else if(customerStatusControll==CustomerStatus.MEMBER){
            Node node = (Node) ae.getSource ();
            Stage stage = (Stage) node.getScene ().getWindow ();

            FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneOrderEmployeeToMember.fxml" ) );
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
}
