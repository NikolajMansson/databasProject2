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
    Button okButton;
    @FXML
    Button cancelButton;
    @FXML
    RadioButton memberRadioButton;
    @FXML
    RadioButton guestRadioButton;
    @FXML
    RadioButton bossRadioButton;
    @FXML
    RadioButton regularEmployeeRadioButton;
    private enum EmployeeStatus {BOSS, EMPLOYEE}
    private enum CustomerStatus {MEMBER, GUEST}
    EmployeeStatus employeeStatusControll = EmployeeStatus.BOSS;
    CustomerStatus customerStatusControll = CustomerStatus.MEMBER;

    @FXML
    public void setMemberRadioButton(ActionEvent ae){
        this.customerStatusControll=CustomerStatus.MEMBER;
    };
    @FXML
    public void setGuestRadioButton(ActionEvent ae){
        this.customerStatusControll=CustomerStatus.GUEST;
    };
    @FXML
    public void setBossRadioButton(ActionEvent ae){
        this.employeeStatusControll=EmployeeStatus.BOSS;
    };
    @FXML
    public void setEmployeeRadioButton(ActionEvent ae){
        this.employeeStatusControll=EmployeeStatus.EMPLOYEE;
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void changeScene(ActionEvent ae) throws IOException {
        if((employeeStatusControll==EmployeeStatus.BOSS)&&(customerStatusControll==CustomerStatus.GUEST)) {
            Node node = (Node) ae.getSource ();
            Stage stage = (Stage) node.getScene ().getWindow ();

            FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneOrderBossToGuest.fxml" ) );
            Parent root = loader.load ();

            Scene scene = new Scene ( root, 500, 300 );
            stage.setScene ( scene );
        }
        else if((employeeStatusControll==EmployeeStatus.EMPLOYEE)&&(customerStatusControll==CustomerStatus.GUEST)) {
            Node node = (Node) ae.getSource ();
            Stage stage = (Stage) node.getScene ().getWindow ();

            FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneOrderEmployeeToGuest.fxml" ) );
            Parent root = loader.load ();

            Scene scene = new Scene ( root, 500, 300 );
            stage.setScene ( scene );
        }
        else if((employeeStatusControll==EmployeeStatus.BOSS)&&(customerStatusControll==CustomerStatus.MEMBER)) {
            Node node = (Node) ae.getSource ();
            Stage stage = (Stage) node.getScene ().getWindow ();

            FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneOrderBossToMember.fxml" ) );
            Parent root = loader.load ();

            Scene scene = new Scene ( root, 500, 300 );
            stage.setScene ( scene );
        }
        else if((employeeStatusControll==EmployeeStatus.EMPLOYEE)&&(customerStatusControll==CustomerStatus.MEMBER)){
            Node node = (Node) ae.getSource ();
            Stage stage = (Stage) node.getScene ().getWindow ();

            FXMLLoader loader = new FXMLLoader ( getClass ().getResource ( "sceneOrderEmployeeToMember.fxml" ) );
            Parent root = loader.load ();

            Scene scene = new Scene ( root, 500, 300 );
            stage.setScene ( scene );
        }
    }
}
