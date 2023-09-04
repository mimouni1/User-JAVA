package gui.userInterfaces;

import java.io.IOException;
import java.sql.SQLException;

import entities.CodePromo;
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
import services.CodePromoService;

public class CodePromoItemController {

    @FXML
    private Text promoItemDateDebut;

    @FXML
    private Text promoItemCode;

    @FXML
    private Text promoItemDateFin;

    @FXML
    private Label userItemUpdateBtn;

    public void setCodePromoData(CodePromo codePromo) {
        CodePromoService codePromoService = new CodePromoService();
        
        promoItemCode.setText(codePromo.getCode());
        promoItemDateDebut.setText(codePromo.getDate_debut().toString());
        promoItemDateFin.setText(codePromo.getDate_fin().toString());

        // userItemUpdateBtn.setOnMouseClicked(event -> {
        //     System.out.println("user EMAIL: " + user.getEmail());

        //     UsersListController.setupdateUserModelShow(1);
        //     UsersListController.setuserEmailToUpdate(user.getEmail());
        //     FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersList.fxml"));
        //     try {
        //         Parent root = loader.load();
        //         Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

        //         // Vider la pane et afficher le contenu de ProductsList.fxml
        //         contentArea.getChildren().clear();
        //         contentArea.getChildren().add(root);
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }

        // });
    }
}
