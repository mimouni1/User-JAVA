package services;

import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.MyDB;

public class UserService implements IUserService {

    public Connection conx;
    public Statement stm;

    public UserService() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(User user) throws SQLException {
        String req = "INSERT INTO `user`( `email`, `roles`,`password`,`is_verified`,`name`, `lastname`,`tel`,`img_url`,`token`,`verification_code`) VALUES (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getRoles());
        ps.setString(3, user.getPassword());
        ps.setBoolean(4, user.getIsVerified());
        ps.setString(5, user.getName());
        ps.setString(6, user.getLastame());
        ps.setString(7, user.getTel());
        ps.setString(8, user.getImgUrl());
        ps.setString(9, user.getToken());
        ps.setInt(10, user.getVerificationCode());
        
        ps.executeUpdate();
        System.out.println("User added successfully");
        ps.close();
    }

     public void update(User user) throws SQLException {
        String req = "UPDATE `user` SET `password`=?,`name`=?,`lastname`=?,`tel`=?,`img_url`=?, `token`=?,`fb_link`=?,`twitter_link`=?,`insta_link`=?,`verification_code`=? WHERE email=?";

        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, user.getPassword());
        ps.setString(2, user.getName());
        ps.setString(3, user.getLastame());
        ps.setString(4, user.getTel());
        ps.setString(5, user.getImgUrl());  
        ps.setString(6, user.getToken());
        ps.setString(7, user.getFbLink());
        ps.setString(8, user.getTwitterLink());
        ps.setString(9, user.getInstaLink());
        ps.setInt(10,user.getVerificationCode());
        ps.setString(11, user.getEmail());
        
    
        ps.executeUpdate();
        System.out.println("User updated successfully");
        ps.close();
    }

    public void verifyAccount(User user) throws SQLException {
        String req = "UPDATE `user` SET `is_verified`=? WHERE email=?";

        PreparedStatement ps = conx.prepareStatement(req);
        ps.setBoolean(1, user.getIsVerified());
        ps.setString(2, user.getEmail());
        
    
        ps.executeUpdate();
        System.out.println("User Verified successfully");
        ps.close();
    }

    public void updateAccountstate(User user) throws SQLException {
        String req = "UPDATE `user` SET `state`=? WHERE email=?";
        
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setBoolean(1, user.getState());
        ps.setString(2, user.getEmail());
        
    
        ps.executeUpdate();
        System.out.println("User updated successfully");
        ps.close();
    }

    public void usePromoCode(User user) throws SQLException {
        String req = "UPDATE `user` SET `code_promo_id`=? WHERE email=?";

        PreparedStatement ps = conx.prepareStatement(req);        
        ps.setInt(1, user.getPromoCode());
        ps.setString(2, user.getEmail());
        
        ps.executeUpdate();
        System.out.println("promo code used successfully");
        ps.close();
    }

    public User getOneUser(String email) throws SQLException {
        String req = "SELECT * FROM `user` where email = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        User user = new User();
        user.setId(-999);

        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setRoles(rs.getString("roles")); 
            user.setPassword(rs.getString("password"));
            user.setIsVerified(rs.getBoolean("is_verified"));
            user.setName(rs.getString("name"));
            user.setLastname(rs.getString("lastname"));
            user.setTel(rs.getString("tel"));
            user.setToken(rs.getString("token"));
            user.setState(rs.getBoolean("state"));
            user.setFbLink(rs.getString("fb_link"));
            user.setTwitterLink(rs.getString("twitter_link"));
            user.setInstaLink(rs.getString("insta_link"));
            user.setVerificationCode(rs.getInt("verification_code"));
        }
        ps.close();
        return user;
    }

    public User getUserById(int id) throws SQLException {
        String req = "SELECT * FROM `user` where id = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        User user = new User();
        user.setId(-999);

        while (rs.next()) {
           user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setRoles(rs.getString("roles")); 
            user.setPassword(rs.getString("password"));
            user.setIsVerified(rs.getBoolean("is_verified"));
            user.setName(rs.getString("name"));
            user.setLastname(rs.getString("lastname"));
            user.setTel(rs.getString("tel"));
            user.setToken(rs.getString("token"));
            user.setState(rs.getBoolean("state"));
            user.setFbLink(rs.getString("fb_link"));
            user.setTwitterLink(rs.getString("twitter_link"));
            user.setInstaLink(rs.getString("insta_link"));
        }
        ps.close();
        return user;
    }

    public ArrayList<User> getAllUser() throws SQLException {
        String req = "SELECT * FROM user";
        PreparedStatement ps = conx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

           user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setRoles(rs.getString("roles")); 
            user.setPassword(rs.getString("password"));
            user.setIsVerified(rs.getBoolean("is_verified"));
            user.setName(rs.getString("name"));
            user.setLastname(rs.getString("lastname"));
            user.setTel(rs.getString("tel"));
            user.setToken(rs.getString("token"));
            user.setState(rs.getBoolean("state"));
            user.setFbLink(rs.getString("fb_link"));
            user.setTwitterLink(rs.getString("twitter_link"));
            user.setInstaLink(rs.getString("insta_link"));
            userList.add(user);
        }
        ps.close();
        return userList;
    }

    public int getActiveNB() throws SQLException {
        String req = "SELECT * FROM `user` where state = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, "1");

        ResultSet rs = ps.executeQuery();
        int count = 0;

        while (rs.next()) {
            count++;
        }
        ps.close();
        return count;
    }

    public int getunActiveNB() throws SQLException {
        String req = "SELECT * FROM `user` where state = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, "0");

        ResultSet rs = ps.executeQuery();
        int count = 0;

        while (rs.next()) {
            count++;
        }
        ps.close();
        return count;
    }
}
