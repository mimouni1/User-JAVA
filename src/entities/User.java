package entities;

public class User {

    private int id;
    private String email;
    private String roles;
    private String password;
    private boolean isVerified;
    private String name;
    private String lastname;
    private String tel;
    private String imgUrl;
    private String token;
    private String fbLink;
    private String twitterLink;
    private String instaLink;
    private int verificationCode;
    private boolean state;
    private int codePromo;
    

    public void setPromoCode(int promoCode) {
        this.codePromo = promoCode;
    }

    public int getPromoCode() {
        return codePromo;
    }

    public User() {
    }

    public User(String name,String lastname, String email, String tel) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.tel = tel;
    }

    public User(String name,String lastname, String email, String tel, String token, String imgUrl,  String password, boolean isVerified, int codePromo) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.tel = tel;
        this.token = token;
        this.imgUrl = imgUrl;
        this.password = password;
        this.isVerified = isVerified; 
        this.codePromo = codePromo;
    }

    public User(String text, String text2, String text3, String text4, String token, String string, String text5 ) {
                this.name = text;
                this.lastname = text2;
                this.email = text3;
                this.tel = text4;
                this.token = token;
                this.imgUrl = string;
                this.password = text5;
                
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
      public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public void setInstaLink(String instaLink) {
        this.instaLink = instaLink;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getVerificationCode() {
        return this.verificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }
       public void setState(Boolean state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastame() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getToken() {
        return token;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public Boolean getState() {
        return state;
    }

    public String getFbLink() {
        return fbLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public String getInstaLink() {
        return instaLink;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", isVerified=" + isVerified + ", name=" + name + ", lastname=" + lastname + ", tel=" + tel + ", imgUrl=" + imgUrl + ", token=" + token + ", fbLink=" + fbLink + ", twitterLink=" + twitterLink + ", instaLink=" + instaLink + ", verificationCode=" + verificationCode + ", state=" + state + '}';
    }

}
 

