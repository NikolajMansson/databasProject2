package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private CreateActiveUserFile createActiveUserFile = new CreateActiveUserFile ();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load ( getClass ().getResource ( "sceneLogin.fxml" ) );
        primaryStage.setTitle ( "Game Store Shopping" );
        primaryStage.setScene ( new Scene ( root, 500, 300 ) );
        primaryStage.show ();
    }


    public static void main(String[] args) {
        launch ( args );
    }

    public void setMyUser(Account account){

        createActiveUserFile.startUpload ( account );

    }
}
