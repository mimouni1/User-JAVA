package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import BookIt.bookit;
import entities.CodePromo;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.CodePromoService;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.SendMail;
import utils.TrayNotificationAlert;
import utils.UserInputValidation;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author ALI
 */
public class UserDashboardController implements Initializable {

    @FXML
    private Circle circle;

    @FXML
    private TextField codeField;

    @FXML
    private Pane content_area;

    @FXML
    private HBox dashboardBtn;

    @FXML
    private Label dashboardText;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private HBox sideBarLogout;

    @FXML
    private HBox navBarLogout;

    @FXML
    private Text navFullname;

    @FXML
    private HBox profileBtn;

    @FXML
    private ImageView profileIcon;

    @FXML
    private ImageView achatIcon;

    @FXML
    private ImageView favIcon;

    @FXML
    private Circle circle1;

    @FXML
    private Text navFullname1;

    @FXML
    private Text totalNotif;

    @FXML
    private Circle donHisImg;

    User user = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UserService userService = new UserService();

        try {
            // user = userService.getOneUser(UserSession.getInstance().getEmail());
            if (UserSession.getInstance().getEmail() == null) {
                user = userService.getOneUser("ikbel.mimouni@esprit.tn");
            } else {
                user = userService.getOneUser(UserSession.getInstance().getEmail());
            }
            /*Image img = new Image("assets/userUploads/" + user.getImgUrl());
            circle.setFill(new ImagePattern(img));
            circle1.setFill(new ImagePattern(img));*/

            navFullname.setText(user.getEmail());
            navFullname1.setText(user.getEmail());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void open_dashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        bookit.stage.getScene().setRoot(root);
    }

    @FXML
    void open_profile(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/userInterfaces/updateUserCard.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!profileBtn.getStyleClass().contains("activeLink")) {
            profileBtn.getStyleClass().add("activeLink");

            // Load the image
            Image image = new Image("assets/img/user-active.png");
            profileIcon.setImage(image);

            if (dashboardBtn.getStyleClass().contains("activeLink")) {
                dashboardBtn.getStyleClass().remove("activeLink");

                Image dashIcon = new Image("assets/img/menu.png");
                dashboardIcon.setImage(dashIcon);
            } 
        }      
   }
    
   @FXML
    void sendClicked(ActionEvent event) throws IOException {
        UserService userService = new UserService();
        CodePromoService codePromoService = new CodePromoService();

        CodePromo codePromo = codePromoService.readByCode(codeField.getText());
        if (codePromo != null) {
            user.setPromoCode(codePromo.getId());
            TrayNotificationAlert.notif("Promo code", "Code promo used successfully.",
                                NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));
        } else {
            TrayNotificationAlert.notif("Promo code", "This promo code doesn't exist",
                        NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));
        }
        
        try {
            userService.usePromoCode(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

   
    @FXML
    void logout(MouseEvent event) throws IOException {
        UserSession.getInstance().cleanUserSession();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/userInterfaces/LogIn.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
