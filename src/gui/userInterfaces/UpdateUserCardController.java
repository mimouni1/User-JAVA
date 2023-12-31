package gui.userInterfaces;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import BookIt.bookit;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.nio.file.Files;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;
import utils.UserInputValidation;
import javafx.scene.Node;

public class UpdateUserCardController implements Initializable {

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private TextField lastnameInput;

    @FXML
    private TextField fbInput;

    @FXML
    private TextField nameInput;

    @FXML
    private ImageView imageInput;

    @FXML
    private TextField instaInput;

    @FXML
    private TextField telInput;

    @FXML
    private TextField twitterInput;

    @FXML
    private Button update_userBtn;

    User userToUpdate;
    private File selectedImageFile;
    private String imageName;
    private static String FxmlToLoad;

    public static String getFxmlToLoad() {
        return FxmlToLoad;
    }

    public static void setFxmlToLoad(String FxmlToLoad) {
        UpdateUserCardController.FxmlToLoad = FxmlToLoad;
    }

    @FXML
    void ajouter_image(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "assets", "userUploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @FXML
    void updateUser(MouseEvent event) {

        UserService userService = new UserService();

        userToUpdate.setName(nameInput.getText());
        userToUpdate.setLastname(lastnameInput.getText());
        userToUpdate.setTel(telInput.getText());
        userToUpdate.setFbLink(fbInput.getText());
        userToUpdate.setTwitterLink(twitterInput.getText());
        userToUpdate.setInstaLink(instaInput.getText());

        try {
            if (UserInputValidation.updateAccountValidator(userToUpdate)) {
                userService.update(userToUpdate);
                UsersListController.setupdateUserModelShow(0);
                UserProfileController.setupdateUserModelShow(0);

                if (FxmlToLoad.equals("UserDashboard.fxml")) {
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/UserDashboard.fxml"));
                    bookit.stage.getScene().setRoot(root);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlToLoad));
                    Parent root = loader.load();
                    Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                    contentArea.getChildren().clear();
                    contentArea.getChildren().add(root);
                }
                TrayNotificationAlert.notif("Update account", "Account updated successfully.",
                        NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setUserUpdateData(User user) {
        userToUpdate = user;
        nameInput.setText(user.getName());
        lastnameInput.setText(user.getLastame());
        telInput.setText(user.getTel());
        fbInput.setText(user.getFbLink());
        twitterInput.setText(user.getTwitterLink());
        instaInput.setText(user.getInstaLink());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
