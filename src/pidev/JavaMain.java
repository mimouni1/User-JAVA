/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pidev;


import interfaces.Interface_IService;
import java.sql.Date;
import models.CodePromo;
import models.User;
import services.CodePromoService;
import services.UserService;

/**
 *
 * @author Ikbel
 */
public class JavaMain {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
         Interface_IService ui= new UserService() ;
         /*Interface_IService cs= new CodePromoService() ;
         CodePromo c1= new CodePromo(123456, new Date(1920), new Date(2920));
         CodePromo c2= new CodePromo(7777, new Date(1479), new Date(1234));
         */
         
         // add codepromo
         //cs.ajouter(c1);
         //cs.ajouter(c2);
         
         // Afficher All
         
         //System.out.println(cs.afficher());
         
         // modifier codepromo
         
         //c1.setId_codePromo(1);
         //c1.setCodePromo(111111);
         //cs.modifier(c1);
         
         // Supprimer code promo
         
         //cs.supprimer(3);
         
         User u1 = new User("baha@gmail.com", "admin","azerty", 1 , "baha", "hzemi", "99229311","img");
          
          
         //ui.ajouter(u1);
         //ui.supprimer(9); fonctionnelle
          //u.setName("azouz");
          //System.out.println(u);
          //ui.modifier(u);
         //System.out.println(ui.afficher());//fonctionnelle
          //System.out.println(cs.afficher());
         
          // read by id
          
         //System.out.println(ui.readById(45)); 
          
          
      }

}



