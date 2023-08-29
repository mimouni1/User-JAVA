package View;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import services.UserService;
import utilities.MyConnection;

public class LogInController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button LogIn;
    @FXML
    private Button SignUp;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void LogInAction(ActionEvent event) throws IOException, SQLException {
        String email = usernameField.getText();
        String password = passwordField.getText();

        UserService userService = new UserService();
        User user = userService.authenticate(email, password);

        if (user == null) {
            showErrorPopup("Invalid email or password!");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceAcceuil.fxml"));
        Parent root = loader.load();
        InterfaceAcceuilController controller = loader.getController();
        controller.setEmail(email);

        Scene scene = new Scene(root);

        Stage stage = (Stage) LogIn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SignUpAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) SignUp.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void showErrorPopup(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
