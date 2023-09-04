package gui.userInterfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;

import org.mindrot.jbcrypt.BCrypt;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import javafx.util.Duration;
import utils.SendMail;
import utils.TrayNotificationAlert;
import utils.UserInputValidation;
import utils.UserSession;

public class SignUpController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private AnchorPane left;

    @FXML
    private Hyperlink logInLink;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField phoneField;

    @FXML
    private PasswordField repassField;

    @FXML
    private RadioButton role_assoc;

    @FXML
    private RadioButton role_indiv;

    @FXML
    private Button signUpBTN;

    @FXML
    private ToggleGroup roles;

    @FXML
    void signUp(ActionEvent event) throws IOException {
        String token = UUID.randomUUID().toString();

        User user = new User(nameField.getText(),lastnameField.getText(), emailField.getText(),
                phoneField.getText(), token,
                "defaultPic.jpg",  passField.getText());

        UserService userService = new UserService();
        if (UserInputValidation.signUpValidator(user, repassField.getText(),
                userService)) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            int code = new Random().nextInt(900000) + 100000;
            user.setVerificationCode(code);
            user.setRoles("[\"ROLE_USER\"]");

            try {
                userService.ajouter(user);

                Map<String, String> data = new HashMap<>();
                data.put("emailSubject", "Confirm your email address for Book IT");
                data.put("titlePlaceholder", "Confirm Your Email Address");
                data.put("msgPlaceholder", "Here's the code to confirm your email address:");

                SendMail.send(user, data);

                System.out.println("sent");

                TrayNotificationAlert.notif("sign Up", "account created successfully, Please confirm your email.",
                        NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

                UserSession.getInstance().setEmail(user.getEmail());
                Parent root = FXMLLoader.load(getClass().getResource("ConfirmEmail.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (SQLException e) {
                e.getMessage();
                System.out.println("error: " + e.getMessage());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {

        }

        // to check password
        // if (BCrypt.checkpw(candidate, hashed))
        // System.out.println("It matches");
        // else
        // System.out.println("It does not match");
    }

    @FXML
    void toLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
