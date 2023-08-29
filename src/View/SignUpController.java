/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;



import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import models.User;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Ikbel
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField pswd;
    @FXML
    private TextField tel;
    @FXML
    private Button signup;

    // Create an instance of the UserService class
    private UserService us = new UserService();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // Test the database connection
    List<User> users = us.afficher();
    System.out.println(users);
    }

    @FXML
private void SignUpOnAction(ActionEvent event) {
     if (name.getText().isEmpty()|| lastname.getText().isEmpty() || email.getText().isEmpty() || pswd.getText().isEmpty() || tel.getText().isEmpty() )
    {
        Alert a = new Alert(Alert.AlertType.ERROR,"Champs vides",ButtonType.OK);
    a.showAndWait();
    }
    
    else {
        User r = new User();
    r.setEmail(email.getText());
    r.setPassword(pswd.getText());
    r.setName(name.getText());
    r.setLastname(lastname.getText());
    r.setTel(tel.getText());
us.ajouter(r);

       
    Alert a = new Alert(Alert.AlertType.INFORMATION,"User ajouté",ButtonType.OK);
    a.showAndWait();
    }
}
}

    
    
     

    

