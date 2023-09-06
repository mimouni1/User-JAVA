package BookIt;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class bookit extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
             root = FXMLLoader.load(getClass().getResource("/gui/userInterfaces/LogIn.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/gui/UserDashboard.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/gui/SignUp.fxml"));
            Scene scene = new Scene(root);

            primaryStage.getIcons().add(new Image("/assets/img/logo.png"));
            primaryStage.setTitle("Book IT");
            primaryStage.setScene(scene);
            bookit.stage = primaryStage;
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        launch(args);
    }
}