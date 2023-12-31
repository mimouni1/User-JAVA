package gui.userInterfaces;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import org.mindrot.jbcrypt.BCrypt;

import entities.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.SendMail;
import utils.TrayNotificationAlert;
import utils.UserSession;
import java.awt.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javafx.scene.input.MouseEvent;
import nl.captcha.Captcha;
import javafx.scene.shape.Rectangle;

public class LogInController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private AnchorPane left;

    @FXML
    private Button loginBTN;

    @FXML
    private PasswordField passField;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private AnchorPane googleBTN;

    @FXML
    private TextField captchaField;

    @FXML
    private ImageView captchaImg;

    Captcha captcha;
    Boolean captchaTest = true;

    @FXML
    void logIn(ActionEvent event) throws IOException {

        String email = emailField.getText();
        String password = passField.getText();

        UserService userService = new UserService();
        User user;

        if (captcha.isCorrect(captchaField.getText())) {
            captchaTest = true;
        } else {
            captchaTest = false;
            captcha = setCaptcha();
        }

        try {
            user = userService.getOneUser(email);
            if (!captchaTest) {
                TrayNotificationAlert.notif("Login", "Invalid captcha.",
                        NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));
            } else if (user.getId() == -999) {
                TrayNotificationAlert.notif("Login", "Invalid credentials.",
                        NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));
            } else {
                // System.out.println(user);
                if (BCrypt.checkpw(password, user.getPassword().replace("$2y$", "$2a$"))) {

                    if (!user.getState()) {
                        TrayNotificationAlert.notif("Login", "Your account is blocked.",
                        NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));

                        return;
                    } else if (user.getIsVerified()) {
                        System.out.println("logged in");
                        TrayNotificationAlert.notif("Login", "logged in successfully.",
                                NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));
                        UserSession.getInstance().setEmail(user.getEmail());
                        user.setState(true);
                        
                        if (user.getRoles().equals("[\"ROLE_USER\"]"))
                               {
                            System.out.println("to the USERDASHBOARD");
                            Parent root = FXMLLoader.load(getClass().getResource("/gui/UserDashboard.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } else if (user.getRoles().equals("[\"ROLE_ADMIN\"]")) {
                            Parent root = FXMLLoader.load(getClass().getResource("/gui/AdminDashboard.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        }
                    }  else if (!user.getIsVerified()) {
                        Map<String, String> data = new HashMap<>();
                        data.put("emailSubject", "Confirm your email address for Book IT");
                        data.put("titlePlaceholder", "Confirm Your Email Address");
                        data.put("msgPlaceholder", "Here's the code to confirm your email address:");

                        SendMail.send(user, data);

                        UserSession.getInstance().setEmail(user.getEmail());
                        Parent root = FXMLLoader.load(getClass().getResource("ConfirmEmail.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                        TrayNotificationAlert.notif("Login", "Please verify your email.",
                                NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));
                        
                     }
                } else {
                    TrayNotificationAlert.notif("Login", "Invalid credentials.",
                            NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));
                }
            } 
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void toSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void forgotPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void google(MouseEvent event) throws IOException, URISyntaxException {
        // Desktop.getDesktop().browse(new URI("http://www.google.com"));
        // Parent root = FXMLLoader.load(getClass().getResource("GoogleAuth.fxml"));
        // Scene scene = new Scene(root);
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // stage.setScene(scene);
        // stage.show();

        String myVariable = "undefined"; // replace with your variable value

        try {
            URL url = new URL("http://localhost:5000/my-variable"); // replace with your URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // set request method and headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // create JSON payload
            String payload = "{\"myVariable\": \"" + myVariable + "\"}";

            // send payload
            OutputStream os = connection.getOutputStream();
            os.write(payload.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            // read response
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        Desktop.getDesktop().browse(new URI("http://localhost/register/"));
        Parent root = FXMLLoader.load(getClass().getResource("GoogleAuth.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        captcha = setCaptcha();
    }

    public Captcha setCaptcha() {
        Captcha captchaV = new Captcha.Builder(250, 150)
                .addText()
                .addBackground()
                .addNoise()
                // .gimp()
                .addBorder()
                .build();

        // System.out.println(captchaV.getImage());
        WritableImage image = SwingFXUtils.toFXImage(captchaV.getImage(), null);

        captchaImg.setImage(image);

        // Rectangle clip = new Rectangle(
        // captchaImg.getFitWidth(), captchaImg.getFitHeight());
        // clip.setArcWidth(100);
        // clip.setArcHeight(100);

        // captchaImg.setClip(clip);

        return captchaV;
    }

}
