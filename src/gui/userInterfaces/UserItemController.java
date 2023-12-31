package gui.userInterfaces;

import java.io.IOException;
import java.sql.SQLException;

import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import services.UserService;

public class UserItemController {

    @FXML
    private Text userItemEmail;

    @FXML
    private ImageView userItemImg;

    @FXML
    private Text userItemName;

    @FXML
    private Text userItemRole;

    @FXML
    private Label userItemStateBtn;

    @FXML
    private ImageView userItemStateBtnImg;

    @FXML
    private Label userItemStateLabel;

    @FXML
    private Text userItemStateText;

    @FXML
    private Text userItemTel;

    @FXML
    private Label userItemUpdateBtn;

    @FXML
    private ImageView userItemUpdateBtnImg;

    public void setUserData(User user) {
        UserService userService = new UserService();
        
        userItemName.setText(user.getName());
        userItemEmail.setText(user.getEmail());
        userItemTel.setText(user.getTel());
        if (user.getRoles().equals("[\"ROLE_USER\"]")) {
            userItemRole.setText("User");
        } else if (user.getRoles().equals("[\"ROLE_ADMIN\"]")) {
            userItemRole.setText("Admin");
        }
        if (user.getState()) {

            if (!userItemStateLabel.getStyleClass().contains("userItem__field-active")) {
                userItemStateLabel.getStyleClass().add("userItem__field-active");
            }

            if (userItemStateLabel.getStyleClass().contains("userItem__field-unactive")) {
                userItemStateLabel.getStyleClass().remove("userItem__field-unactive");
            }

            userItemStateText.setText("Active");

            Image stateBtnImg = new Image(
                    getClass().getResource("/assets/img/lock-icon.png").toExternalForm());
            userItemStateBtnImg.setImage(stateBtnImg);

        } else {
            if (!userItemStateLabel.getStyleClass().contains("userItem__field-unactive")) {
                userItemStateLabel.getStyleClass().add("userItem__field-unactive");
            }

            if (userItemStateLabel.getStyleClass().contains("userItem__field-active")) {
                userItemStateLabel.getStyleClass().remove("userItem__field-active");
            }
            userItemStateText.setText("Blocked");

            Image stateBtnImg = new Image(
                    getClass().getResource("/assets/img/unlock-icon.png").toExternalForm());
            userItemStateBtnImg.setImage(stateBtnImg);

        }

        userItemStateBtn.setOnMouseClicked(event -> {
            System.out.println("user EMAIL: " + user.getEmail());
            try {
                userService.updateAccountstate(user);
                updateStateLabel(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        userItemUpdateBtn.setOnMouseClicked(event -> {
            System.out.println("user EMAIL: " + user.getEmail());

            UsersListController.setupdateUserModelShow(1);
            UsersListController.setuserEmailToUpdate(user.getEmail());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersList.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    public void updateStateLabel(User user) throws SQLException {
        if (user.getState()) {
            user.setState(false);
            if (!userItemStateLabel.getStyleClass().contains("userItem__field-active")) {
                userItemStateLabel.getStyleClass().add("userItem__field-active");
            }

            if (userItemStateLabel.getStyleClass().contains("userItem__field-unactive")) {
                userItemStateLabel.getStyleClass().remove("userItem__field-unactive");
            }

            userItemStateText.setText("Active");

            Image stateBtnImg = new Image(
                    getClass().getResource("/assets/img/lock-icon.png").toExternalForm());
            userItemStateBtnImg.setImage(stateBtnImg);

        } else {
            user.setState(true);
            if (!userItemStateLabel.getStyleClass().contains("userItem__field-unactive")) {
                userItemStateLabel.getStyleClass().add("userItem__field-unactive");
            }

            if (userItemStateLabel.getStyleClass().contains("userItem__field-active")) {
                userItemStateLabel.getStyleClass().remove("userItem__field-active");
            }
            userItemStateText.setText("Blocked");

            Image stateBtnImg = new Image(
                    getClass().getResource("/assets/img/unlock-icon.png").toExternalForm());
            userItemStateBtnImg.setImage(stateBtnImg);

        }
    }
}
