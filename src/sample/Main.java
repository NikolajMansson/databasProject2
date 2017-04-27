package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static CreateActiveUserFile createActiveUserFile = new CreateActiveUserFile ();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load ( getClass ().getResource ( "sceneSearchFromGameLibrary.fxml" ) );
        primaryStage.setTitle ( "Game Store Shopping" );
        primaryStage.setScene ( new Scene ( root, 500, 300 ) );
        primaryStage.show ();
    }


    public static void main(String[] args) {
        launch ( args );
    }

    public static void setMyUser(Account account) throws IOException {

        createActiveUserFile.startUpload ( account );

    }
}
