/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

/**
 *
 * @author Ikbel
 */
public class CodePromo {
       private int id_codePromo;
    private int codePromo;
    private Date date_debut;
    private Date date_fin;
    
    // constructeur avec id 

    public CodePromo(int id_codePromo, int codePromo, Date date_debut, Date date_fin) {
        this.id_codePromo = id_codePromo;
        this.codePromo = codePromo;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    
    // construteur sans id 

    public CodePromo(int codePromo, Date date_debut, Date date_fin) {
        this.codePromo = codePromo;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    
    // Constructeur non parametré
    
    public CodePromo(){};

    public CodePromo(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // geters and seters

    public int getId_codePromo() {
        return id_codePromo;
    }

    public void setId_codePromo(int id_codePromo) {
        this.id_codePromo = id_codePromo;
    }

    public int getCodePromo() {
        return codePromo;
    }

    public void setCodePromo(int codePromo) {
        this.codePromo = codePromo;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
    
    // to String

    @Override
    public String toString() {
        return "CodePromo{" + "id_codePromo=" + id_codePromo + ", codePromo=" + codePromo + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
}
