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

public class Controller2 implements Initializable {
    @FXML private Label welcomeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Employee.getInstance();
        welcomeLabel.setText("Welcome " + Employee.getInstance().getUsername() + "!");
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
