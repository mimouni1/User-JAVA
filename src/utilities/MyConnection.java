/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ikbel
 */
public class MyConnection {
     //DB PARAM
    static final String URL ="jdbc:mysql://localhost:3306/ratrappage";
    static final String USER ="root";
    static final String PASSWORD ="";
   
     private Connection cnx;

     static MyConnection instance;

 private MyConnection()
    {
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  public Connection getCnx()
  {
        return cnx;
  }
 public static MyConnection getInstance()
    {
        if(instance == null)
            instance = new MyConnection();
       
        return instance;
    }
    
}
