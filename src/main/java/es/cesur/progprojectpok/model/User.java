package es.cesur.progprojectpok.model;


import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column (name = "USERNAME")
    private String username;
    @Column (name = "PASSWORD")
    private String password;
    @Column (name = "REMEMBERME")
    private Boolean rememberMe;
    @Column (name = "EMAIL")
    private String email;

    public User(String username, String password, Boolean rememberMe, String email) {

        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
        this.email = email;
    }

    public User(){}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
