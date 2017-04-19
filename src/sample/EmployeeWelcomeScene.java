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
import java.util.ResourceBundle;

public class EmployeeWelcomeScene implements Initializable {
    @FXML private Label welcomeLabel;
    ReadActiveUserFile readUser = new ReadActiveUserFile ();
    EmployeeAccount account = new EmployeeAccount("", "");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readUser.openFile ();
        this.account = (EmployeeAccount) readUser.readRecords ();
        readUser.closeFile ();
        welcomeLabel.setText("Welcome " + account.getId() + "!");
    }

    @FXML
    private void returnToLogin(ActionEvent ae) throws IOException {
        Node node = (Node)ae.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneLogin.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);

    }
}
