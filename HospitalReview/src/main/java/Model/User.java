/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hai06
 */
public class User {
    private int id = -1;
    private String email;
    private String password;
    private Boolean isActivated = false;
    private int type;

    public User() {
    }
    
    public User(String email, String password, Boolean isActivated, int type) {
        this.email = email;
        this.password = password;
        this.isActivated = false;
        this.type = type;
    }
    
    public User(int id, String email, String password, Boolean isActivated, int type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isActivated = isActivated;
        this.type = type;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isActivated() {
        return isActivated;
    }

    public void setActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
}
