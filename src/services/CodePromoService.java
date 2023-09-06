package services;

import entities.CodePromo;
import utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CodePromoService {
    Connection cnx = MyDB.getInstance().getConx();
    public void ajouter(CodePromo t) {
         try {
            
            String req = "INSERT INTO code_promo (`code`,`date_debut`,`date_fin`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.setString(1, t.getCode());
            ps.setDate(2,(Date)t.getDate_debut() );
            ps.setDate(3, (Date)t.getDate_fin());
            
            ps.executeUpdate();
            System.out.println("CodePromo Added Successfully!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void supprimer(int id) {
        try {
            String req ="DELETE FROM code_promo WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            
            ps.executeUpdate();
            System.out.println("codepromo Deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<CodePromo> getAllCodes() throws SQLException {
        String req = "SELECT * FROM code_promo";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        ArrayList<CodePromo> codeList = new ArrayList<>();

        while (rs.next()) {
            CodePromo code = new CodePromo();

           code.setId(rs.getInt("id"));
            code.setCode(rs.getString("code"));
            code.setDate_debut(rs.getDate("date_debut"));
            code.setDate_fin(rs.getDate("date_fin")); 
            
            codeList.add(code);
        }
        ps.close();
        return codeList;
    }
    public ArrayList<CodePromo> afficher() {
        ArrayList<CodePromo> codePromos = new ArrayList<>();
    
        try {
            
            String req = "SELECT * FROM code_promo ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                CodePromo b = new CodePromo();
                b.setId(rs.getInt(1));
                b.setCode(rs.getString(2));
                b.setDate_debut(rs.getDate(3));
                b.setDate_fin(rs.getDate(4));
                
                codePromos.add(b);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return codePromos;
    }
    
    public CodePromo readByCode(String code) {
        CodePromo b = null;
        
        try {
            String req = "SELECT * FROM code_promo WHERE code=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, code);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                // Code exists in the database
                b = new CodePromo();
                b.setId(rs.getInt(1));
                b.setCode(rs.getString(2));
                b.setDate_debut(rs.getDate(3));
                b.setDate_fin(rs.getDate(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return b;
    }
    public ArrayList<CodePromo> sortBy(String nom_column, String Asc_Dsc) {
        ArrayList<CodePromo> codePromos = new ArrayList<>();
         
        try {
            
            String req = "SELECT * FROM code_promo ORDER BY "+nom_column + " "+Asc_Dsc+" ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
               CodePromo b = new CodePromo();
                b.setId(rs.getInt(1));
                b.setCode(rs.getString(2));
                b.setDate_debut(rs.getDate(3));
                b.setDate_fin(rs.getDate(4));
                
                codePromos.add(b);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return codePromos;
    }
    public void modifier(CodePromo t) {
        try {
             String req ="UPDATE code_promo SET `code`= ? , `date_debut`= ? , `date_fin`= ?  WHERE id= ?";
             PreparedStatement ps = cnx.prepareStatement(req);
              ps.setString(1, t.getCode());
             ps.setDate(2, (Date)t.getDate_debut());
             ps.setDate(3, (Date)t.getDate_fin());
             ps.setInt(4, t.getId());
             ps.executeUpdate();
             System.out.println("codepromo updated successfully!");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
     }
}
