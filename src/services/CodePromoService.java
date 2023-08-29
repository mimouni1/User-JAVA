/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import interfaces.Interface_IService;
import models.CodePromo;
import utilities.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.User;

/**
 *
 * @author Ikbel
 */
public class CodePromoService implements Interface_IService <CodePromo>{
     //var
    Connection cnx = MyConnection.getInstance().getCnx();

    
    @Override
    public void ajouter(CodePromo t) {
         try {
            
            String req = "INSERT INTO codepromo (`codepromo`,`date_debut`,`date_fin`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.setInt(1, t.getCodePromo());
            ps.setDate(2,(Date)t.getDate_debut() );
            ps.setDate(3, (Date)t.getDate_fin());
            
            ps.executeUpdate();
            System.out.println("CodePromo Added Successfully!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override 
    public void supprimer(int id) {
        try {
            String req ="DELETE FROM codepromo WHERE id_codepromo= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            
            ps.executeUpdate();
            System.out.println("codepromo Deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<CodePromo> afficher() {
        ArrayList<CodePromo> codePromos = new ArrayList<>();

    
        try {
            
            String req = "SELECT * FROM codepromo ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                CodePromo b = new CodePromo();
                b.setId_codePromo(rs.getInt(1));
                b.setCodePromo(rs.getInt(2));
                b.setDate_debut(rs.getDate(3));
                b.setDate_fin(rs.getDate(4));
                
                codePromos.add(b);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return codePromos;
    }

    @Override
    public CodePromo readById(int id) {
        CodePromo b = new CodePromo();
        
 
        try {
           
            String req = "SELECT * FROM codepromo WHERE id_codepromo= "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
            b.setId_codePromo(rs.getInt(1));
            b.setCodePromo(rs.getInt(2));
            b.setDate_debut((Date)rs.getDate(3));
            b.setDate_fin((Date)rs.getDate(4));
            

           
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
        return b;
    
    }
    
     

    @Override
    public ArrayList<CodePromo> sortBy(String nom_column, String Asc_Dsc) {
        ArrayList<CodePromo> codePromos = new ArrayList<>();
         
        try {
            
            String req = "SELECT * FROM codepromo ORDER BY "+nom_column + " "+Asc_Dsc+" ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
               CodePromo b = new CodePromo();
                b.setId_codePromo(rs.getInt(1));
                b.setCodePromo(rs.getInt(2));
                b.setDate_debut(rs.getDate(3));
                b.setDate_fin(rs.getDate(4));
                
                codePromos.add(b);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return codePromos;
    }

    @Override
    public void modifier(CodePromo t) {
       try {
            String req ="UPDATE codepromo SET `codepromo`= ? , `date_debut`= ? , `date_fin`= ?  WHERE id_codepromo= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, t.getCodePromo());
            ps.setDate(2, (Date)t.getDate_debut());
            ps.setDate(3, (Date)t.getDate_fin());
            ps.setInt(4, t.getId_codePromo());
            ps.executeUpdate();
            System.out.println("codepromo updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
   /*  private int getPromoCodeId(String promoCode) {
    int promoCodeId = 0;
    try {
        String sql = "SELECT id FROM codepromo WHERE code = ?";
        PreparedStatement statement = cnx.prepareStatement(sql);
        statement.setString(1, promoCode);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            promoCodeId = result.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return promoCodeId;
}
  */  
}
    

