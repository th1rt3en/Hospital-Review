/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Negarr
 */
public class Hospital extends User {
    private String name;
    private String address;
    private String website = "N/A";

    public Hospital() {
    }

    public Hospital(User user) {
        super(user.getId(), user.getEmail(), user.getPassword(), user.getType());
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    
}
