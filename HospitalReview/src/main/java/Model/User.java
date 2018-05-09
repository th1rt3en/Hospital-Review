/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
<<<<<<< HEAD
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
    
=======
 * @author Negarr
 */
public class User {
    private int id;
    private String email;
    private String password;
    private boolean activated = false;
    private String type;

    public User(int id, String email, String password, String type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
    }

>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
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

<<<<<<< HEAD
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
    
    
=======
    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User() {
    }
>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
}
