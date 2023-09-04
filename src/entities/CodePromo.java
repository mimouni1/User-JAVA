package entities;

import java.util.Date;

public class CodePromo {
    private int id;
    private String code;
    private Date date_debut;
    private Date date_fin;
   
    public CodePromo() {
    }
 
    public CodePromo(String code, Date date_debut, Date date_fin) {
        this.code = code;
        this.date_debut = date_debut;
        this.date_fin = date_fin ;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "CodePromo [id=" + id + ", code=" + code + ", date_debut=" + date_debut + ", date_fin=" + date_fin + "]";
    }

}
