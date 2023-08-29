/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import interfaces.Interface_IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.User;

import utilities.MyConnection;

/**
 *
 * @author Ikbel
 */
public class UserService implements Interface_IService <User> {
    
    Connection cnx=MyConnection.getInstance().getCnx();
    
  private static User currentUser;
  
 public User authenticate(String email, String password) throws SQLException {
    // ...
    String req="SELECT * FROM user WHERE `email`='"+email+"'";
    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery(req);
    // check if the user exists
    if(rs.next()) {
        // check if the provided password matches the retrieved user's password
        String retrievedPassword = rs.getString("password");
        if (retrievedPassword.equals(password)) {
            // create a User object with the retrieved user information and return it
            User user = new User();
            user.setEmail(email);
            user.setName(rs.getString("name"));
            user.setLastname(rs.getString("lastname"));
            user.setTel(rs.getString("tel"));
            // save the user in the currentUser static variable
            currentUser = user;

            return user;
        }
    }
    // if the user does not exist or the password is incorrect, return null
    return null;
}

    public static User getCurrentUser() {
        return currentUser;
    }
    
    @Override
    public void ajouter(User user) {
    try {
        String req = "INSERT INTO `user` ( `email`, `roles`, `password`, `name`, `lastname`, `tel`, `img_url`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getRoles());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getName());
        ps.setString(5, user.getLastname());
        ps.setString(6, user.getTel());
        ps.setString(7, user.getImg_url());
        //ps.setInt(9, user.getCodePromo().getId_codePromo());
       
        ps.executeUpdate();
        System.out.println("User Added Successfully!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
     


    
    @Override
   public void modifier(User user) {
    try {
        String req = "UPDATE user SET email = ?, password = ?, name = ?, lastname = ?, tel = ?, img_url = ? WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());
        ps.setString(4, user.getLastname());
        ps.setString(5, user.getTel());
        ps.setString(6, user.getImg_url());
        //ps.setInt(9, user.getCodePromo().getId_codePromo());
        ps.executeUpdate();
        System.out.println("User Updated Successfully!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   
    @Override
   public void supprimer(int id) {
    try {
        String req = "DELETE FROM user WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("User Deleted Successfully!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
   public ArrayList<User> afficher() {
       CodePromoService us=new CodePromoService();
    ArrayList<User> users = new ArrayList<>();
    try {
        String req = "SELECT * FROM user";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setEmail(rs.getString(2));
            user.setRoles(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setIsVerified(rs.getInt(5));
            user.setName(rs.getString(6));
            user.setLastname(rs.getString(7));
            user.setTel(rs.getString(8));
            user.setImg_url(rs.getString(9));
            //user.setCodePromo(us.readById(rs.getInt(10)));
            users.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return users;
}


   
    @Override
    public User readById(int id) {
       // CodePromoService us=new CodePromoService();
        User user=new User();
        try {
        String req="SELECT * FROM user WHERE `id`='"+id+"'";
        Statement ste = cnx.createStatement();
        ResultSet rs=ste.executeQuery(req);
        while(rs.next()){
            
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setRoles(rs.getString("roles"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setLastname(rs.getString("lastname"));
            user.setTel(rs.getString("tel"));
            user.setImg_url(rs.getString("img_url"));
           // user.setCodePromo(us.readById(rs.getInt("codepromo_id")));
            
        }
           } catch (SQLException ex) {
        ex.printStackTrace();    }
       return user;
    }

   
    public User readByEmail(String email) {
    //CodePromoService us=new CodePromoService();
    User user=new User();
    try {
        String req="SELECT * FROM user WHERE `email`='"+email+"'";
        Statement ste = cnx.createStatement();
        ResultSet rs=ste.executeQuery(req);
        while(rs.next()){
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setRoles(rs.getString("roles"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setLastname(rs.getString("lastname"));
            user.setTel(rs.getString("tel"));
            user.setImg_url(rs.getString("img_url"));
           // user.setCodePromo(us.readById(rs.getInt("codepromo_id")));
           
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return user;
}

    
    @Override
    public ArrayList<User> sortBy(String nom_column, String Asc_Dsc) {
        ArrayList<User>users=new ArrayList();
        //CodePromoService us=new CodePromoService();
       try {
            String req="SELECT * FROM user ORDER BY "+nom_column+" "+Asc_Dsc+"";
            Statement ste = cnx.createStatement();
            ResultSet rs=ste.executeQuery(req);
         while(rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setEmail(rs.getString("roles"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setLastname(rs.getString("lastname"));
            user.setTel(rs.getString("tel"));
            //user.setCodePromo(us.readById(rs.getInt("codepromo_id")));
             users.add(user);
            
        }   } catch (SQLException ex) {
        ex.printStackTrace(); ;
    }
     return users;
    }
}
