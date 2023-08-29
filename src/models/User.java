/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Ikbel
 */
public class User {
   private int id;
   private String email;
   private String roles;
   private String password;
   private int isVerified;
   private String name;
   private String lastname;
   private String tel;
   private String img_url;

    public User() {
    }

    public User(String email, String roles, String password, int isVerified, String name, String lastname, String tel, String img_url) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.isVerified = isVerified;
        this.name = name;
        this.lastname = lastname;
        this.tel = tel;
        this.img_url = img_url;
    }

    public User(int id, String email, String roles, String password, int isVerified, String name, String lastname, String tel, String img_url) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.isVerified = isVerified;
        this.name = name;
        this.lastname = lastname;
        this.tel = tel;
        this.img_url = img_url;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRoles() {
        return roles;
    }
    
    public String getPassword() {
        return password;
    }

    public int isIsVerified() {
        return isVerified;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTel() {
        return tel;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", isVerified=" + isVerified + ", name=" + name + ", lastname=" + lastname + ", tel=" + tel + ", img_url=" + img_url + '}';
    }

}

 


