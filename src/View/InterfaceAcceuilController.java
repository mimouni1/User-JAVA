package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.User;

public class InterfaceAcceuilController implements Initializable {

    @FXML
    private BorderPane BorderPane;
    @FXML
    private Button menu;
    @FXML
    private VBox MenuPane;
    @FXML
    private Button edit;
    private String email;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @FXML
    private void handleEdit(ActionEvent event) throws IOException {
        Parent editAccount = FXMLLoader.load(getClass().getResource("EditAccount.fxml"));
        Scene editAccountScene = new Scene(editAccount);
        Stage window = (Stage) edit.getScene().getWindow();
        window.setScene(editAccountScene);
        window.show();
    }

    @FXML
    private void toggleMenu(ActionEvent event) {
        if (MenuPane.getMaxWidth() == 0) {
            // Show the menu
            MenuPane.setMaxWidth(200);
            MenuPane.setMinWidth(200);
        } else {
            // Hide the menu
            MenuPane.setMaxWidth(0);
            MenuPane.setMinWidth(0);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
