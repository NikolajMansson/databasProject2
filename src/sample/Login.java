package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private TextField loginUsername;
    @FXML
    private TextField loginPassword;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private void loginAsEmployee(ActionEvent ae) throws IOException {
        Main main = new Main();
        DBConnection connection = new DBConnection();
        boolean testUserIdPassword = connection.searchForPasswordEmployee(loginUsername.getText(), loginPassword.getText());

        if (testUserIdPassword == true) {

            EmployeeAccount account = new EmployeeAccount(loginUsername.getText(), loginPassword.getText());

            try {
                main.setMyUser(account);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Node node = (Node) ae.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneEmployee.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 500, 300);
            stage.setScene(scene);
        }

    }

    public void loginAsCustomer(ActionEvent ae) throws IOException{
        // TODO implement here
    }

    @FXML
    public void loginAsBoss(ActionEvent ae) throws IOException {
        Main main = new Main();

        DBConnection connection = new DBConnection();
        boolean testUserIdPassword = connection.searchForPasswordEmployee(loginUsername.getText(), loginPassword.getText());

        if (testUserIdPassword == true) {

            BossAccount account = new BossAccount(loginUsername.getText(), loginPassword.getText());

            try {
                main.setMyUser(account);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Node node = (Node) ae.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneBoss.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 500, 300);
            stage.setScene(scene);
        }

    }
}
