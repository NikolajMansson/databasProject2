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

public class Controller implements Initializable {
    @FXML private TextField loginUsername;
    @FXML private TextField loginPassword;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EmployeeAccount.getInstance();
    }

    @FXML
    private void loginEmployeeAccount(ActionEvent ae) throws IOException {
        EmployeeAccount.getInstance().setId(loginUsername.getText());

        Node node = (Node)ae.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sceneEmployee.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
    }
}
