/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import javafx.scene.control.Alert;
import utilities.MyConnection;
import services.UserService;

public class EditAccountController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private Button save;
    @FXML
    private Button retour;
    @FXML
    private Button load;
    @FXML
    private TextField tel;
    @FXML
    private TextField lastname;
    @FXML
    private PasswordField newpswd;
    @FXML
    private TextField emailTextField;

    private UserService userService;
    private User loggedInUser;
    

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 User currentUser = UserService.getCurrentUser();
        if (currentUser != null) {
            emailTextField.setText(currentUser.getEmail());
            name.setText(currentUser.getName());
            lastname.setText(currentUser.getLastname());
            tel.setText(currentUser.getTel());
    }
    }

    @FXML
    private void retourAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InterfaceAcceuil.fxml"));
        Scene scene = new Scene(root);

        // Set the main application interface on the primary stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
private void loadAction(ActionEvent event) throws IOException, SQLException {
    Connection cnx = MyConnection.getInstance().getCnx();
    PreparedStatement pst = cnx.prepareStatement("SELECT * FROM user WHERE email=?");
    pst.setString(1, emailTextField.getText());

    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
        email.setText(rs.getString("email"));
        name.setText(rs.getString("name"));
        lastname.setText(rs.getString("lastname"));
        tel.setText(rs.getString("tel"));
    }
}

/*void setEmail(String email) {
    
    this.email = email;
    emailTextField.setText(email);
    
    try {
        loadAction(null); // pass null as argument since we don't use the event object
    } catch (IOException | SQLException ex) {
        // handle exceptions
    }
}*/

     @FXML
private void saveAction(ActionEvent event) throws IOException, SQLException {
    // get the updated user information from the form fields
    
    String name = this.name.getText();
    String lastname = this.lastname.getText();
    String Tel = this.tel.getText();
    String email = this.emailTextField.getText();
    String newPassword = this.newpswd.getText();

    // update the user information in the database
    Connection cnx = MyConnection.getInstance().getCnx();
    PreparedStatement pst = cnx.prepareStatement("UPDATE user SET password=?, name=?, lastname=?, tel=? WHERE email=?");
    pst.setString(1, newPassword);
    pst.setString(2, name);
    pst.setString(3, lastname);
    pst.setString(4, Tel);
    pst.setString(5, email);
    pst.executeUpdate();

    // show a success message and redirect to the home page
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText("User information has been updated successfully!");
    alert.showAndWait();

    Parent root = FXMLLoader.load(getClass().getResource("InterfaceAcceuil.fxml"));
    Scene scene = new Scene(root);

    // Set the main application interface on the primary stage
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    } 


}
