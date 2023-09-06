package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BookIt.bookit;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.UserService;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author ALI
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Circle circle;

    @FXML
    private Pane content_area;

    @FXML
    private HBox dashboardBtn;

    @FXML
    private Label dashboardText;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private HBox usersBtn;

    @FXML
    private Label usersText;

    @FXML
    private ImageView usersIcon;

    @FXML
    private HBox sideBarLogout;

    @FXML
    private HBox navBarLogout;

    @FXML
    private Text navFullname;

    @FXML
    private HBox chartContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user;
        UserService userService = new UserService();
        int activeNB = 0;
        int unActiveNB = 0;

        try {
            // user = userService.getOneUser(UserSession.getInstance().getEmail());
            if (UserSession.getInstance().getEmail() == null) {
                user = userService.getOneUser("ikbel.mimouni@esprit.tn");
            } else {
                user = userService.getOneUser(UserSession.getInstance().getEmail());
            }
          //  Image img = new Image("assets/userUploads/" + user.getImgUrl());
         //   circle.setFill(new ImagePattern(img));

            navFullname.setText(user.getEmail());
            //activeNB = userService.getActiveNB();
            //unActiveNB = userService.getunActiveNB();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // create the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        // set the labels for the axes
        xAxis.setLabel("Number of users");
        // yAxis.setLabel("Value");

        // create the bar chart
        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        // add the data to the bar chart
       // barChart.getData().addAll(series, series2);

        final PieChart pieChart = new PieChart();

        // create the data
        final PieChart.Data data1 = new PieChart.Data("Active", activeNB);
        final PieChart.Data data2 = new PieChart.Data("Unactive", unActiveNB);

        // add the data to the pie chart
        pieChart.getData().addAll(data1, data2);

        chartContainer.getChildren().addAll(barChart, pieChart);
    }

    @FXML
    private void open_dashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        bookit.stage.getScene().setRoot(root);
    }

    @FXML
    private void open_usersList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/userInterfaces/UsersList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!usersBtn.getStyleClass().contains("activeLink")) {
            usersBtn.getStyleClass().add("activeLink");
            usersText.getStyleClass().add("activeText");

            // Load the image
            Image image = new Image("assets/img/user-active.png");
            usersIcon.setImage(image);

            if (dashboardBtn.getStyleClass().contains("activeLink")) {
                dashboardBtn.getStyleClass().remove("activeLink");
                dashboardText.getStyleClass().remove("activeText");

                Image dashIcon = new Image("assets/img/menu.png");
                dashboardIcon.setImage(dashIcon);

            }
        }
    }

    @FXML
    private void open_codeList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/userInterfaces/CodePromoList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!usersBtn.getStyleClass().contains("activeLink")) {
            usersBtn.getStyleClass().add("activeLink");
            usersText.getStyleClass().add("activeText");

            // Load the image
            Image image = new Image("assets/img/user-active.png");
            usersIcon.setImage(image);

            if (dashboardBtn.getStyleClass().contains("activeLink")) {
                dashboardBtn.getStyleClass().remove("activeLink");
                dashboardText.getStyleClass().remove("activeText");

                Image dashIcon = new Image("assets/img/menu.png");
                dashboardIcon.setImage(dashIcon);

            }
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

