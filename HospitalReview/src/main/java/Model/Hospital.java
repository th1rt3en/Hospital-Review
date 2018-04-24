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
public class Hospital {
    private int id = -1;
    private String name;
    private String hospitalAddress;
    private String website;
    
    public Hospital() {
    }

    public Hospital(String name, String hospitalAddress, String website) {
        this.name = name;
        this.hospitalAddress = hospitalAddress;
        this.website = website;
    }
    
    public Hospital(int id, String name, String hospitalAddress, String website) {
        this.id = id;
        this.name = name;
        this.hospitalAddress = hospitalAddress;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    
    
    
    
}
